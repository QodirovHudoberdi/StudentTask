package com.company.service;

import com.company.models.PhotoDTO;
import com.company.models.StudentDTO;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class DocumentService implements com.company.interfaces.Document {
    @Autowired
    private PhotoService photoService2;
    private static final String PATH = "src/main/resources/documents/";
    private static final String PATH2 = "src/main/resources/images/";

    /**
     * Upload excel document to database
     *
     * @param dto List of students
     */
    public void getExcel(List<StudentDTO> dto) {
        String filePath = PATH + "listOfStudents.xlsx";
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("students");

            XSSFRow headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("First Name");
            headerRow.createCell(2).setCellValue("Middle Name");
            headerRow.createCell(3).setCellValue("SurName");
            headerRow.createCell(4).setCellValue("Gender");
            headerRow.createCell(5).setCellValue("Birthdate");
            headerRow.createCell(6).setCellValue("Created Date");
            headerRow.createCell(7).setCellValue("Study Start Date");
            headerRow.createCell(8).setCellValue("Study End Date");

            for (int i = 0; i < dto.size(); i++) {
                StudentDTO studentDto = dto.get(i);
                System.out.println(studentDto.toString());

                XSSFRow row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(studentDto.getId());
                row.createCell(1).setCellValue(studentDto.getFirstName());
                row.createCell(2).setCellValue(studentDto.getMiddleName());
                row.createCell(3).setCellValue(studentDto.getSurName());
                row.createCell(4).setCellValue(String.valueOf(studentDto.getGender()));

                LocalDate birthdate = LocalDate.parse(studentDto.getBirthdate());
                LocalDate createdDate = studentDto.getCreatedTime().toLocalDate();
                LocalDate studyStartDate = LocalDate.parse(studentDto.getStudyStartDate());
                LocalDate studyEndDate = LocalDate.parse(studentDto.getStudyEndDate());

                row.createCell(5).setCellValue(String.valueOf(birthdate));
                row.createCell(6).setCellValue(String.valueOf(createdDate));
                row.createCell(7).setCellValue(String.valueOf(studyStartDate));
                row.createCell(8).setCellValue(String.valueOf(studyEndDate));
            }

            int columnCount = headerRow.getLastCellNum();
            for (int i = 0; i < columnCount; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Upload pdf to database
     *
     * @param photo used for set profile user's image
     */
    public void getPdf(StudentDTO dto, PhotoDTO photo) {
        String filePath = PATH + dto.getSurName() + ".pdf";
        try (
                PdfWriter pdfWriter = new PdfWriter(filePath);
                PdfDocument pdfDocument = new PdfDocument(pdfWriter);
                Document document = new Document(pdfDocument)
        ) {

            document.add(new Paragraph(dto.getFirstName() + " " + dto.getSurName() + " " + dto.getMiddleName()).setHorizontalAlignment(HorizontalAlignment.CENTER));
            document.add(new Paragraph("Birthday: " + dto.getBirthdate()).setHorizontalAlignment(HorizontalAlignment.LEFT));
            document.add(new Paragraph("Description: " + dto.getDescription()).setHorizontalAlignment(HorizontalAlignment.LEFT));
            document.add(new Paragraph("Gender : " + dto.getGender()).setHorizontalAlignment(HorizontalAlignment.LEFT));
            document.add(new Paragraph("Study Started Time: " + dto.getStudyStartDate()).setHorizontalAlignment(HorizontalAlignment.LEFT));
            document.add(new Paragraph("Study End Time: " + dto.getStudyEndDate()).setHorizontalAlignment(HorizontalAlignment.LEFT));

            String fileName = photoService2.imageSaver(dto, photo);
            ImageData imageData = ImageDataFactory.create(fileName);
            Image image = new Image(imageData).setHorizontalAlignment(HorizontalAlignment.RIGHT).setMarginRight(20);
            document.add(image);

            System.out.println("Finish");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
