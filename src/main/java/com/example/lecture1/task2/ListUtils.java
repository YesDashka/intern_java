package com.example.lecture1.task2;

import java.util.*;
import java.util.stream.Collectors;

public class ListUtils {

    public static List<Map.Entry<String, Long>> returnTop5HashTags(List<String> listOfStrings){
        List<List<String>> listOfListOfHashTagStrings = new ArrayList<>();
        for(String str: listOfStrings){
            List<String> listOfHashTagStrings = Arrays.stream(str.split(" "))
                    .filter(p -> p.startsWith("#"))
                    .distinct()
                    .collect(Collectors.toList());

            listOfListOfHashTagStrings.add(listOfHashTagStrings);
        }

        //the returned list is sorted by map entry key in natural order and then by values in reverse order
        // (in order to predict the order in which keys with the same values will be displayed)
        return listOfListOfHashTagStrings.stream().flatMap(Collection::stream)
                .collect(Collectors.toList())
                .stream()
                .collect(Collectors.collectingAndThen(Collectors.groupingBy(s -> s, Collectors.counting()), Map::entrySet))
                .stream()
                .sorted((s1, s2) -> Comparator.<String>naturalOrder().compare(s1.getKey(), s2.getKey()))
                .sorted((s1, s2) -> Long.compare(s2.getValue(), s1.getValue()))
                .limit(5)
                .collect(Collectors.toList());
    }
}
