package com.studentregistry.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class StudentExcelExporter extends Exporter {

  public StudentExcelExporter(Stage stage, Exportable[] items) {
    super(stage, "Export students", "students.xlsx", items);
  }

  @Override
  ExtensionFilter[] getExtensions() {
    return new ExtensionFilter[] {
        new ExtensionFilter("Excel", "*.xlsx"),
    };
  }

  @Override
  void export(File file, Exportable[] items) {
    XSSFWorkbook workbook = new XSSFWorkbook();

    XSSFSheet sheet = workbook.createSheet("Students");

    for (int i = 0; i < items.length; i++) {
      String[] columns = items[i].getExportableRow();
      XSSFRow row = sheet.createRow(i);
      for (int j = 0; j < columns.length; j++) {
        row.createCell(j).setCellValue(columns[j]);
      }
    }

    try {
      FileOutputStream outputStream = new FileOutputStream(file);
      workbook.write(outputStream);
      workbook.close();
    } catch (FileNotFoundException e) {
      System.err.println("File not found");
    } catch (IOException e) {
      System.err.println("Failed to write Excel file");
    }
  }
}
