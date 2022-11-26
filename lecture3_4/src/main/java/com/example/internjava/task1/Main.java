package com.example.internjava.task1;

import com.example.internjava.task1.collect.PersonAttributeLinesCollector;
import com.example.internjava.task1.model.PersonXmlTag;
import com.example.internjava.task1.read.XmlNameAttributeReader;
import com.example.internjava.task1.read.XmlAttributeCollectionReader;
import com.example.internjava.task1.model.LineTags;
import com.example.internjava.task1.collect.LinesCollector;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {

    private static final String PERSON_CLOSE_TAG = "</person>";
    private static final String PERSON_LIST_CLOSE_TAG = "</persons>";

    private static final String inputFileName = "input/task1/input.xml";
    public static final String outputFileName = "output/task1/output.xml";

    public static void main(String[] args) throws IOException {
        XmlAttributeCollectionReader<PersonXmlTag> collectionReader = new XmlAttributeCollectionReader<>(
                new XmlNameAttributeReader(),
                PERSON_CLOSE_TAG,
                PERSON_LIST_CLOSE_TAG
        );

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            List<LineTags<PersonXmlTag>> lineTags = collectionReader.readAll(reader);

            LinesCollector<PersonXmlTag> nameAttributeLinesCollector = new PersonAttributeLinesCollector();
            String collect = nameAttributeLinesCollector.collect(lineTags);

            Files.writeString(Path.of(outputFileName), collect);
        }
    }


}
