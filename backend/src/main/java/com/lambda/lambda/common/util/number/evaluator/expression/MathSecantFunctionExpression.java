package com.lambda.lambda.common.util.number.evaluator.expression;

import com.lambda.lambda.common.helper.number.ComplexNumberHelper;
import com.lambda.lambda.common.util.number.ComplexNumber;

public final class MathSecantFunctionExpression extends MathFunctionExpression {
    // New Instance Method
    public static MathSecantFunctionExpression newInstance(MathExpression argument) {
        return new MathSecantFunctionExpression(argument);
    }

    // New Instance Method
    private MathSecantFunctionExpression(MathExpression argument) {
        super(argument);
    }

    // Main Instance Methods
    @Override
    public String getName() {
        return "sec";
    }

    @Override
    protected ComplexNumber evaluateFunction(ComplexNumber argument) {
        return ComplexNumberHelper.sec(argument);
    }
}
