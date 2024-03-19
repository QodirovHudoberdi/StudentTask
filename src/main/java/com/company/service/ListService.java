package com.company.service;

import com.company.dto.PhotoDTO;
import com.company.dto.StudentDTO;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ListService {
    @Autowired
    private PhotoService2 photoService2;
    private static final String PATH = "src/main/resources/documents/";
    private static final String PATH2 = "src/main/resources/images/";

    // Get Excel Document
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

    // upload PDf to Database

    public void getPdf(StudentDTO dto, PhotoDTO photo) {


        File file = new File(PATH + dto.getSurName() + ".pdf");
        try (PdfWriter pdfWriter = new PdfWriter(file)) {

            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.addNewPage();
            Document document = new Document(pdfDocument);


            Paragraph paragraph = new Paragraph("STUDENT");
            paragraph.setHorizontalAlignment(HorizontalAlignment.RIGHT);
            document.add(paragraph);
            Paragraph paragraph1 = new Paragraph(dto.getFirstName() + " " + dto.getSurName() + " " + dto.getMiddleName());
            paragraph1.setHorizontalAlignment(HorizontalAlignment.LEFT);
            document.add(paragraph1);
            Paragraph paragraph5 = new Paragraph("Birthday : " + dto.getBirthdate().toString());
            paragraph5.setHorizontalAlignment(HorizontalAlignment.LEFT);
            document.add(paragraph5);
            Paragraph paragraph2 = new Paragraph("Description : " + dto.getDescription());
            paragraph2.setHorizontalAlignment(HorizontalAlignment.LEFT);
            document.add(paragraph2);
            Paragraph paragraph3 = new Paragraph("Study Started Time : " + dto.getStudyStartDate());
            paragraph3.setHorizontalAlignment(HorizontalAlignment.LEFT);
            document.add(paragraph3);
            Paragraph paragraph4 = new Paragraph("Study End Time :  " + dto.getStudyEndDate());
            paragraph4.setHorizontalAlignment(HorizontalAlignment.LEFT);
            document.add(paragraph4);

            ImageData imageData = ImageDataFactory.create(photoService2.imageSaver(dto, photo));
            Image image1 = new Image(imageData);
            image1.setHorizontalAlignment(HorizontalAlignment.RIGHT);
            image1.setMarginTop(-200);
            document.add(image1);
            document.close();

            System.out.println("Finish");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
