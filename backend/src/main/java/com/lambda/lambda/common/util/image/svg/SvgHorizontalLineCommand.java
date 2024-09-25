package com.lambda.lambda.common.util.image.svg;

/**
 * Structure class for SVG Horizontal Line Command
 */
public final class SvgHorizontalLineCommand extends SvgCommand {
    // Instance Fields
    private double x;

    // New Instance Method
    public static SvgHorizontalLineCommand newInstance(boolean isLowercase, double x) {
        return new SvgHorizontalLineCommand(isLowercase, x);
    }

    // Constructor Method
    private SvgHorizontalLineCommand(boolean isLowercase, double x) {
        super(isLowercase);
        this.x = x;
    }

    // Accessor Methods
    public double getX() {
        return this.x;
    }
}
