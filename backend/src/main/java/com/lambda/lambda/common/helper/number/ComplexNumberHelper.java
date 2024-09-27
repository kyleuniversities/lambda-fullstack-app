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
        if (trimmedText.isEmpty()) {
            return ComplexNumber.newInstance();
        }
        if (!trimmedText.contains("i")) {
            return ComplexNumber.newInstance(Double.parseDouble(trimmedText), 0);
        }
        char firstChar = trimmedText.charAt(0);
        boolean firstPartIsSigned = firstChar == '+' || firstChar == '-';
        String succeedingText =
                trimmedText.substring(ConditionalHelper.ifReturnElse(firstPartIsSigned, 1, 0));
        StringList parts = StringHelper.split(succeedingText, "[+-]+");
        if (parts.size() == 1) {
            String imaginaryText =
                    ConditionalHelper.ifReturnElse(firstPartIsSigned, firstChar + "", "+")
                            + succeedingText;
            return ComplexNumberHelper.parseImaginaryNumber(imaginaryText);
        }
        String realText = ConditionalHelper.ifReturnElse(firstPartIsSigned, firstChar + "", "+")
                + parts.get(0);
        String imaginarySign = succeedingText.substring(parts.get(0).length());
        String imaginaryText = imaginarySign + parts.get(1);
        return ComplexNumber.newInstance(Double.parseDouble(realText), 0.0)
                .add(ComplexNumberHelper.parseImaginaryNumber(imaginaryText));
    }

    /**
     * Returns a imaginary number from text
     */
    public static ComplexNumber parseImaginaryNumber(String text) {
        String trimmedText = StringDeleterHelper
                .deleteAllInstances(StringDeleterHelper.deleteAllInstances(text, 'i'), ' ');
        if (trimmedText.isEmpty()) {
            return ComplexNumber.newInstance(0.0, 1.0);
        }
        char firstChar = trimmedText.charAt(0);
        boolean firstPartIsSigned = firstChar == '+' || firstChar == '-';
        int signMultiplier = ConditionalHelper.ifReturnElse(firstChar == '-', -1, 1);
        String succeedingText =
                trimmedText.substring(ConditionalHelper.ifReturnElse(firstPartIsSigned, 1, 0));
        if (succeedingText.isEmpty()) {
            return ComplexNumber.newInstance(0.0, signMultiplier * 1.0);
        }
        return ComplexNumber.newInstance(0.0, signMultiplier * Double.parseDouble(succeedingText));
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
