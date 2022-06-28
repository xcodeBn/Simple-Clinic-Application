package com.example.hassanbazzounmobileapplicationproject2.Classes;

public class Appoinments {

    private String date;
    private String symptomsDescription;

    public Appoinments(String date, String symptomsDescription) {
        this.date = date;
        this.symptomsDescription = symptomsDescription;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSymptomsDescription() {
        return symptomsDescription;
    }

    public void setSymptomsDescription(String symptomsDescription) {
        this.symptomsDescription = symptomsDescription;
    }
}
