package com.example.lecture1.task3;

public class Cylinder implements Shape{
    private final double height;
    private final double radius;

    public Cylinder(double height, double radius) {
        this.height = height;
        this.radius = radius;
    }

    @Override
    public double getVolume() {
        return Math.pow(radius, 2) * height * Math.PI;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ", radius=" + radius +
                '}';
    }
}
