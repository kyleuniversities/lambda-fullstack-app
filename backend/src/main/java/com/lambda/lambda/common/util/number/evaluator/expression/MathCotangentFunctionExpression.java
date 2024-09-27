package com.lambda.lambda.common.util.number.evaluator.expression;

import com.lambda.lambda.common.helper.number.ComplexNumberHelper;
import com.lambda.lambda.common.util.number.ComplexNumber;

public final class MathCotangentFunctionExpression extends MathFunctionExpression {
    // New Instance Method
    public static MathCotangentFunctionExpression newInstance(MathExpression argument) {
        return new MathCotangentFunctionExpression(null, argument);
    }

    public static MathCotangentFunctionExpression newInstance(MathExpression exponent,
            MathExpression argument) {
        return new MathCotangentFunctionExpression(exponent, argument);
    }

    // New Instance Method
    private MathCotangentFunctionExpression(MathExpression exponent, MathExpression argument) {
        super(exponent, argument);
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
