package com.lambda.lambda.common.util.number.coordinates;

import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.number.DoubleHelper;
import com.lambda.lambda.common.helper.number.coordinates.CoordinatesHelper;

/**
 * Structure class for a 2D Line
 */
public final class Line {
    // Instance Fields
    private double a;
    private double b;
    private double c;

    // New Instance Methods
    public static Line newInstance(Point start, Point end) {
        return CoordinatesHelper.getEnclosingLine(LineSegment.newInstance(start, end));
    }

    public static Line newInstance(double a, double b, double c) {
        return new Line(a, b, c);
    }

    // Constructor Method
    private Line(double a, double b, double c) {
        super();
        double normalizationConstant = this.collectNormalizationConstant(a, b, c);
        this.a = a * normalizationConstant;
        this.b = b * normalizationConstant;
        this.c = c * normalizationConstant;
    }

    // Accessor Methods
    public double getA() {
        return this.a;
    }

    public double getB() {
        return this.b;
    }

    public double getC() {
        return this.c;
    }

    // Private Helper Methods
    private double collectNormalizationConstant(double a, double b, double c) {
        int signFlipper = ConditionalHelper.newTernaryOperation(a < 0, () -> -1, () -> 1);
        return 1.0 * signFlipper
                / DoubleHelper.max(DoubleHelper.squareRoot(a * a + b * b + c * c), 0.000001);
    }

    // To String Method
    @Override
    public String toString() {
        double a = this.a;
        String bSign = ConditionalHelper.ifReturnElse(this.b < 0, "-", "+");
        double bAbsolute = DoubleHelper.absoluteValue(this.b);
        String cSign = ConditionalHelper.ifReturnElse(this.b < 0, "-", "+");
        double cAbsolute = DoubleHelper.absoluteValue(this.c);
        return String.format("%.3f %s %.3f %s %.3f = 0", a, bSign, bAbsolute, cSign, cAbsolute);
    }
}
