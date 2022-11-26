package com.example.lecture1.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task1.ArrayUtils;

class ArrayUtilsTest {
    @Test
    void shouldFilterAndSortArrayOfPositiveNumbersCorrectly() {
        int[] actual = ArrayUtils.reverseSortFilteredByPositiveNumbers(new int[]{0, 1, 2, 3, 4});
        int[] expected = {4, 3, 2, 1, 0};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFilterAndSortArrayOfNegativeNumbersCorrectly() {
        int[] actual = ArrayUtils.reverseSortFilteredByPositiveNumbers(new int[]{-1, -2, -3, -4, -5});
        Assertions.assertArrayEquals(new int[]{}, actual);
    }

    @Test
    public void shouldFilterAndSortArrayOfPositiveAndNegativeNumbersCorrectly() {
        int[] actual = ArrayUtils.reverseSortFilteredByPositiveNumbers(new int[]{1, 2, 3, 4, 5, -1, -2, -3, -4, -5});
        int[] expected = {5, 4, 3, 2, 1};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFilterAndSortEmptyArrayCorrectly() {
        int[] actual = ArrayUtils.reverseSortFilteredByPositiveNumbers(new int[]{});
        int[] expected = {};
        Assertions.assertArrayEquals(expected, actual);
    }
}