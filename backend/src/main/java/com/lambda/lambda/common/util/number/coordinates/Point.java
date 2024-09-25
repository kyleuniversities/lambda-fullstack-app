package com.lambda.lambda.common.util.number.coordinates;

/**
 * Structure class for a 2D Point
 */
public final class Point {
    // Instance Fields
    private double x;
    private double y;

    // New Instance Method
    public static Point newInstance(double x, double y) {
        return new Point(x, y);
    }

    // Constructor Method
    private Point(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }

    // Accessor Methods
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    // To String Method
    @Override
    public String toString() {
        return String.format("(%.3f, %.3f)", this.x, this.y);
    }
}
