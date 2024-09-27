package com.lambda.lambda.common.util.number.evaluator.expression;

import com.lambda.lambda.common.helper.number.ComplexNumberHelper;
import com.lambda.lambda.common.util.number.ComplexNumber;

public final class MathSineFunctionExpression extends MathFunctionExpression {
    // New Instance Method
    public static MathSineFunctionExpression newInstance(MathExpression argument) {
        return new MathSineFunctionExpression(null, argument);
    }

    public static MathSineFunctionExpression newInstance(MathExpression exponent,
            MathExpression argument) {
        return new MathSineFunctionExpression(exponent, argument);
    }

    // New Instance Method
    private MathSineFunctionExpression(MathExpression exponent, MathExpression argument) {
        super(exponent, argument);
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
