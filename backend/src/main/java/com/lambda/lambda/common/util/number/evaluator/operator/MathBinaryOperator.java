package com.lambda.lambda.common.util.number.evaluator.operator;

import com.lambda.lambda.common.util.number.ComplexNumber;

public abstract class MathBinaryOperator extends MathOperator {
    // Constructor Method
    protected MathBinaryOperator() {
        super();
    }

    // Abstract Methods
    public abstract ComplexNumber operate(ComplexNumber value1, ComplexNumber value2);
}
