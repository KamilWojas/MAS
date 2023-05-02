package com.company;

public class Status {
    private int id;
    private String name;

    // konstruktor
    public Status(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // settery i gettery
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}