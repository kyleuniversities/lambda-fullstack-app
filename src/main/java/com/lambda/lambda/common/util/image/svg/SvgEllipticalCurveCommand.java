package com.lambda.lambda.common.util.image.svg;

/**
 * Structure class for SVG Elliptical Curve Command
 */
public final class SvgEllipticalCurveCommand extends SvgCommand {
    // Instance Fields
    private double radiusX;
    private double radiusY;
    private double xAxisDegrees;
    private boolean isLargeArc;
    private boolean isSweep;
    private double x;
    private double y;

    // New Instance Method
    public static SvgEllipticalCurveCommand newInstance(boolean isLowercase, double radiusX,
            double radiusY, double xAxisDegrees, boolean isLargeArc, boolean isSweep, double x,
            double y) {
        return new SvgEllipticalCurveCommand(isLowercase, radiusX, radiusY, xAxisDegrees,
                isLargeArc, isSweep, x, y);
    }

    // Constructor Method
    private SvgEllipticalCurveCommand(boolean isLowercase, double radiusX, double radiusY,
            double xAxisDegrees, boolean isLargeArc, boolean isSweep, double x, double y) {
        super(isLowercase);
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.xAxisDegrees = xAxisDegrees;
        this.isLargeArc = isLargeArc;
        this.isSweep = isSweep;
        this.x = x;
        this.y = y;
    }

    // Accessor Methods
    public double getRadiusX() {
        return this.radiusX;
    }

    public double getRadiusY() {
        return this.radiusY;
    }

    public double getXAxisDegrees() {
        return this.xAxisDegrees;
    }

    public boolean isLargeArc() {
        return this.isLargeArc;
    }

    public boolean isSweep() {
        return this.isSweep;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
}
