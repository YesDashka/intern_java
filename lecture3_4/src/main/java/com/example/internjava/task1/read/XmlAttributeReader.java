package com.example.internjava.task1.read;

import java.util.Collection;

public interface XmlAttributeReader<T> {

    Collection<T> read(String str);

}
