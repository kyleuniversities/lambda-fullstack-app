package com.lambda.lambda.common.util.number.evaluator.expression;

import com.lambda.lambda.common.helper.number.ComplexNumberHelper;
import com.lambda.lambda.common.util.number.ComplexNumber;

public final class MathCosecantFunctionExpression extends MathFunctionExpression {
    // New Instance Method
    public static MathCosecantFunctionExpression newInstance(MathExpression argument) {
        return new MathCosecantFunctionExpression(null, argument);
    }

    public static MathCosecantFunctionExpression newInstance(MathExpression exponent,
            MathExpression argument) {
        return new MathCosecantFunctionExpression(exponent, argument);
    }

    // New Instance Method
    private MathCosecantFunctionExpression(MathExpression exponent, MathExpression argument) {
        super(exponent, argument);
    }

    // Main Instance Methods
    @Override
    public String getName() {
        return "csc";
    }

    @Override
    protected ComplexNumber evaluateFunction(ComplexNumber argument) {
        return ComplexNumberHelper.csc(argument);
    }
}
