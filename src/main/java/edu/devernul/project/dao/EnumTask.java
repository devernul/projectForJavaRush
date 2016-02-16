package edu.devernul.project.dao;

import edu.devernul.project.model.Status;
import edu.devernul.project.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public enum EnumTask {

    TASK1("Task #1") {
    },
    TASK2("Task #2") {
    },
    TASK3("Task #3") {
    },
    TASK4("Task #4") {
    },
    TASK5("Task #5") {
    },
    TASK6("Task #6") {
    },
    TASK7("Task #7") {
    },
    TASK8("Task #8") {
    },
    TASK9("Task #9") {
    },
    TASK10("Task #10") {
    },
    ;


    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    private String name;

    private String description =
            "<p>awoke</p>\n" +
            "<p>eat</p>\n" +
            "<p>walk</p>\n" +
            "<p>sleep</p>";


    private String date = dateFormat.format( new Date() ) ;

    private Status status;

    private List<User> users;

    EnumTask(String name) {
        this.name = name;
    }

    public String getDate() {

        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

}
