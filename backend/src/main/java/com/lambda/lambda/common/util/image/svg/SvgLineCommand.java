package com.lambda.lambda.common.util.image.svg;

/**
 * Structure class for SVG Line Command
 */
public final class SvgLineCommand extends SvgCommand {
    // Instance Fields
    private double x;
    private double y;

    // New Instance Method
    public static SvgLineCommand newInstance(boolean isLowercase, double x, double y) {
        return new SvgLineCommand(isLowercase, x, y);
    }

    // Constructor Method
    private SvgLineCommand(boolean isLowercase, double x, double y) {
        super(isLowercase);
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
}
