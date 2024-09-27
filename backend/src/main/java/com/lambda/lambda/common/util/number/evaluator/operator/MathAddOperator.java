package com.lambda.lambda.common.util.number.evaluator.operator;

import com.lambda.lambda.common.util.number.ComplexNumber;

public final class MathAddOperator extends MathBinaryOperator {
    // New Instance Method
    public static MathAddOperator newInstance() {
        return new MathAddOperator();
    }

    // Constructor Method
    protected MathAddOperator() {
        super();
    }

    // Overridden Math Methods
    @Override
    public String toMathString() {
        return "+";
    }

    public ComplexNumber operate(ComplexNumber value1, ComplexNumber value2) {
        return value1.add(value2);
    }
}
