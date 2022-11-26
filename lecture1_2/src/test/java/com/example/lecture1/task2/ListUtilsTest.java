package com.example.lecture1.task2;

import org.assertj.core.api.CollectionAssert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static task2.ListUtils.returnTop5HashTags;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ListUtilsTest {

    @Test
    void shouldReturnAnEmptyListOfMapEntry() {
        List<String> actualEmptyListOfStrings = List.of();
        returnTop5HashTags(actualEmptyListOfStrings);

        List<Map.Entry<String, Long>> expectedEmptyListOfStrings = new ArrayList<>();

        CollectionAssert.assertThatCollection(actualEmptyListOfStrings)
                .isEqualTo(expectedEmptyListOfStrings);

    }

    @Test
    void shouldReturnAnListOfMapEntryCorrectlyWhenOnlyDifferentHashTags() {
        List<String> actualEmptyListOfStrings = List.of("#hi #my #friend", "#my #name #is #Dasha", "#I #am #Dasha",
                "#hi #and #goodbye", "#Dasha #is #fine", "#dont #forget #about #friend", "#fine", "#he #is #fine");

        List<Map.Entry<String, Long>> entries = returnTop5HashTags(actualEmptyListOfStrings);

        List<Map.Entry<String, Long>> expectedListOfHashTagsMapEntry = new ArrayList<>();

        expectedListOfHashTagsMapEntry.add(Map.entry("#Dasha", 3L));
        expectedListOfHashTagsMapEntry.add(Map.entry("#fine", 3L));
        expectedListOfHashTagsMapEntry.add(Map.entry("#is", 3L));
        expectedListOfHashTagsMapEntry.add(Map.entry("#friend", 2L));
        expectedListOfHashTagsMapEntry.add(Map.entry("#hi", 2L));

        CollectionAssert.assertThatCollection(entries)
                .isEqualTo(expectedListOfHashTagsMapEntry);

    }

    @Test
    void shouldReturnAnListOfMapEntryCorrectlyWhenOnlyHashTagsThatRepeated() {
        List<String> actualEmptyListOfStrings = List.of("#hi #my #friend #friend", "#my #name #is #Dasha", "#I #am #Dasha",
                "#hi #and #goodbye", "#Dasha #is #fine", "#dont #forget #about #about #friend", "#fine", "#he #is #fine");

        List<Map.Entry<String, Long>> entries = returnTop5HashTags(actualEmptyListOfStrings);

        List<Map.Entry<String, Long>> expectedListOfHashTagsMapEntry = new ArrayList<>();

        expectedListOfHashTagsMapEntry.add(Map.entry("#Dasha", 3L));
        expectedListOfHashTagsMapEntry.add(Map.entry("#fine", 3L));
        expectedListOfHashTagsMapEntry.add(Map.entry("#is", 3L));
        expectedListOfHashTagsMapEntry.add(Map.entry("#friend", 2L));
        expectedListOfHashTagsMapEntry.add(Map.entry("#hi", 2L));

        CollectionAssert.assertThatCollection(entries)
                .isEqualTo(expectedListOfHashTagsMapEntry);

    }

    @Test
    void shouldReturnAnListOfMapEntryCorrectlyWhenNotOnlyHashTags() {
        List<String> actualEmptyListOfStrings = List.of("#hi my #friend", "#my name #is #Dasha", "I #am Dasha",
                "#hi #and #goodbye", "#Dasha is #fine", "dont #forget about #friend", "#fine", "#he #is #fine #fine");

        List<Map.Entry<String, Long>> entries = returnTop5HashTags(actualEmptyListOfStrings);

        List<Map.Entry<String, Long>> expectedListOfHashTagsMapEntry = new ArrayList<>();

        expectedListOfHashTagsMapEntry.add(Map.entry("#fine", 3L));
        expectedListOfHashTagsMapEntry.add(Map.entry("#Dasha", 2L));
        expectedListOfHashTagsMapEntry.add(Map.entry("#friend", 2L));
        expectedListOfHashTagsMapEntry.add(Map.entry("#hi", 2L));
        expectedListOfHashTagsMapEntry.add(Map.entry("#is", 2L));

        CollectionAssert.assertThatCollection(entries)
                .isEqualTo(expectedListOfHashTagsMapEntry);

    }

    @Test
    void shouldThrowExceptionWhenListContainsNull() {
        assertThrows(NullPointerException.class, () -> {
            List<String> actualListContainsNull = List.of("I am #fine", "#Dasha #loves dogs", "#love is #awesome",
                    "#Dasha likes #read books", "#reas", "#read #and #send", "#send", "#Dasha", "#loves", null);
            returnTop5HashTags(actualListContainsNull);
        });
    }

    @Test
    void shouldThrowExceptionWhenListIsNull(){
        assertThrows(NullPointerException.class, () -> {
            returnTop5HashTags(null);
        });
    }
}
