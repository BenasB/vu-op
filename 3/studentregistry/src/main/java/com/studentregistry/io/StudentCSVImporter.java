package com.studentregistry.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.studentregistry.dto.NewStudentInputData;

import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class StudentCSVImporter extends Importer<NewStudentInputData> {

  public StudentCSVImporter(Stage stage) {
    super(stage, "Import students", "students.csv");
  }

  @Override
  ExtensionFilter[] getExtensions() {
    return new ExtensionFilter[] {
        new ExtensionFilter("CSV", "*.csv"),
    };
  }

  @Override
  public NewStudentInputData[] importData() {
    ArrayList<NewStudentInputData> result = new ArrayList<NewStudentInputData>();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new FileReader(file));
    } catch (FileNotFoundException e1) {
      System.err.println("Failed to close the reader while importing students from CSV");
      return new NewStudentInputData[] {};
    }
    try {
      String row;
      while ((row = reader.readLine()) != null) {
        String[] columns = row.split(", ");
        NewStudentInputData inputData = new NewStudentInputData(columns[0], columns[1], Integer.parseInt(columns[2]),
            columns[3]);

        result.add(inputData);
      }
    } catch (NumberFormatException e1) {
      System.err.println("Failed to parse integer while importing students from CSV");
    } catch (IOException e1) {
      System.err.println("Failed to read while importing students from CSV");
      return new NewStudentInputData[] {};
    }

    try {
      reader.close();
    } catch (IOException e) {
      System.err.println("Failed to close the reader while importing students from CSV");
      return new NewStudentInputData[] {};
    }
    return result.toArray(NewStudentInputData[]::new);
  }
}
