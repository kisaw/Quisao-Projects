package com.example.upangpocketproject.Classes;

public class PaymentDataClass {
    private String paymentID, studentID, paymentPeriod, paymentDate;
    private double paymentAmount;

    public PaymentDataClass(){}

    public PaymentDataClass(String paymentID, String studentID, String paymentPeriod, String paymentDate, double paymentAmount){
        this.paymentID = paymentID;
        this.studentID = studentID;
        this.paymentPeriod = paymentPeriod;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getPaymentPeriod() {
        return paymentPeriod;
    }

    public void setPaymentPeriod(String paymentPeriod) {
        this.paymentPeriod = paymentPeriod;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
}
