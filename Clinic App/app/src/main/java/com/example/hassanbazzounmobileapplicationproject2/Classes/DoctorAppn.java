package com.example.hassanbazzounmobileapplicationproject2.Classes;

public class DoctorAppn {

    private String patient_name;
    private String date;
    private String symptomsDescription;

    public DoctorAppn(String patient_name, String date, String symptomsDescription) {
        this.patient_name = patient_name;
        this.date = date;
        this.symptomsDescription = symptomsDescription;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
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
