package com.studentregistry.io;

import java.util.ArrayList;
import java.util.Iterator;

import com.studentregistry.dto.NewStudentInputData;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class StudentExcelImporter extends Importer<NewStudentInputData> {

  public StudentExcelImporter(Stage stage) {
    super(stage, "Import students", "students.xlsx");
  }

  @Override
  ExtensionFilter[] getExtensions() {
    return new ExtensionFilter[] {
        new ExtensionFilter("Excel", "*.xlsx"),
    };
  }

  @Override
  public NewStudentInputData[] importData() {
    ArrayList<NewStudentInputData> result = new ArrayList<NewStudentInputData>();

    try {
      XSSFWorkbook workbook = new XSSFWorkbook(file);
      XSSFSheet sheet = workbook.getSheetAt(0);

      Iterator<Row> rowIterator = sheet.iterator();
      while (rowIterator.hasNext()) {
        Row row = rowIterator.next();
        Iterator<Cell> cellIterator = row.cellIterator();

        NewStudentInputData inputData = new NewStudentInputData(
            cellIterator.next().getStringCellValue(),
            cellIterator.next().getStringCellValue(),
            Integer.parseInt(cellIterator.next().getStringCellValue()),
            cellIterator.next().getStringCellValue());

        result.add(inputData);
      }
      workbook.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result.toArray(NewStudentInputData[]::new);
  }
}
