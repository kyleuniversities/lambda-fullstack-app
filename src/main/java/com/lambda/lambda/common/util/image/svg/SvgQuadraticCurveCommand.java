package com.lambda.lambda.common.util.image.svg;

import java.util.List;

/**
 * Structure class for SVG Quadratic Curve Command
 */
public final class SvgQuadraticCurveCommand extends SvgCommand {
    // Instance Fields
    private List<Double> xList;
    private List<Double> yList;

    // New Instance Method
    public static SvgQuadraticCurveCommand newInstance(boolean isLowercase, List<Double> xList,
            List<Double> yList) {
        return new SvgQuadraticCurveCommand(isLowercase, xList, yList);
    }

    // Constructor Method
    private SvgQuadraticCurveCommand(boolean isLowercase, List<Double> xList, List<Double> yList) {
        super(isLowercase);
        this.xList = xList;
        this.yList = yList;
    }

    // Accessor Methods
    public List<Double> getXList() {
        return this.xList;
    }

    public List<Double> getYList() {
        return this.yList;
    }
}
