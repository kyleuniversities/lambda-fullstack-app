package com.lambda.lambda.common.helper.number;

import com.lambda.lambda.common.helper.ConditionalHelper;

/**
 * Helper class for String Operations
 */
public final class DoubleHelper {
    /**
     * Returns the absolute value of an double
     */
    public static double absoluteValue(double value) {
        return ConditionalHelper.ifReturnElse(value < 0, -value, value);
    }

    /**
     * Returns the average of two doubles
     */
    public static double average(double a, double b) {
        return (a + b) / 2.0;
    }

    /**
     * Returns if two doubles are equal under a threshold
     */
    public static boolean doubleEquals(double a, double b) {
        return DoubleHelper.doubleEquals(a, b, 0.000001);
    }

    /**
     * Returns if two doubles are equal under a threshold
     */
    public static boolean doubleEquals(double a, double b, double zeroThreshold) {
        return DoubleHelper.absoluteValue(a - b) < zeroThreshold;
    }

    /**
     * Operation for greater than
     */
    public static boolean greaterThan(double a, double b) {
        return a > b;
    }

    /**
     * Returns if a doubles is zero under a threshold
     */
    public static boolean isZero(double a) {
        return DoubleHelper.isZero(a, 0.000001);
    }

    /**
     * Returns if a doubles is zero under a threshold
     */
    public static boolean isZero(double a, double zeroThreshold) {
        return DoubleHelper.doubleEquals(a, 0.0, zeroThreshold);
    }

    /**
     * Operation for less than
     */
    public static boolean lessThan(double a, double b) {
        return a < b;
    }

    /**
     * Returns the maximum of two doubles
     */
    public static double max(double a, double b) {
        return a < b ? b : a;
    }

    /**
     * Returns the minimum of two doubles
     */
    public static double min(double a, double b) {
        return a > b ? b : a;
    }

    /**
     * Returns the square root of an double
     */
    public static double squareRoot(double value) {
        return Math.sqrt(value);
    }

    /**
     * Converts a double to decimal text
     */
    public static String toDecimalText(double value, int numberOfDecimalDigits) {
        return String.format("%." + numberOfDecimalDigits + "f", value);
    }

    /**
     * Converts a double to decimal text in hundredths
     */
    public static String toDecimalTextInHundredths(double value) {
        return DoubleHelper.toDecimalText(value, 2);
    }

    /**
     * Converts a double to decimal text in millionths
     */
    public static String toDecimalTextInMillionths(double value) {
        return DoubleHelper.toDecimalText(value, 6);
    }

    /**
     * Converts a double to decimal text in tenths
     */
    public static String toDecimalTextInTenths(double value) {
        return DoubleHelper.toDecimalText(value, 1);
    }

    /**
     * Converts a double to decimal text in thousandths
     */
    public static String toDecimalTextInThousandths(double value) {
        return DoubleHelper.toDecimalText(value, 3);
    }

    /**
     * Private Constructor to prevent instantiation
     */
    private DoubleHelper() {
        super();
    }
}
