package com.example.lecture1.task1;

import java.util.Comparator;
import java.util.stream.IntStream;

public class ArrayUtils {
    public static int[] reverseSortFilteredByPositiveNumbers(int[] arr){
        return IntStream.of(arr)
                .filter(p -> p >= 0)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(x -> x)
                .toArray();
    }
}
