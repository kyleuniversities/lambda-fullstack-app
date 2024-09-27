package com.lambda.lambda.common.util.number.evaluator.operator;

import com.lambda.lambda.common.util.number.ComplexNumber;

public final class MathMultiplyOperator extends MathBinaryOperator {
    // New Instance Method
    public static MathMultiplyOperator newInstance() {
        return new MathMultiplyOperator();
    }

    // Constructor Method
    protected MathMultiplyOperator() {
        super();
    }

    // Overridden Math Methods
    @Override
    public String toMathString() {
        return "*";
    }

    public ComplexNumber operate(ComplexNumber value1, ComplexNumber value2) {
        return value1.multiply(value2);
    }
}
