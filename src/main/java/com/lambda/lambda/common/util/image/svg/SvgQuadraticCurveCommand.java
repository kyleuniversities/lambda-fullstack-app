package com.lambda.lambda.common.util.image.svg;

/**
 * Structure class for SVG QuadraticCurve Command
 */
public final class SvgQuadraticCurveCommand extends SvgCommand {
    // Instance Fields
    private double x1;
    private double y1;
    private double x2;
    private double y2;

    // New Instance Method
    public static SvgQuadraticCurveCommand newInstance(boolean isLowercase, double x1, double y1,
            double x2, double y2) {
        return new SvgQuadraticCurveCommand(isLowercase, x1, y1, x2, y2);
    }

    // Constructor Method
    private SvgQuadraticCurveCommand(boolean isLowercase, double x1, double y1, double x2,
            double y2) {
        super(isLowercase);
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
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
}
