package com.example.myapplication.Domain;

public class Price {
    private int id;
    private String Value;

    @Override
    public String toString() {
        return Value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
