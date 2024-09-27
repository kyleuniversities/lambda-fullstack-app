package com.lambda.lambda.common.util.number.evaluator.expression;

import com.lambda.lambda.common.helper.number.ComplexNumberHelper;
import com.lambda.lambda.common.util.number.ComplexNumber;

public final class MathCotangentFunctionExpression extends MathFunctionExpression {
    // New Instance Method
    public static MathCotangentFunctionExpression newInstance(MathExpression argument) {
        return new MathCotangentFunctionExpression(argument);
    }

    // New Instance Method
    private MathCotangentFunctionExpression(MathExpression argument) {
        super(argument);
    }

    // Main Instance Methods
    @Override
    public String getName() {
        return "cot";
    }

    @Override
    protected ComplexNumber evaluateFunction(ComplexNumber argument) {
        return ComplexNumberHelper.cot(argument);
    }
}
