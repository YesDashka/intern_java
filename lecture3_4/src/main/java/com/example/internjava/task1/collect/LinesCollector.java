package com.example.internjava.task1.collect;

import com.example.internjava.task1.model.LineTags;

import java.util.List;

public interface LinesCollector<T> {

    String collect(List<LineTags<T>> lineTags);

}
