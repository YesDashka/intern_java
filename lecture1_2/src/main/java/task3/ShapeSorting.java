package task3;

import java.util.Comparator;
import java.util.List;

public final class ShapeSorting {
    public static void sortByVolume(List<Shape> shapes) {
        shapes.sort(Comparator.comparingDouble(Shape::getVolume));
    }
}
