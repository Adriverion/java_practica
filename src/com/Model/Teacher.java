package com.Model;

import java.util.ArrayList;

public class Teacher {
    private String id;
    private ArrayList<Integer> subtracted;

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

    public Integer[] getListDevice() {
        return subtracted.toArray(new Integer[0]);
    }

    public void associateDevice(int index) {
        if(!subtracted.contains(index)) {
            subtracted.add(index);
        }
    }
}
