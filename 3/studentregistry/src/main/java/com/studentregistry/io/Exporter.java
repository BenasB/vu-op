package com.studentregistry.io;

import javafx.stage.Stage;

public abstract class Exporter extends FileLoader {

  public abstract void export(Exportable[] items);

  public Exporter(Stage stage, String title, String initialFileName) {
    super(stage, title, initialFileName, true);
  }
}
