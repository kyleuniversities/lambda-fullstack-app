package com.lambda.lambda.common.util.number.evaluator.expression;

import com.lambda.lambda.common.helper.number.ComplexNumberHelper;
import com.lambda.lambda.common.util.number.ComplexNumber;

public final class MathCosineFunctionExpression extends MathFunctionExpression {
    // New Instance Method
    public static MathCosineFunctionExpression newInstance(MathExpression argument) {
        return new MathCosineFunctionExpression(argument);
    }

    // New Instance Method
    private MathCosineFunctionExpression(MathExpression argument) {
        super(argument);
    }

    // Main Instance Methods
    @Override
    public String getName() {
        return "cos";
    }

    @Override
    protected ComplexNumber evaluateFunction(ComplexNumber argument) {
        return ComplexNumberHelper.cos(argument);
    }
}
