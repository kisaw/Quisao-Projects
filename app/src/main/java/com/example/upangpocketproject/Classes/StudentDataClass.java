package com.example.upangpocketproject.Classes;

public class StudentDataClass {
    private String studentID, studentPassword, studentFName, studentLName, studentMI, studentPocketPassword, studentCourse;
    private double pocketBalance;

    public StudentDataClass(String studentID, String studentPassword, String studentFName, String studentLName, String studentMI, String studentPocketPassword, String studentCourse, double pocketBalance) {
        this.studentID = studentID;
        this.studentPassword = studentPassword;
        this.studentFName = studentFName;
        this.studentLName = studentLName;
        this.studentMI = studentMI;
        this.studentPocketPassword = studentPocketPassword;
        this.studentCourse = studentCourse;
        this.pocketBalance = pocketBalance;
    }

    public StudentDataClass(){

    }


    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getStudentFName() {
        return studentFName;
    }

    public void setStudentFName(String studentFName) {
        this.studentFName = studentFName;
    }

    public String getStudentLName() {
        return studentLName;
    }

    public void setStudentLName(String studentLName) {
        this.studentLName = studentLName;
    }

    public String getStudentMI() {
        return studentMI;
    }

    public void setStudentMI(String studentMI) {
        this.studentMI = studentMI;
    }

    public String getStudentPocketPassword() {
        return studentPocketPassword;
    }

    public void setStudentPocketPassword(String studentPocketPassword) {
        this.studentPocketPassword = studentPocketPassword;
    }

    public String getStudentCourse() {
        return studentCourse;
    }

    public void setStudentCourse(String studentCourse) {
        this.studentCourse = studentCourse;
    }

    public double getPocketBalance() {
        return pocketBalance;
    }

    public void setPocketBalance(double pocketBalance) {
        this.pocketBalance = pocketBalance;
    }
}
