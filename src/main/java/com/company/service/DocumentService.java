package com.company.service;

import com.company.dto.PhotoDTO;
import com.company.dto.fieldstudy.FieldStudiesDto;
import com.company.dto.studen.StudentDto;
import com.company.interfaces.Documents;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class DocumentService implements Documents {
    @Autowired
    private PhotoService photoService;
    private static final String PATH = "src/main/resources/documents/";


    /**
     * Upload excel document to database
     *
     * @param dto List of students
     */
    public void getExcel(List<StudentDto> dto) {
        String filePath = PATH + "listOfStudents.xlsx";
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            try (FileOutputStream out = new FileOutputStream(filePath)) {

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
                headerRow.createCell(9).setCellValue("University Study");
                headerRow.createCell(10).setCellValue("field of University");

                for (int i = 0; i < dto.size(); i++) {
                    StudentDto studenDto = dto.get(i);
                    System.out.println(studenDto.toString());

                    XSSFRow row = sheet.createRow(i + 1);
                    row.createCell(0).setCellValue(studenDto.getId());
                    row.createCell(1).setCellValue(studenDto.getFirstName());
                    row.createCell(2).setCellValue(studenDto.getMiddleName());
                    row.createCell(3).setCellValue(studenDto.getSurName());
                    row.createCell(4).setCellValue(String.valueOf(studenDto.getGender()));

                    LocalDate birthdate = studenDto.getBirthDate();
                    LocalDate createdDate = studenDto.getCreatedTime().toLocalDate();
                    LocalDate studyStartDate = studenDto.getStudyStartDate();
                    LocalDate studyEndDate = studenDto.getStudyEndDate();
                    FieldStudiesDto studyField = studenDto.getStudyFieldId();
                    row.createCell(5).setCellValue(String.valueOf(birthdate));
                    row.createCell(6).setCellValue(String.valueOf(createdDate));
                    row.createCell(7).setCellValue(String.valueOf(studyStartDate));
                    row.createCell(8).setCellValue(String.valueOf(studyEndDate));
                    row.createCell(9).setCellValue(studyField.getUniversity().getName());
                    row.createCell(10).setCellValue(studyField.getName());

                }

                int columnCount = headerRow.getLastCellNum();
                for (int i = 0; i < columnCount; i++) {
                    sheet.autoSizeColumn(i);
                }

                workbook.write(out);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Upload pdf to database
     *
     * @param photo used for set profile user's image
     * @param dto   fields of student which you need
     */
    public void getPdf(StudentDto dto, PhotoDTO photo) {
        String filePath = PATH + dto.getSurName() + ".pdf";
        try (
                PdfWriter pdfWriter = new PdfWriter(filePath);
                PdfDocument pdfDocument = new PdfDocument(pdfWriter);
                Document document = new Document(pdfDocument, PageSize.A4)
        ) {
            String fileName = photoService.imageSaver(dto, photo);
            ImageData imageData = ImageDataFactory.create(fileName);
            Image image = new Image(imageData).setHorizontalAlignment(HorizontalAlignment.RIGHT).setMarginTop(-20);

            image.setHeight(130);
            image.setWidth(110);

            document.add(new Paragraph(dto.getFirstName() + " " + dto.getSurName() + " " + dto.getMiddleName()).setHorizontalAlignment(HorizontalAlignment.CENTER).setFontSize(16).setWidth(300).setMarginRight(40).setUnderline());
            document.add(image);

            String age = String.valueOf(LocalDate.now().getYear() - dto.getBirthDate().getYear());
            LocalDate birthday = dto.getBirthDate();
            document.add(new Paragraph(dto.getGender() + ", " + age + " years old ." + " Date of birth : "
                    + birthday.getMonth() + " " + birthday.getDayOfMonth() + " , " + birthday.getYear()
            ).setHorizontalAlignment(HorizontalAlignment.CENTER).setFontSize(10).setWidth(300).setMarginRight(40).setMarginTop(-110));
            FieldStudiesDto studyField = dto.getStudyFieldId();
            document.add(new Paragraph("Study at  :  " + studyField.getUniversity().getName() + " University  Field  of  "
                    + studyField.getName()
            ).setHorizontalAlignment(HorizontalAlignment.CENTER).setFontSize(12).setWidth(300).setMarginRight(40));

            LocalDate startDate = dto.getStudyStartDate();

            document.add(new Paragraph("Study Started at  " + startDate.getMonth()
                    + " " + startDate.getDayOfMonth() + " , " + startDate.getYear()).setHorizontalAlignment(HorizontalAlignment.CENTER).setFontSize(12).setWidth(300).setMarginRight(40));

            LocalDate endDate = dto.getStudyEndDate();

            document.add(new Paragraph("Study Ended at  " + endDate.getMonth()
                    + " " + endDate.getDayOfMonth() + " , " + endDate.getYear()).setHorizontalAlignment(HorizontalAlignment.CENTER).setFontSize(12).setWidth(300).setMarginRight(40));

            document.add(new Paragraph("Description\n  " + dto.getDescription()).setHorizontalAlignment(HorizontalAlignment.CENTER).setFontSize(12).setWidth(300).setMarginRight(40));


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
