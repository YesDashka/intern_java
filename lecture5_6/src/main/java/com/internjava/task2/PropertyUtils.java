package com.internjava.task2;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PropertyUtils {
    private static final Path filePath = Path.of("src/main/resources/file.properties");

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        PropertyClass propertyClass = loadFromProperties(PropertyClass.class, filePath);
        System.out.println(propertyClass.getStringProperty());
        System.out.println(propertyClass.getMyNumber());
        System.out.println(propertyClass.getTimeProperty());
    }

    public static <T> T loadFromProperties(Class<T> cls, Path propertiesPath) throws InstantiationException, IllegalAccessException {
        T propertyClassObject = cls.newInstance();

        Map<String, String> propertiesFileData = readProperties(propertiesPath);

        for (Field field : cls.getDeclaredFields()) {
            try {
                Property property = field.getAnnotation(Property.class);
                String key = getPropertyNameOrDefault(property, field);
                String value = getValue(propertiesFileData, key);
                setObjectField(propertyClassObject, field, value, property);
            } catch (InvalidPropertyException e) {
                System.err.println("unable to set object field " + field.getName() + ": errorMessage = " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return propertyClassObject;
    }

    private static String getPropertyNameOrDefault(Property property, Field field) {
        return Optional.ofNullable(property)
                .filter(s -> !s.name().equals(""))
                .map(Property::name)
                .orElse(field.getName());
    }

    private static String getValue(Map<String, String> propertiesData, String key) throws InvalidPropertyException {
        Optional<String> maybePropertyValue = Optional.empty();
        if (propertiesData.containsKey(key)) {
            maybePropertyValue = Optional.of(propertiesData.get(key));
        }
        return maybePropertyValue.orElseThrow(() -> new InvalidPropertyException("No such key: " + key));
    }

    private static Map<String, String> readProperties(Path path) {
        try {
            var properties = new Properties();
            properties.load(Files.newInputStream(path));
            Map<String, String> propertiesData = new HashMap<>();
            properties.forEach((k, v) -> propertiesData.put(k.toString(), v.toString()));
            return propertiesData;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> void setObjectField(T object, Field field, String value, Property property) throws IllegalAccessException, InvalidPropertyException {
        Class<?> fieldType = field.getType();
        field.setAccessible(true);
        if (fieldType.isAssignableFrom(String.class)) {
            field.set(object, value);
            return;
        }
        if (fieldType.isAssignableFrom(int.class) || fieldType.isAssignableFrom(Integer.class)) {
            try {
                int numberValue = Integer.parseInt(value);
                field.set(object, numberValue);
            } catch (NumberFormatException e) {
                throw new InvalidPropertyException("Invalid number provided for field " + field.getName() + ", value = " + value);
            }
        }
        if (fieldType.isAssignableFrom(Instant.class)) {
            if (property == null) {
                throw new InvalidPropertyException("Property annotation must be provided for date field");
            }
            var localDateTime = LocalDateTime.parse(value, DateTimeFormatter.ofPattern(property.format(), Locale.getDefault()));
            var instant = localDateTime.toInstant(ZoneOffset.UTC);
            field.set(object, instant);
        }
    }

}