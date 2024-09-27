package com.lambda.lambda.common.util.number;

import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.number.DoubleHelper;
import com.lambda.lambda.common.helper.string.StringHelper;

/**
 * Structure class for a complex number
 */
public final class ComplexNumber {
    // Instance Fields
    private double a;
    private double b;

    // New Instance Methods
    public static ComplexNumber newInstance() {
        return ComplexNumber.newInstance(0, 0);
    }

    public static ComplexNumber newInstanceFromEulerExponent(ComplexNumber eulerExponent) {
        return ComplexNumber.newInstanceFromRadiusAngle(Math.exp(eulerExponent.a), eulerExponent.b);
    }

    public static ComplexNumber newInstanceFromRadiusAngle(double r, double theta) {
        return ComplexNumber.newInstance(r * Math.cos(theta), r * Math.sin(theta));
    }

    public static ComplexNumber newInstance(double a, double b) {
        return new ComplexNumber(a, b);
    }

    // Constructor Method
    private ComplexNumber(double a, double b) {
        super();
        this.a = a;
        this.b = b;
    }

    // Accessor Methods
    public double getA() {
        return this.a;
    }

    public double getB() {
        return this.b;
    }

    public double getRadius() {
        return Math.sqrt(this.a * this.a + this.b * this.b);
    }

    public double getAngle() {
        return DoubleHelper.angle(this.a, this.b);
    }

    public ComplexNumber getEulerExponent() {
        double r = this.getRadius();
        double theta = this.getAngle();
        return ComplexNumber.newInstance(DoubleHelper.ln(r), theta);
    }

    // Operant Methods
    public ComplexNumber add(ComplexNumber z) {
        return ComplexNumber.newInstance(this.a + z.a, this.b + z.b);
    }

    public ComplexNumber subtract(ComplexNumber z) {
        return ComplexNumber.newInstance(this.a - z.a, this.b - z.b);
    }

    public ComplexNumber multiply(ComplexNumber z) {
        return ComplexNumber.newInstance(this.a * z.a - this.b * z.b, this.a * z.b + this.b * z.a);
    }

    public ComplexNumber divide(ComplexNumber z) {
        double r1 = this.getRadius();
        double theta1 = this.getAngle();
        double r2 = z.getRadius();
        double theta2 = this.getAngle();
        return ComplexNumber.newInstanceFromRadiusAngle(r1 / r2, theta1 - theta2);
    }

    public ComplexNumber power(ComplexNumber z) {
        return ComplexNumber.newInstanceFromEulerExponent(this.getEulerExponent().multiply(z));
    }

    public ComplexNumber toOpposite() {
        double r = this.getRadius();
        double theta = this.getAngle();
        return ComplexNumber.newInstanceFromRadiusAngle(-r, theta);
    }

    public ComplexNumber toReciprocal() {
        double r = this.getRadius();
        double theta = this.getAngle();
        return ComplexNumber.newInstanceFromRadiusAngle(1.0 / r, -theta);
    }

    // To String Methods
    @Override
    public String toString() {
        boolean aIsZero = DoubleHelper.doubleEquals(this.a, 0.0);
        boolean bIsZero = DoubleHelper.doubleEquals(this.b, 0.0);
        if (aIsZero && bIsZero) {
            return DoubleHelper.toDecimalTextInThousandths(0.0);
        }
        if (aIsZero) {
            return DoubleHelper.toDecimalTextInThousandths(this.b) + "i";
        }
        if (bIsZero) {
            return DoubleHelper.toDecimalTextInThousandths(this.a);
        }
        boolean bIsNegative = this.b < 0.0;
        String aText = DoubleHelper.toDecimalTextInThousandths(this.a);
        String bText = DoubleHelper.toDecimalTextInThousandths(DoubleHelper.absoluteValue(this.b));
        String sign = ConditionalHelper.ifReturnElse(bIsNegative, "-", "+");
        return StringHelper.format("%s %s %si", aText, sign, bText);
    }
}
