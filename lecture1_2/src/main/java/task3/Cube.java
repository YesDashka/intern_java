package task3;

public class Cube implements Shape{

    private final double ribLength;

    public Cube(double ribLength) {
        this.ribLength = ribLength;
    }

    @Override
    public double getVolume() {
        return Math.pow(ribLength, 3);
    }

    @Override
    public String toString() {
        return "Cube{" +
                "ribLength=" + ribLength +
                '}';
    }
}
