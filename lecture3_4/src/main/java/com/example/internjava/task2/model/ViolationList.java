package com.example.internjava.task2.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class ViolationList {

    private List<Violation> violations;

    public ViolationList(List<Violation> violations) {
        this.violations = violations;
    }

    public ViolationList() {
    }

    @XmlElement(name = "violation")
    public List<Violation> getViolations() {
        return violations;
    }
}
