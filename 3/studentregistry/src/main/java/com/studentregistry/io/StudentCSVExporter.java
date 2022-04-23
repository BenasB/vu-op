package com.studentregistry.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class StudentCSVExporter extends Exporter {

  public StudentCSVExporter(Stage stage, Exportable[] items) {
    super(stage, "Export students", "students.csv", items);
  }

  @Override
  ExtensionFilter[] getExtensions() {
    return new ExtensionFilter[] {
        new ExtensionFilter("CSV", "*.csv"),
    };
  }

  @Override
  void export(File file, Exportable[] items) {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(file));
      for (Exportable row : items) {
        writer.write(String.join(", ", row.getExportableRow()));
        writer.newLine();
      }
      writer.close();
    } catch (IOException e) {
      System.err.print("Failed to export students to CSV");
    }
  }
}
