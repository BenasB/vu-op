package com.studentregistry.dto;

public class NewStudentInputData {
    public String firstName;
    public String lastName;
    public String group;

    public NewStudentInputData(String firstName, String lastName, String group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
    }
}
