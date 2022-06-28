package com.example.hassanbazzounmobileapplicationproject2.Classes;

public class Consultation {

    private String date;
    private String payment_value;


    public Consultation(String date, String payment_value) {
        this.date = date;
        this.payment_value = payment_value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPayment_value() {
        return payment_value;
    }

    public void setPayment_value(String payment_value) {
        this.payment_value = payment_value;
    }
}

