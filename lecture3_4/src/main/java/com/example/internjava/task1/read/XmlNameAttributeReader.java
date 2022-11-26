package com.example.internjava.task1.read;

import com.example.internjava.task1.model.PersonXmlTag;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlNameAttributeReader implements XmlAttributeReader<PersonXmlTag> {

    private static final Pattern SURNAME_ATTRIBUTE_PATTERN = Pattern.compile("surname.?=.?\"([^\"]*)\"[ ]?");
    private static final Pattern NAME_ATTRIBUTE_PATTERN = Pattern.compile("[ ]{1,}name.?=.?\"([^\"]*)\"");

    @Override
    public List<PersonXmlTag> read(String string) {
        List<PersonXmlTag> output = new ArrayList<>();

        Matcher surnameMatcher = SURNAME_ATTRIBUTE_PATTERN.matcher(string);
        Matcher nameMatcher = NAME_ATTRIBUTE_PATTERN.matcher(string);

        PersonXmlTag personTag = new PersonXmlTag();
        while (true) {
            if (nameMatcher.find()) {
                personTag.setName(matchGroup(nameMatcher, PersonXmlTag.NAME_ATTRIBUTE_NAME));
                string = string.replaceAll(personTag.getName(), "");
            }
            if (surnameMatcher.find()) {
                personTag.setSurname(matchGroup(surnameMatcher, PersonXmlTag.SURNAME_ATTRIBUTE_NAME));
                string = string.replaceAll(personTag.getSurname(), "");
            }
            if (personTag.isEmpty()) {
                break;
            }
            output.add(new PersonXmlTag(personTag.getName(), personTag.getSurname()));
            personTag.reset();
        }
        return output;
    }

    private static String matchGroup(Matcher matcher, String attributeName) {
        String group = matcher.group();
        return group.replaceAll(attributeName + "?.=", "")
                    .replaceAll("\"", "")
                    .trim();
    }

}
