package com.example.lecture1.task3;

import org.assertj.core.api.CollectionAssert;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static com.example.lecture1.task3.ShapeSorting.sortByVolume;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShapeSortingTest {


    @Test
    void shouldSortCollectionWhenUnorderedShapes() {
        Cylinder cylinder = new Cylinder(2, 3);
        Ball ball = new Ball(6);
        Cube cube = new Cube(2);

        List<Shape> actualShapes = Arrays.asList(cylinder, ball, cube);
        sortByVolume(actualShapes);

        List<Shape> expectedShapes = Arrays.asList(cube, cylinder, ball);

        CollectionAssert.assertThatCollection(actualShapes)
                .isEqualTo(expectedShapes);
    }

    @Test
    void shouldSortCollectionWhenOrderedShapes() {
        Cylinder cylinder = new Cylinder(2, 3);
        Ball ball = new Ball(6);
        Cube cube = new Cube(2);

        List<Shape> actualShapes = Arrays.asList(cube, cylinder, ball);
        sortByVolume(actualShapes);

        List<Shape> expectedShapes = Arrays.asList(cube, cylinder, ball);

        CollectionAssert.assertThatCollection(actualShapes)
                .isEqualTo(expectedShapes);
    }

    @Test
    void shouldSortEmptyCollectionWhenEmptyCollection() {
        List<Shape> actualEmptyList = Arrays.asList();
        sortByVolume(actualEmptyList);

        List<Shape> expectedEmptyList = Arrays.asList();

        CollectionAssert.assertThatCollection(actualEmptyList)
                .isEqualTo(expectedEmptyList);
    }

    @Test
    void shouldThrowExceptionWhenCollectionContainsNull(){
        Cylinder cylinder = new Cylinder(2, 3);
        Ball ball = new Ball(6);
        assertThrows(NullPointerException.class, () -> {
            List<Shape> actualNullList = Arrays.asList(cylinder, null, ball);
            sortByVolume(actualNullList);
        });
    }

    @Test
    void shouldThrowExceptionWhenCollectionIsNull(){
        assertThrows(NullPointerException.class, () -> {
            sortByVolume(null);
        });
    }

}