package com.lambda.lambda.common.util.number.evaluator.expression;

import com.lambda.lambda.common.helper.number.ComplexNumberHelper;
import com.lambda.lambda.common.util.number.ComplexNumber;

public final class MathNaturalLogFunctionExpression extends MathFunctionExpression {
    // New Instance Method
    public static MathNaturalLogFunctionExpression newInstance(MathExpression argument) {
        return new MathNaturalLogFunctionExpression(argument);
    }

    // New Instance Method
    private MathNaturalLogFunctionExpression(MathExpression argument) {
        super(argument);
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
