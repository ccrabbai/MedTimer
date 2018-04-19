package com.example.android.medtimer;

import android.text.Editable;

/**
 * Created by onyekachukwu on 3/25/2018.
 */

public class MedicationModel {
    private Editable nameOfDrug;
    private Editable description;
    private Editable interval;
    private Editable startDate;
    private Editable endDate;
    private Editable startTime;

    public MedicationModel(Editable nameOfDrug, Editable description, Editable interval,
                           Editable startDate, Editable endDate, Editable startTime) {
        this.nameOfDrug = nameOfDrug;
        this.description = description;
        this.interval = interval;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;

    }


    public Editable getNameOfDrug() {
        return nameOfDrug;
    }

    public void setNameOfDrug(Editable nameOfDrug) {
        this.nameOfDrug = nameOfDrug;
    }

    public Editable getDescription() {
        return description;
    }

    public void setDescription(Editable description) {
        this.description = description;
    }

    public Editable getInterval() {
        return interval;
    }

    public void setInterval(Editable interval) {
        this.interval = interval;
    }

    public Editable getStartDate() {
        return startDate;
    }

    public void setStartDate(Editable startDate) {
        this.startDate = startDate;
    }

    public Editable getEndDate() {
        return endDate;
    }

    public void setEndDate(Editable endDate) {
        this.endDate = endDate;
    }

    public Editable getStartTime() {
        return startTime;
    }

    public void setStartTime(Editable startTime) {
        this.startTime = startTime;
    }
}
