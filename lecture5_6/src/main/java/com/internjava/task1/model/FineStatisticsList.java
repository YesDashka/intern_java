package com.internjava.task1.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class FineStatisticsList {

    private List<FineStatistics> fineStatistics;

    public FineStatisticsList(List<FineStatistics> fineStatistics) {
        this.fineStatistics = fineStatistics;
    }

    public FineStatisticsList() {
    }

    @XmlElement(name = "fineStatistics")
    public List<FineStatistics> getFineStatistics() {
        return fineStatistics;
    }
}
