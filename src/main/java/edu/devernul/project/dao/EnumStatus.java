package edu.devernul.project.dao;


public enum EnumStatus {

    NEW("New") {

    },
    PROCESS("Process") {

    },
    COMPLETE("Complete") {

    };



    String title;
    EnumStatus(String title) { this.title = title; }

    public String getTitle() {
        return title;
    }
}