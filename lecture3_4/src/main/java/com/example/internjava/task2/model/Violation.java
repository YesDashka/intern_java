package com.example.internjava.task2.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "type", "fineAmount" })
public class Violation {

    private String type;

    private double fineAmount;

    public Violation() {
    }

    public Violation(String type, double fineAmount) {
        this.type = type;
        this.fineAmount = fineAmount;
    }

    @XmlElement(name = "type")
    public String getType() {
        return type;
    }

    @XmlElement(name = "fineAmount")
    public double getFineAmount() {
        return fineAmount;
    }

    @Override
    public String toString() {
        return "type='" + type + '\'' +
                ", fineAmount=" + fineAmount;
    }
}
