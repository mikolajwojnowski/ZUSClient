package org.example;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class PdfMaker {
    private String templatePath;
    private String outputPath;
    private String content;

    public PdfMaker(String outputPath, String content) {
        this.templatePath = "C:\\Users\\wojno\\Desktop\\client\\pliki\\template.pdf";
        this.outputPath = outputPath;
        this.content = content;
    }

    public int wygenerujRaport() {
        try {
            // Step 1: Create a document
            Document document = new Document();
            String file = this.outputPath + "\\raport.pdf";

            // Step 2: Create a PdfWriter instance
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));

            // Step 3: Open the document for writing
            document.open();

            // Step 4: Add the template content
            PdfReader pdfReader = new PdfReader(templatePath);
            writer.getDirectContent().addTemplate(writer.getImportedPage(pdfReader, 1), 0, 0);

            // Step 5: Add your content
            document.add(new Paragraph(" ")); // Add some space
            document.add(new Paragraph(" ")); // Add some space
            document.add(new Paragraph(content, FontFactory.getFont(FontFactory.HELVETICA, 12)));
            //document.add(new Paragraph(" ")); // Add some space

            // Step 6: Close the document
            document.close();

            // Step 7: Close the PdfWriter
            writer.close();

            // Step 8: Close the PdfReader
            pdfReader.close();

            System.out.println("PDF created successfully at: " + outputPath);


            return 0; // Success
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
            return 1; // Error
        }
    }
}
