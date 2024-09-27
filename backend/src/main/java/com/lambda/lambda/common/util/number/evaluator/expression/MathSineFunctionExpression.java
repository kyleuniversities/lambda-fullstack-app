package com.lambda.lambda.common.util.number.evaluator.expression;

import com.lambda.lambda.common.helper.number.ComplexNumberHelper;
import com.lambda.lambda.common.util.number.ComplexNumber;

public final class MathSineFunctionExpression extends MathFunctionExpression {
    // New Instance Method
    public static MathSineFunctionExpression newInstance(MathExpression argument) {
        return new MathSineFunctionExpression(argument);
    }

    // New Instance Method
    private MathSineFunctionExpression(MathExpression argument) {
        super(argument);
    }

    // Main Instance Methods
    @Override
    public String getName() {
        return "sin";
    }

    @Override
    protected ComplexNumber evaluateFunction(ComplexNumber argument) {
        return ComplexNumberHelper.sin(argument);
    }
}
