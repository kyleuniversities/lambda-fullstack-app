package com.lambda.lambda.common.util.number.evaluator.operator;

import com.lambda.lambda.common.util.number.ComplexNumber;

public final class MathSubtractOperator extends MathBinaryOperator {
    // New Instance Method
    public static MathSubtractOperator newInstance() {
        return new MathSubtractOperator();
    }

    // Constructor Method
    protected MathSubtractOperator() {
        super();
    }

    // Overridden Math Methods
    @Override
    public String toMathString() {
        return "-";
    }

    public ComplexNumber operate(ComplexNumber value1, ComplexNumber value2) {
        return value1.subtract(value2);
    }
}
