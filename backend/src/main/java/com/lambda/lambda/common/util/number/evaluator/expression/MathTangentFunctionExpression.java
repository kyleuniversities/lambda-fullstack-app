package com.lambda.lambda.common.util.number.evaluator.expression;

import com.lambda.lambda.common.helper.number.ComplexNumberHelper;
import com.lambda.lambda.common.util.number.ComplexNumber;

public final class MathTangentFunctionExpression extends MathFunctionExpression {
    // New Instance Method
    public static MathTangentFunctionExpression newInstance(MathExpression argument) {
        return new MathTangentFunctionExpression(argument);
    }

    // New Instance Method
    private MathTangentFunctionExpression(MathExpression argument) {
        super(argument);
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
