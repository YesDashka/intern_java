package com.internjava.task1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Violator {

    @JsonProperty("date_time")
    private String dateTime;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("type")
    private String type;
    @JsonProperty("fine_amount")
    private long fineAmount;

    public Violator() {
    }

    public String getType() {
        return type;
    }

    public long getFineAmount() {
        return fineAmount;
    }

    @Override
    public String toString() {
        return "Violator{" +
                "dateTime='" + dateTime + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", type='" + type + '\'' +
                ", fineAmount=" + fineAmount +
                '}';
    }
}
