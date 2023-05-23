package com.example.swbackend.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public enum Color {

    RED("RED"),
    GREEN("GREEN"),
    YELLOW("YELLOW"),
    PURPLE("PURPLE"),
    ORANGE("ORANGE"),
    PINK("PINK");

    private String color;
    Color(String color){
        this.color  = color;
    }
    public String getColor(){
        return this.color;
    }
    @JsonValue
    public String toString(){
        return String.valueOf(color);
    }
}
