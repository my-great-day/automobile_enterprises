package com.example.carpre.carpre;

public class DriveSql {
    int idDrive,workexDrive;
    String birthdayDrive;
    String categoryDrive, firstNameDrive, lastNameDrive, patronycDrive;


    public DriveSql(int idDrive, String firstNameDrive, String lastNameDrive, String patronycDrive, int workexDrive, String birthdayDrive, String categoryDrive) {
        this.idDrive = idDrive;
        this.firstNameDrive = firstNameDrive;
        this.lastNameDrive = lastNameDrive;
        this.patronycDrive = patronycDrive;
        this.workexDrive = workexDrive;
        this.birthdayDrive = birthdayDrive;
        this.categoryDrive = categoryDrive;

    }

    public int getIdDrive() {
        return idDrive;
    }

    public void setIdDrive(int idDrive) {
        this.idDrive = idDrive;
    }

    public int getWorkexDrive() {
        return workexDrive;
    }

    public void setWorkexDrive(int workexDrive) {
        this.workexDrive = workexDrive;
    }

    public String getBirthdayDrive() {
        return birthdayDrive;
    }

    public void setBirthdayDrive(String birthdayDrive) {
        this.birthdayDrive = birthdayDrive;
    }

    public String getCategoryDrive() {
        return categoryDrive;
    }

    public void setCategoryDrive(String categoryDrive) {
        this.categoryDrive = categoryDrive;
    }

    public String getFirstNameDrive() {
        return firstNameDrive;
    }

    public void setFirstNameDrive(String firstNameDrive) {
        this.firstNameDrive = firstNameDrive;
    }

    public String getLastNameDrive() {
        return lastNameDrive;
    }

    public void setLastNameDrive(String lastNameDrive) {
        this.lastNameDrive = lastNameDrive;
    }

    public String getPatronycDrive() {
        return patronycDrive;
    }

    public void setPatronycDrive(String patronycDrive) {
        this.patronycDrive = patronycDrive;
    }

}

