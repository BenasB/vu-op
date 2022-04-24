package com.studentregistry.business;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.studentregistry.dto.NewStudentInputData;
import com.studentregistry.dto.UpdateStudentInputData;
import com.studentregistry.io.Exportable;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Student implements Exportable {
  private String id = UUID.randomUUID().toString();
  private String firstName;
  private String lastName;
  private Integer course;
  private String group;
  private TableView<Student> mainTable;
  private Map<LocalDate, Boolean> attendance = new HashMap<LocalDate, Boolean>();

  public Student(NewStudentInputData inputData, TableView<Student> mainTable) {
    firstName = inputData.firstName;
    lastName = inputData.lastName;
    course = inputData.course;
    group = inputData.group;
    this.mainTable = mainTable;
  }

  public void update(UpdateStudentInputData inputData) {
    firstName = inputData.firstName;
    lastName = inputData.lastName;
    course = inputData.course;
    group = inputData.group;
    mainTable.refresh();
  }

  public void updateAttendance(LocalDate date, Boolean value) {
    attendance.put(date, value);

    boolean columnExists = false;
    for (TableColumn<Student, ?> column : mainTable.getColumns()) {
      if (column.getText().equals(date.toString()))
        columnExists = true;
    }

    // Add new column if needed
    if (!columnExists && Boolean.TRUE.equals(value)) {
      TableColumn<Student, String> newColumn = new TableColumn<Student, String>(date.toString());
      newColumn.setStyle("-fx-alignment: CENTER;");
      newColumn.setCellValueFactory(
          param -> {
            String columnName = param.getTableColumn().getText();
            LocalDate dateFromColumnName = LocalDate.parse(columnName);
            Boolean hasAttendance = param.getValue().getAttendance(dateFromColumnName);
            return new ReadOnlyObjectWrapper<String>(Boolean.TRUE.equals(hasAttendance) ? "X" : "");
          });
      mainTable.getColumns().add(newColumn);

      // Sort columns by date
      Comparator<TableColumn<Student, ?>> comparator = (TableColumn<Student, ?> c1, TableColumn<Student, ?> c2) -> {
        LocalDate date1, date2;
        try {
          date1 = LocalDate.parse(c1.getText());
          date2 = LocalDate.parse(c2.getText());
        } catch (DateTimeParseException e) {
          return 0;
        }
        return date1.compareTo(date2);
      };
      mainTable.getColumns().sort(comparator);
    }

    mainTable.refresh();
  }

  public Boolean getAttendance(LocalDate date) {
    return attendance.getOrDefault(date, false);
  }

  public String getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Integer getCourse() {
    return course;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public String[] getExportableRow() {
    return new String[] { firstName, lastName, course.toString(), group };
  }
}
