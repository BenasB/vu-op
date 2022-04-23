package com.studentregistry.business;

import java.util.UUID;

import com.studentregistry.dto.NewStudentInputData;
import com.studentregistry.dto.UpdateStudentInputData;
import com.studentregistry.io.Exportable;

import javafx.scene.control.TableView;

public class Student implements Exportable {
  private String id;
  private String firstName;
  private String lastName;
  private Integer course;
  private String group;
  private TableView<Student> mainTable;

  public Student(NewStudentInputData inputData, TableView<Student> mainTable) {
    id = UUID.randomUUID().toString();
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
