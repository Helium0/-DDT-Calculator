package Utility;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {

    static FileInputStream fileInput;
    static FileOutputStream fileOutput;
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;
    static XSSFRow row;
    static XSSFCell cell;
    static CellStyle cellStyle;


    public static int getRowsNumber(String excelFile, String excelSheet) throws IOException {
        fileInput = new FileInputStream(excelFile);
        workbook = new XSSFWorkbook(fileInput);
        sheet = workbook.getSheet(excelSheet);
        int rows = sheet.getLastRowNum();
        workbook.close();
        fileInput.close();

        return rows;
    }

    public static int getCellsNumber(String excelFile, String excelSheet, int rowNumber) throws IOException {
        fileInput = new FileInputStream(excelFile);
        workbook = new XSSFWorkbook(fileInput);
        sheet = workbook.getSheet(excelSheet);
        int cells = sheet.getRow(rowNumber).getLastCellNum();
        workbook.close();
        fileInput.close();

        return cells;
    }

    public static String getCellData(String excelFile, String excelSheet, int rowNumber, int cellNumber) throws IOException {
        fileInput = new FileInputStream(excelFile);
        workbook = new XSSFWorkbook(fileInput);
        sheet = workbook.getSheet(excelSheet);
        row = sheet.getRow(rowNumber);
        cell = row.getCell(cellNumber);

        String data;

        try {

            DataFormatter dataFormatter = new DataFormatter();
            data = dataFormatter.formatCellValue(cell);

        } catch (NullPointerException e) {

            data = " ";
        }

        workbook.close();
        fileInput.close();
        return data;
    }

    public void setCellData(String excelFile, String excelSheet, int rowNumber, int cellNumber, String data) throws IOException {
        fileInput = new FileInputStream(excelFile);
        workbook = new XSSFWorkbook(fileInput);
        sheet = workbook.getSheet(excelSheet);
        row = sheet.getRow(rowNumber);
        cell = row.getCell(cellNumber);
        cell.setCellValue(data);

        fileOutput = new FileOutputStream(excelFile);
        workbook.write(fileOutput);
        workbook.close();
        fileInput.close();
        fileOutput.close();
    }

    public void fillGreen(String excelFile, String excelSheet, int rowNumber, int cellNumber) throws IOException {
        fileInput = new FileInputStream(excelFile);
        workbook = new XSSFWorkbook(fileInput);
        sheet = workbook.getSheet(excelSheet);
        row = sheet.getRow(rowNumber);
        cell = row.getCell(cellNumber);
        cellStyle = workbook.createCellStyle();

        cellStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        workbook.close();
        fileInput.close();
    }

    public void fillRed(String excelFile, String excelSheet, int rowNumber, int cellNumber) throws IOException {
        fileInput = new FileInputStream(excelFile);
        workbook = new XSSFWorkbook(fileInput);
        sheet = workbook.getSheet(excelSheet);
        row = sheet.getRow(rowNumber);
        cell = row.getCell(cellNumber);
        cellStyle = workbook.createCellStyle();

        cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        workbook.close();
        fileInput.close();
    }


}
