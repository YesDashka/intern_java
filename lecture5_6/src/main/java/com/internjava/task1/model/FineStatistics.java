package com.internjava.task1.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "type", "fineAmount" })
public class FineStatistics {

    private String type;

    private long fineAmount;

    public FineStatistics() {
    }

    public FineStatistics(String type, long fineAmount) {
        this.type = type;
        this.fineAmount = fineAmount;
    }

    @XmlElement(name = "type")
    public String getType() {
        return type;
    }

    @XmlElement(name = "fineAmount")
    public long getFineAmount() {
        return fineAmount;
    }

    @Override
    public String toString() {
        return "FineStatistics{" +
                "type='" + type + '\'' +
                ", fineAmount=" + fineAmount +
                '}';
    }
}
