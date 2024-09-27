package com.lambda.lambda.common.helper.number;

import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.string.StringDeleterHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.util.number.ComplexNumber;
import com.lambda.lambda.common.util.string.StringList;

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
     * Returns a complex number from text
     */
    public static ComplexNumber parseComplexNumber(String text) {
        String trimmedText = StringDeleterHelper.deleteAllInstances(text, ' ');
        if (!trimmedText.contains("i") || trimmedText.isEmpty()) {
            return ComplexNumber.newInstance(Double.parseDouble(trimmedText), 0);
        }
        char firstChar = trimmedText.charAt(0);
        boolean isSigned = firstChar == '-' || firstChar == '+';
        String componentsText = ConditionalHelper.ifReturnElse(isSigned, "", "+") + trimmedText;
        StringList parts = StringHelper.split(componentsText.substring(1), "[+-]+");
        String rawFirstPart = parts.get(0);
        String firstPart = ConditionalHelper.ifReturnElse(isSigned, firstChar, "+") + rawFirstPart;
        if (firstPart.contains("i")) {
            return ComplexNumber.newInstance(0,
                    Double.parseDouble(trimmedText.substring(0, trimmedText.length() - 1)));
        }
        String sign = componentsText.charAt(1 + rawFirstPart.length()) + "";
        String secondPart = sign + parts.get(1);
        return ComplexNumber.newInstance(Double.parseDouble(firstPart),
                Double.parseDouble(secondPart.substring(0, secondPart.length() - 1)));
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
