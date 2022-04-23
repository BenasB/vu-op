package com.studentregistry.io;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public abstract class Exporter {

  abstract ExtensionFilter[] getExtensions();

  abstract void export(File file, Exportable[] items);

  public Exporter(Stage stage, String title, String initialFileName, Exportable[] items) {
    File file = showDialog(stage, title, initialFileName);
    if (file == null)
      return;

    export(file, items);
  }

  File showDialog(Stage stage, String title, String initialFileName) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle(title);
    fileChooser.setInitialFileName(initialFileName);
    fileChooser.getExtensionFilters().addAll(getExtensions());
    File file = fileChooser.showSaveDialog(stage);

    return file;
  }
}
