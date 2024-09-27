package com.lambda.lambda.common.util.number.evaluator.operator;

import com.lambda.lambda.common.util.number.ComplexNumber;

public final class MathDivideOperator extends MathBinaryOperator {
    // New Instance Method
    public static MathDivideOperator newInstance() {
        return new MathDivideOperator();
    }

    // Constructor Method
    protected MathDivideOperator() {
        super();
    }

    // Overridden Math Methods
    @Override
    public String toMathString() {
        return "/";
    }

    public ComplexNumber operate(ComplexNumber value1, ComplexNumber value2) {
        return value1.divide(value2);
    }
}
