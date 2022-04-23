package com.studentregistry.business;

import com.studentregistry.dto.NewStudentInputData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class StudentRegistry {
  private static StudentRegistry Instance;
  private ObservableList<Student> students = FXCollections.observableArrayList();
  private TableView<Student> mainTable;

  public StudentRegistry(TableView<Student> mainTable) throws Exception {
    if (Instance == null)
      Instance = this;
    else
      throw new Exception("StudentRegistry already initialized.");

    this.mainTable = mainTable;
  }

  public static StudentRegistry getInstance() {
    return Instance;
  }

  public void addStudent(NewStudentInputData inputData) {
    students.add(new Student(inputData, mainTable));
  }

  public void deleteStudent(Student studentToRemove) {
    students.remove(studentToRemove);
  }

  public ObservableList<Student> getStudents() {
    return students;
  }
}
