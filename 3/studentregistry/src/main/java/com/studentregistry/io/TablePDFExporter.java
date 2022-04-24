package com.studentregistry.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.studentregistry.business.Student;

import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class TablePDFExporter extends Exporter<TableView<Student>> {

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
  public void export(TableView<Student> items) {
    Document doc = new Document(PageSize.A4.rotate());
    PdfWriter writer = null;
    try {
      writer = PdfWriter.getInstance(doc, new FileOutputStream(file));
    } catch (FileNotFoundException e) {
      System.err.println("Failed to locate the file");
    } catch (DocumentException e) {
      System.err.println("Failed to add the writer to the pdf document");
    }

    doc.open();

    PdfPTable table = new PdfPTable(items.getColumns().size());

    for (int j = 0; j < items.getColumns().size(); j++) {
      table.addCell(new Phrase(items.getColumns().get(j).getText()));
    }
    table.completeRow();

    for (int i = 0; i < items.getItems().size(); i++) {
      for (int j = 0; j < items.getColumns().size(); j++) {
        if (items.getColumns().get(j).getCellData(i) != null) {
          table.addCell(new Phrase(items.getColumns().get(j).getCellData(i).toString()));
        } else {
          table.addCell(new Phrase(""));
        }
      }
      table.completeRow();
    }

    try {
      doc.add(table);
    } catch (DocumentException e) {
      System.err.println("Failed to add the table to the pdf document");
    }
    doc.close();
    writer.close();
  }
}
