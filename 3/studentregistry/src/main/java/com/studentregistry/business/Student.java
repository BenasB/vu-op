package com.studentregistry.business;

import java.util.UUID;

import com.studentregistry.dto.NewStudentInputData;
import com.studentregistry.dto.UpdateStudentInputData;

import javafx.scene.control.TableView;

public class Student {
  private String id;
  private String firstName;
  private String lastName;
  private Integer course;
  private String group;
  private TableView<Student> mainTable;

  public Student(NewStudentInputData inputData, TableView<Student> mainTable) {
    id = UUID.randomUUID().toString();
    this.mainTable = mainTable;
    firstName = inputData.firstName;
    lastName = inputData.lastName;
    course = inputData.course;
    group = inputData.group;
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
  public String toString() {
    return id + ", " + firstName + ", " + lastName + ", " + course + ", " + group;
  }
}
