package com.example.upangpocketproject.Classes;

public class TuitionDataClass {
    private String courseID;
    private double courseTFee, courseMFee, courseLabFee, courseOtherFee;

    public TuitionDataClass(String courseID, double courseTFee, double courseMFee, double courseLabFee, double courseOtherFee){
        this.courseID = courseID;
        this.courseTFee = courseTFee;
        this.courseMFee = courseMFee;
        this.courseLabFee = courseLabFee;
        this.courseOtherFee = courseOtherFee;
    }

    public TuitionDataClass(){}

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public double getCourseTFee() {
        return courseTFee;
    }

    public void setCourseTFee(double courseTFee) {
        this.courseTFee = courseTFee;
    }

    public double getCourseMFee() {
        return courseMFee;
    }

    public void setCourseMFee(double courseMFee) {
        this.courseMFee = courseMFee;
    }

    public double getCourseLabFee() {
        return courseLabFee;
    }

    public void setCourseLabFee(double courseLabFee) {
        this.courseLabFee = courseLabFee;
    }

    public double getCourseOtherFee() {
        return courseOtherFee;
    }

    public void setCourseOtherFee(double courseOtherFee) {
        this.courseOtherFee = courseOtherFee;
    }
}
