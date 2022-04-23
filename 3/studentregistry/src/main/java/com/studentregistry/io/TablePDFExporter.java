package com.studentregistry.io;

import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class TablePDFExporter extends Exporter {

  public TablePDFExporter(Stage stage) {
    super(stage, "Export table", "students.pdf");
  }

  @Override
  ExtensionFilter[] getExtensions() {
    return new ExtensionFilter[] {
        new ExtensionFilter("PDF", "*.pdf"),
    };
  }

  @Override
  public void export(Exportable[] items) {
    // TODO Auto-generated method stub

  }

}
