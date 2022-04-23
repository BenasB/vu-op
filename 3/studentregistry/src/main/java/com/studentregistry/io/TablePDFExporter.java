package com.studentregistry.io;

import java.io.File;

import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class TablePDFExporter extends Exporter {

  public TablePDFExporter(Stage stage, Exportable[] items) {
    super(stage, "Export table", "students.pdf", items);
  }

  @Override
  ExtensionFilter[] getExtensions() {
    return new ExtensionFilter[] {
        new ExtensionFilter("PDF", "*.pdf"),
    };
  }

  @Override
  void export(File file, Exportable[] items) {
    // TODO Auto-generated method stub

  }

}
