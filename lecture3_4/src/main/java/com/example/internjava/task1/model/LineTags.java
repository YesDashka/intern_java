package com.example.internjava.task1.model;

import java.util.List;

public class LineTags<T> {
    private final String line;
    private final List<T> tags;

    public LineTags(String line, List<T> tags) {
        this.line = line;
        this.tags = tags;
    }

    public String getLine() {
        return line;
    }

    public List<T> getTags() {
        return tags;
    }
}
