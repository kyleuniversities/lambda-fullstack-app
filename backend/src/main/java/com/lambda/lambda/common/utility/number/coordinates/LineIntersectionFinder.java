package com.lambda.lambda.common.utility.number.coordinates;

import com.lambda.lambda.common.helper.ExceptionHelper;
import com.lambda.lambda.common.helper.number.DoubleHelper;
import com.lambda.lambda.common.util.number.coordinates.Line;
import com.lambda.lambda.common.util.number.coordinates.Point;

/**
 * Function class for finding the intersection between two lines
 */
public final class LineIntersectionFinder {
    // Instance Fields
    private Line f;
    private Line g;

    // New Instance Method
    public static LineIntersectionFinder newInstance() {
        return new LineIntersectionFinder();
    }

    // Constructor Method
    private LineIntersectionFinder() {
        super();
    }

    // Main Instance Method
    public Point find(Line f, Line g) {
        this.reset(f, g);
        if (this.isSameSlope()) {
            ExceptionHelper
                    .throwNewIllegalStateException("Lines do not have a single intersection.");
        }
        if (this.firstLineIsHorizontal()) {
            return this.findHorizontalIntersection();
        }
        if (this.secondLineIsVertical()) {
            return this.findVerticalIntersection();
        }
        return this.findSubstitutableIntersection();
    }

    // Major Methods
    private boolean isSameSlope() {
        return DoubleHelper.doubleEquals(f.getA(), g.getA())
                && DoubleHelper.doubleEquals(f.getB(), g.getB());
    }

    private boolean firstLineIsHorizontal() {
        return DoubleHelper.isZero(f.getA());
    }

    private boolean secondLineIsVertical() {
        return DoubleHelper.isZero(f.getB());
    }

    private Point findHorizontalIntersection() {
        double y1 = -f.getC();
        double a2 = g.getA();
        double b2 = g.getB();
        double c2 = g.getC();
        double x = -(b2 * y1 + c2) / a2;
        double y = y1;
        return Point.newInstance(x, y);
    }

    private Point findVerticalIntersection() {
        double x2 = -g.getC();
        double a1 = f.getA();
        double b1 = f.getB();
        double c1 = f.getC();
        double x = x2;
        double y = -(a1 * x2 + c1) / b1;
        return Point.newInstance(x, y);
    }

    // x = - (B_1y + C_1) / A_1
    // y = - (A_2 x + C_2) / B_2
    // y = - (A_2 (- (B_1y + C_1) / A_1) + C_2) /B_2
    // y = - (A_2 (- B_1y - C_1) / A_1 + C_2) / B_2
    // y = - (A_2 (- B_1y - C_1) + C_2 A_1) / (A_1B_2)
    // y = - (A_1C_2 - A_2B_1 - A_2C_1) / (A_1B_2)
    // x = - (B_1y + C_1) / A_1
    private Point findSubstitutableIntersection() {
        double a1 = f.getA();
        double b1 = f.getB();
        double c1 = f.getC();
        double a2 = g.getA();
        double b2 = g.getB();
        double c2 = g.getC();
        double y = -(a1 * c2 - a2 * b1 - a2 * c1) / (a1 * b2);
        double x = -(b1 * y + c1) / a1;
        return Point.newInstance(x, y);
    }

    // Initialization Methods
    private void reset(Line f, Line g) {
        this.f = f;
        this.g = g;
    }
}
