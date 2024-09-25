package com.lambda.lambda.common.util.image.svg;

/**
 * Structure class for SVG Move Command
 */
public final class SvgMoveCommand extends SvgCommand {
    // Instance Fields
    private double x;
    private double y;

    // New Instance Method
    public static SvgMoveCommand newInstance(boolean isLowercase, double x, double y) {
        return new SvgMoveCommand(isLowercase, x, y);
    }

    // Constructor Method
    private SvgMoveCommand(boolean isLowercase, double x, double y) {
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
