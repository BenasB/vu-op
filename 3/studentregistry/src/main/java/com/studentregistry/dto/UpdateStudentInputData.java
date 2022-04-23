package com.studentregistry.dto;

public class UpdateStudentInputData {
    public String firstName;
    public String lastName;
    public Integer course;
    public String group;

    public UpdateStudentInputData(String firstName, String lastName, Integer course, String group) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;
        this.group = group;
    }
}
