package com.company.service;

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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Service;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ListService {
    private static final String PATH = "src/main/resources/";


    public void getExcel(List<StudentDTO> dto) {

        File file = new File(PATH + "listOfStudents.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();

        try (FileOutputStream out = new FileOutputStream(file)) {

            XSSFSheet sheet = workbook.createSheet("students");


            XSSFRow row0 = sheet.createRow(0);
            row0.createCell(0).setCellValue("ID");
            row0.createCell(1).setCellValue(" FullName ");
            row0.createCell(2).setCellValue(" Birthdate ");
            row0.createCell(3).setCellValue("Created Date");
            row0.createCell(4).setCellValue("Study Start Date");
            row0.createCell(5).setCellValue("Study End Date");


            for (int i = 0; i < dto.size(); i++) {
                StudentDTO studentDto = dto.get(i);
                System.out.println(studentDto.toString());

                XSSFRow row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(studentDto.getId());
                row.createCell(1).setCellValue(studentDto.getFirstName() + " " + studentDto.getSurName());
                row.createCell(2).setCellValue(String.valueOf(studentDto.getBirthdate().toLocalDate()));
                row.createCell(3).setCellValue(String.valueOf(studentDto.getCreatedTime()));
                row.createCell(4).setCellValue(String.valueOf(studentDto.getStudyStartDate().toLocalDate()));
                row.createCell(5).setCellValue(String.valueOf(studentDto.getStudyEndDate().toLocalDate()));


            }

            for (int i = 0; i < 6; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            workbook.close();

            Desktop.getDesktop().open(file);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void getPdf(StudentDTO dto) {


        File file = new File(PATH + "student.pdf");
        try (PdfWriter pdfWriter = new PdfWriter(file)) {

            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.addNewPage();
            Document document = new Document(pdfDocument);


            Paragraph paragraph = new Paragraph("STUDENT");
            paragraph.setHorizontalAlignment(HorizontalAlignment.RIGHT);
            document.add(paragraph);
            Paragraph paragraph1 = new Paragraph(dto.getFirstName()+" " +dto.getSurName()+" "+ dto.getMiddleName());
            paragraph1.setHorizontalAlignment(HorizontalAlignment.RIGHT);
            document.add(paragraph1);
            Paragraph paragraph5 = new Paragraph("Birthday : "+dto.getBirthdate().toString());
            paragraph5.setHorizontalAlignment(HorizontalAlignment.RIGHT);
            document.add(paragraph5);
            Paragraph paragraph2 = new Paragraph("Description : " +dto.getDescription());
            paragraph2.setHorizontalAlignment(HorizontalAlignment.RIGHT);
            document.add(paragraph2);
            Paragraph paragraph3 = new Paragraph("Study Started Time : "+ dto.getStudyStartDate().toLocalDate());
            paragraph3.setHorizontalAlignment(HorizontalAlignment.RIGHT);
            document.add(paragraph3);
            Paragraph paragraph4 = new Paragraph("Study End Time :  "+ dto.getStudyEndDate().toLocalDate());
            paragraph4.setHorizontalAlignment(HorizontalAlignment.RIGHT);
            document.add(paragraph4);


      // add image

            String fileName = PATH + "student.png";

            ImageData imageData = ImageDataFactory.create(fileName);
            Image image = new Image(imageData);

            document.add(image);

            // finally
            document.close();

            System.out.println("Finish");

            Desktop.getDesktop().open(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
