package com.lambda.lambda.common.util.number.evaluator.operator;

import com.lambda.lambda.common.util.number.ComplexNumber;

public abstract class MathUnaryOperator extends MathOperator {
    // Constructor Method
    protected MathUnaryOperator() {
        super();
    }

    // Abstract Methods
    public abstract ComplexNumber operate(ComplexNumber value);
}
