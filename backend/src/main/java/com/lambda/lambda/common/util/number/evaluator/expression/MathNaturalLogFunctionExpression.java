package com.lambda.lambda.common.util.number.evaluator.expression;

import com.lambda.lambda.common.helper.number.ComplexNumberHelper;
import com.lambda.lambda.common.util.number.ComplexNumber;

public final class MathNaturalLogFunctionExpression extends MathFunctionExpression {
    // New Instance Method
    public static MathNaturalLogFunctionExpression newInstance(MathExpression argument) {
        return new MathNaturalLogFunctionExpression(null, argument);
    }

    public static MathNaturalLogFunctionExpression newInstance(MathExpression exponent,
            MathExpression argument) {
        return new MathNaturalLogFunctionExpression(exponent, argument);
    }

    // New Instance Method
    private MathNaturalLogFunctionExpression(MathExpression exponent, MathExpression argument) {
        super(exponent, argument);
    }

    // Main Instance Methods
    @Override
    public String getName() {
        return "ln";
    }

    @Override
    protected ComplexNumber evaluateFunction(ComplexNumber argument) {
        return ComplexNumberHelper.ln(argument);
    }
}
