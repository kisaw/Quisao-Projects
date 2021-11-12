package com.example.upangpocketproject.Classes;

public class ReportDataClass {
    private String reportID, studentID, message, datetime;

    public ReportDataClass(){}

    public ReportDataClass(String reportID, String studentID, String message, String datetime) {
        this.reportID = reportID;
        this.studentID = studentID;
        this.message = message;
        this.datetime = datetime;
    }

    public String getReportID() {
        return reportID;
    }

    public void setReportID(String reportID) {
        this.reportID = reportID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return datetime;
    }

    public void setDate(String date) {
        this.datetime = date;
    }

}
