package com.example.swbackend.constant;

public enum Color {
    RED("RED"),
    GREEN("GREEN"),
    YELLOW("YELLOW"),
    PURPLE("PURPLE"),
    ORANGE("ORANGE");

    private String color;
    Color(String color){
        this.color  = color;
    }

    public String getColor(){
        return this.color;
    }
}
