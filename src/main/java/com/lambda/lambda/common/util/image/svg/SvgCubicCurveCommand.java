package com.lambda.lambda.common.util.image.svg;

import java.util.List;

/**
 * Structure class for SVG Cubic Curve Command
 */
public final class SvgCubicCurveCommand extends SvgCommand {
    // Instance Fields
    private List<Double> xList;
    private List<Double> yList;

    // New Instance Method
    public static SvgCubicCurveCommand newInstance(boolean isLowercase, List<Double> xList,
            List<Double> yList) {
        return new SvgCubicCurveCommand(isLowercase, xList, yList);
    }

    // Constructor Method
    private SvgCubicCurveCommand(boolean isLowercase, List<Double> xList, List<Double> yList) {
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
