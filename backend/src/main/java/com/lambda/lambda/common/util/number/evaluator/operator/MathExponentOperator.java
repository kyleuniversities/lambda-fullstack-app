package com.lambda.lambda.common.util.number.evaluator.operator;

import com.lambda.lambda.common.util.number.ComplexNumber;

public final class MathExponentOperator extends MathBinaryOperator {
    // New Instance Method
    public static MathExponentOperator newInstance() {
        return new MathExponentOperator();
    }

    // Constructor Method
    protected MathExponentOperator() {
        super();
    }

    // Overridden Math Methods
    @Override
    public String toMathString() {
        return "^";
    }

    public ComplexNumber operate(ComplexNumber value1, ComplexNumber value2) {
        return value1.power(value2);
    }
}
