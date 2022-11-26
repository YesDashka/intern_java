package com.example.internjava.task1.collect;

import com.example.internjava.task1.model.PersonXmlTag;
import com.example.internjava.task1.model.LineTags;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PersonAttributeLinesCollector implements LinesCollector<PersonXmlTag> {
    private static final Pattern SURNAME_ATTRIBUTE_PATTERN = Pattern.compile("surname.?=.?\"([^\"]*)\"[ ]?");

    @Override
    public String collect(List<LineTags<PersonXmlTag>> lineTagsList) {
        return lineTagsList.stream().map(lineTags -> {
            String line = lineTags.getLine();

            List<PersonXmlTag> validTuples = new ArrayList<>();
            PersonXmlTag tmp = new PersonXmlTag();
            for (PersonXmlTag stringTuple2 : lineTags.getTags()) {
                if (stringTuple2.getSurname() != null && tmp.getSurname() == null) {
                    tmp.setSurname(stringTuple2.getSurname());
                }
                if (stringTuple2.getName() != null && tmp.getName() == null) {
                    tmp.setName(stringTuple2.getName());
                }
                if (tmp.getName() != null && tmp.getSurname() != null) {
                    validTuples.add(tmp);
                    tmp = new PersonXmlTag();
                }
            }
            if (tmp.getSurname() != null || tmp.getName() != null) {
                validTuples.add(tmp);
            }

            for (PersonXmlTag tag : validTuples) {
                line = line.replaceFirst(SURNAME_ATTRIBUTE_PATTERN.pattern(), "");
                String name = getOrEmpty(tag.getName());
                String surname = getOrEmpty(tag.getSurname());
                String joined = String.join(" ", name, surname);
                line = line.replaceFirst(
                        getNameAttributePattern(name),
                        createAttribute(PersonXmlTag.NAME_ATTRIBUTE_NAME, joined));
            }

            return line;
        }).collect(Collectors.joining());

    }

    private static String getOrEmpty(String str) {
        return str == null ? "" : str;
    }

    private static String createAttribute(String attributeName, String value) {
        return String.format("%s=\"%s\"", attributeName, value);
    }
    private static String getNameAttributePattern(String value) {
        return String.format("%s.?=.?\"%s\"", PersonXmlTag.NAME_ATTRIBUTE_NAME, value);
    }

}
