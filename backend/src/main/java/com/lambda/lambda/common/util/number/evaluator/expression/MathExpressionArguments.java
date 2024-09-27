package com.lambda.lambda.common.util.number.evaluator.expression;

import com.lambda.lambda.common.util.number.ComplexNumber;

public final class MathExpressionArguments {
    // Instance Fields
    private ComplexNumber x;
    private ComplexNumber y;

    // New Instance Method
    public static MathExpressionArguments newInstance(ComplexNumber x, ComplexNumber y) {
        return new MathExpressionArguments(x, y);
    }

    // Constructor Method
    private MathExpressionArguments(ComplexNumber x, ComplexNumber y) {
        super();
        this.x = x;
        this.y = y;
    }

    // Accessor Methods
    public ComplexNumber getX() {
        return this.x;
    }

    public ComplexNumber getY() {
        return this.y;
    }

    public ComplexNumber get(String name) {
        if (name.equals("x")) {
            return this.getX();
        }
        return this.getY();
    }
}
