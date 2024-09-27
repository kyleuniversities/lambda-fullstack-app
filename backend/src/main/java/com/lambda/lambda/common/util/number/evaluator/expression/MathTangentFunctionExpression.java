package com.lambda.lambda.common.util.number.evaluator.expression;

import com.lambda.lambda.common.helper.number.ComplexNumberHelper;
import com.lambda.lambda.common.util.number.ComplexNumber;

public final class MathTangentFunctionExpression extends MathFunctionExpression {
    // New Instance Method
    public static MathTangentFunctionExpression newInstance(MathExpression argument) {
        return new MathTangentFunctionExpression(null, argument);
    }

    public static MathTangentFunctionExpression newInstance(MathExpression exponent,
            MathExpression argument) {
        return new MathTangentFunctionExpression(exponent, argument);
    }

    // New Instance Method
    private MathTangentFunctionExpression(MathExpression exponent, MathExpression argument) {
        super(exponent, argument);
    }

    // Main Instance Methods
    @Override
    public String getName() {
        return "tan";
    }

    @Override
    protected ComplexNumber evaluateFunction(ComplexNumber argument) {
        return ComplexNumberHelper.tan(argument);
    }
}
