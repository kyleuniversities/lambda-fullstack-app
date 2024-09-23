package com.lambda.lambda.common.util.image.svg;

/**
 * Structure class for SVG CubicCurve Command
 */
public final class SvgCubicCurveCommand extends SvgCommand {
    // Instance Fields
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;

    // New Instance Method
    public static SvgCubicCurveCommand newInstance(boolean isLowercase, double x1, double y1,
            double x2, double y2, double x3, double y3) {
        return new SvgCubicCurveCommand(isLowercase, x1, y1, x2, y2, x3, y3);
    }

    // Constructor Method
    private SvgCubicCurveCommand(boolean isLowercase, double x1, double y1, double x2, double y2,
            double x3, double y3) {
        super(isLowercase);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    // Accessor Methods
    public double getX1() {
        return this.x1;
    }

    public double getY1() {
        return this.y1;
    }

    public double getX2() {
        return this.x2;
    }

    public double getY2() {
        return this.y2;
    }

    public double getX3() {
        return this.x3;
    }

    public double getY3() {
        return this.y3;
    }
}
