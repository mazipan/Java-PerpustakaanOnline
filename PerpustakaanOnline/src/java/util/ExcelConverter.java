/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author jitzu
 */
public class ExcelConverter {

    public ExcelConverter() {

    }

    public static void makeFolder(String path) {
        File cekfolder = new File(path);
        //"C:/report_perpustakaan_online"
        if (!cekfolder.exists()) {
            new File(path).mkdir();
        }
    }

    public static File createXlsx(String[] header, String[][] data, String path) {

        try {
            XSSFWorkbook xwb = new XSSFWorkbook();
            XSSFSheet sheet = xwb.createSheet();

            CellStyle cellStyle = xwb.createCellStyle();
            cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
            cellStyle.setAlignment(CellStyle.VERTICAL_TOP);
            cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            cellStyle.setWrapText(false);

            Font bold = xwb.createFont();
            bold.setBoldweight(Font.BOLDWEIGHT_BOLD);
            bold.setFontHeightInPoints((short) 10);

            CellStyle cellStyleHeader = xwb.createCellStyle();
            cellStyleHeader.setAlignment(CellStyle.ALIGN_LEFT);
            cellStyleHeader.setAlignment(CellStyle.VERTICAL_TOP);
            cellStyleHeader.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            cellStyleHeader.setBorderTop(XSSFCellStyle.BORDER_THIN);
            cellStyleHeader.setBorderRight(XSSFCellStyle.BORDER_THIN);
            cellStyleHeader.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            cellStyleHeader.setFont(bold);
            cellStyleHeader.setWrapText(false);

            XSSFRow row;
            Cell cell;

            //header
            row = sheet.createRow(0);
            for (int i = 0; i < header.length; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(cellStyleHeader);
                cell.setCellValue(header[i]);
            }

            int colCount = header.length;
            int no = 1;

            for (String[] obj : data) {
                row = sheet.createRow(no);
                for (int i = 0; i < colCount; i++) {
                    cell = row.createCell(i);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(obj[i]);
                }
                no++;
            }

            for (int i = 0; i < header.length; i++) {
                sheet.autoSizeColumn(i);
            }

            File newFile = new File(path);
            try (FileOutputStream fileOut = new FileOutputStream(path)) {
                xwb.write(fileOut);
            }

            return newFile;
        } catch (IOException e) {
            return null;
        }
    }

    public static File createXls(String[] header, String[][] data, String path) {

        try {
            HSSFWorkbook xwb = new HSSFWorkbook();
            HSSFSheet sheet = xwb.createSheet();

            CellStyle cellStyle = xwb.createCellStyle();
            cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
            cellStyle.setAlignment(CellStyle.VERTICAL_TOP);
            cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            cellStyle.setWrapText(false);

            Font bold = xwb.createFont();
            bold.setBoldweight(Font.BOLDWEIGHT_BOLD);
            bold.setFontHeightInPoints((short) 10);

            CellStyle cellStyleHeader = xwb.createCellStyle();
            cellStyleHeader.setAlignment(CellStyle.ALIGN_LEFT);
            cellStyleHeader.setAlignment(CellStyle.VERTICAL_TOP);
            cellStyleHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            cellStyleHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
            cellStyleHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
            cellStyleHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            cellStyleHeader.setFont(bold);
            cellStyleHeader.setWrapText(false);

            HSSFRow row;
            Cell cell;

            //header
            row = sheet.createRow(0);
            for (int i = 0; i < header.length; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(cellStyleHeader);
                cell.setCellValue(header[i]);
            }

            int colCount = header.length;
            int no = 1;

            for (String[] obj : data) {
                row = sheet.createRow(no);
                for (int i = 0; i < colCount; i++) {
                    cell = row.createCell(i);
                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(obj[i]);
                }
                no++;
            }

            for (int i = 0; i < header.length; i++) {
                sheet.autoSizeColumn(i);
            }

            File newFile = new File(path);
            try (FileOutputStream fileOut = new FileOutputStream(newFile)) {
                xwb.write(fileOut);
            }

            return newFile;
        } catch (IOException e) {
            return null;
        }
    }

}
