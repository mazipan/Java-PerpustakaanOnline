/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jitzu
 */
public class PDFconverter {

    public PDFconverter() {

    }

    public static void createPDF(String[] header, String[][] data, String path, String tittle, float[] columnWidths) {
        try {
            Document doc = new Document();

            PdfWriter.getInstance(doc, new FileOutputStream(path));

            doc.open();
            doc.setPageSize(PageSize.A4);
            doc.setMargins(10, 10, 10, 10);
            Font litle = new Font(Font.COURIER, 7, Font.NORMAL);
            Font norm = new Font(Font.TIMES_ROMAN, 8, Font.NORMAL);
            Font normBold = new Font(Font.TIMES_ROMAN, 8, Font.BOLD);
            Font TitleFont = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);

            doc.add(Chunk.NEWLINE);
            Paragraph judul = new Paragraph(tittle, TitleFont);
            judul.setAlignment(Element.ALIGN_CENTER);
            doc.add(judul);

//          Paragraph tgl = new Paragraph("tanggal " + tanggal + "\n", TitleFont);            
//          tgl.setAlignment(Element.ALIGN_CENTER);
//          doc.add(tgl);
            doc.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(header.length);
            table.setWidthPercentage(100f);

            for (String head : header) {
                table.addCell(new PdfPCell(new Phrase(head, normBold)));
            }

            for (String[] obj : data) {
                for (int i = 0; i < header.length; i++) {
                    table.addCell(new PdfPCell(new Phrase(obj[i], norm)));
                }

            }

            //float[] columnWidths = new float[] {10f, 20f, 30f, 10f};
            table.setWidths(columnWidths);

            doc.add(table);

//            Paragraph stamp = new Paragraph(new Chunk("this report has generated with QCMS by " + System.getProperty("user.name") + " on " + new Date(), litle));
//            stamp.setAlignment(Element.ALIGN_BOTTOM);
//            stamp.setAlignment(Element.ALIGN_CENTER);
//            doc.add(stamp);
//
//            Paragraph tanda = new Paragraph(new Chunk("Mengetahui,", norm));
//            tanda.setSpacingBefore(100);
//            tanda.setAlignment(Element.ALIGN_RIGHT);
//            tanda.setAlignment(Element.ALIGN_BOTTOM);
//            doc.add(tanda);
//
//            Paragraph nama = new Paragraph(new Chunk("MANAGER Dept.RnQ", norm));
//            nama.setSpacingBefore(30);
//            nama.setAlignment(Element.ALIGN_RIGHT);
//            nama.setAlignment(Element.ALIGN_BOTTOM);
//            doc.add(nama);
            doc.close();
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(PDFconverter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
