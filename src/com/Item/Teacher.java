package com.Item;

import java.util.ArrayList;

public class Teacher {
    private String id;
    public ArrayList<Integer> subtracted;

    public Teacher(String id) {
        this.id = id;
        subtracted = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
