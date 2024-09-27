package com.lambda.lambda.common.util.number.evaluator.operator;

import com.lambda.lambda.common.util.number.ComplexNumber;

public final class MathNegativeOperator extends MathUnaryOperator {
    // New Instance Method
    public static MathNegativeOperator newInstance() {
        return new MathNegativeOperator();
    }

    // Constructor Method
    protected MathNegativeOperator() {
        super();
    }

    // Overridden Math Methods
    @Override
    public String toMathString() {
        return "-";
    }

    public ComplexNumber operate(ComplexNumber value) {
        return value.toOpposite();
    }
}
