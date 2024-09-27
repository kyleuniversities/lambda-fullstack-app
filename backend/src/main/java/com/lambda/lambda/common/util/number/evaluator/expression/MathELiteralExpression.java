package com.lambda.lambda.common.util.number.evaluator.expression;

import com.lambda.lambda.common.util.json.JsonMapObject;
import com.lambda.lambda.common.util.number.ComplexNumber;

public final class MathELiteralExpression extends MathExpression {
    // New Instance Method
    public static MathELiteralExpression newInstance() {
        return new MathELiteralExpression();
    }

    // Constructor Method
    private MathELiteralExpression() {
        super();
    }

    @Override
    public ComplexNumber evaluate(MathExpressionArguments arguments) {
        return ComplexNumber.newInstance(Math.E, 0);
    }

    @Override
    public String toMathString() {
        return "e";
    }

    @Override
    protected void appendJsonObject(JsonMapObject map) {
        // Do Nothing
    }
}
