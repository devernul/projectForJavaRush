package edu.devernul.project.dao;


public enum EnumUser {

    DMITRIY("dmitry","devernul@gmail.com") {

    },
    OLEG("oleg","oleges@gmail.com") {

    },;


    private String nameUser;
    private String eMail;

    EnumUser(String nameUser, String eMail) {
        this.eMail = eMail;
        this.nameUser = nameUser;
    }

    public String geteMail() {
        return eMail;
    }

    public String getNameUser() {
        return nameUser;
    }
}