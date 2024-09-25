package com.lambda.lambda.common.util.image.svg;

/**
 * Structure class for SVG Vertical Line Command
 */
public final class SvgVerticalLineCommand extends SvgCommand {
    // Instance Fields
    private double y;

    // New Instance Method
    public static SvgVerticalLineCommand newInstance(boolean isLowercase, double y) {
        return new SvgVerticalLineCommand(isLowercase, y);
    }

    // Constructor Method
    private SvgVerticalLineCommand(boolean isLowercase, double y) {
        super(isLowercase);
        this.y = y;
    }

    // Accessor Methods
    public double getY() {
        return this.y;
    }
}
