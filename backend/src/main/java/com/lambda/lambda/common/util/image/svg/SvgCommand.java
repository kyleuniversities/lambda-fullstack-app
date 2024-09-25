package com.lambda.lambda.common.util.image.svg;

/**
 * Structure class for SVG Command
 */
public abstract class SvgCommand {
    // Instance Fields
    private boolean isRelative;

    // Constructor Method
    protected SvgCommand(boolean isRelative) {
        super();
        this.isRelative = isRelative;
    }

    // Accessor Methods
    public final boolean isRelative() {
        return this.isRelative;
    }
}
