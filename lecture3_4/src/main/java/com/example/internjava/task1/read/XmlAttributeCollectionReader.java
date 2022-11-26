package com.example.internjava.task1.read;

import com.example.internjava.task1.model.LineTags;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlAttributeCollectionReader<T> {

    private static final String NEXT_LINE = "\n";
    private static final String SHORT_END_TAG = "/>";
    
    private final XmlAttributeReader<T> attributeReader;
    private final String closeTag;
    private final String collectionCloseTag;

    public XmlAttributeCollectionReader(
            XmlAttributeReader<T> xmlAttributeReader,
            String closeTag,
            String collectionCloseTag
    ) {
        this.attributeReader = xmlAttributeReader;
        this.closeTag = closeTag;
        this.collectionCloseTag = collectionCloseTag;
    }

    public List<LineTags<T>> readAll(BufferedReader reader) throws IOException {
        List<LineTags<T>> attributeLines = new ArrayList<>();

        String prevLine = "";
        String tmpLine;
        List<T> tmpAttributes = new ArrayList<>();
        while ((tmpLine = reader.readLine()) != null) {
            tmpAttributes.addAll(attributeReader.read(tmpLine));
            tmpLine = prevLine + tmpLine;
            if (tmpLine.contains(SHORT_END_TAG) || tmpLine.contains(closeTag)) {
                attributeLines.add(new LineTags<>(tmpLine, tmpAttributes));
                tmpAttributes = new ArrayList<>();
                prevLine = NEXT_LINE;
                continue;
            }
            if (tmpLine.contains(collectionCloseTag)) {
                attributeLines.add(new LineTags<>(tmpLine, tmpAttributes));
            }
            prevLine = tmpLine + NEXT_LINE;
        }
        return attributeLines;
    }
}
