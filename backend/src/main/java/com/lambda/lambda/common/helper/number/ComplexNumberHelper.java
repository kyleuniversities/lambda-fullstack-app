package com.lambda.lambda.common.helper.number;

import com.lambda.lambda.common.util.number.ComplexNumber;

/**
 * Helper class for Complex Number Operations
 */
public final class ComplexNumberHelper {
    /**
     * Returns the absolute value of a complex number
     */
    public static double absoluteValue(ComplexNumber z) {
        return z.getRadius();
    }

    /**
     * Returns the cosine of a complex number
     */
    public static ComplexNumber cos(ComplexNumber z) {
        return ComplexNumber.newInstance(Math.cos(z.getA()) * Math.cosh(z.getB()),
                -Math.sin(z.getA()) * Math.sinh(z.getB()));
    }

    /**
     * Returns the cotangent of a complex number
     */
    public static ComplexNumber cot(ComplexNumber z) {
        return ComplexNumberHelper.tan(z).toReciprocal();
    }

    /**
     * Returns the cosecant of a complex number
     */
    public static ComplexNumber csc(ComplexNumber z) {
        return ComplexNumberHelper.sin(z).toReciprocal();
    }

    /**
     * Returns the natural log of a complex number
     */
    public static ComplexNumber ln(ComplexNumber z) {
        return z.getEulerExponent();
    }

    /**
     * Returns the secant of a complex number
     */
    public static ComplexNumber sec(ComplexNumber z) {
        return ComplexNumberHelper.cos(z).toReciprocal();
    }

    /**
     * Returns the sine of a complex number
     */
    public static ComplexNumber sin(ComplexNumber z) {
        return ComplexNumber.newInstance(Math.sin(z.getA()) * Math.cosh(z.getB()),
                Math.cos(z.getA()) * Math.sinh(z.getB()));
    }

    /**
     * Returns the tangent of a complex number
     */
    public static ComplexNumber tan(ComplexNumber z) {
        return ComplexNumberHelper.sin(z).divide(ComplexNumberHelper.cos(z));
    }

    /**
     * Private Constructor to prevent instantiation
     */
    private ComplexNumberHelper() {
        super();
    }
}
