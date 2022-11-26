package com.example.internjava.task1.model;

public class PersonXmlTag {

    public static final String NAME_ATTRIBUTE_NAME = "name";
    public static final String SURNAME_ATTRIBUTE_NAME = "surname";

    private String name;
    private String surname;

    public PersonXmlTag(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public PersonXmlTag() {
    }

    public boolean isEmpty() {
        return name == null && surname == null;
    }

    public void reset() {
        this.name = null;
        this.surname = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "PersonXmlTag{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

}
