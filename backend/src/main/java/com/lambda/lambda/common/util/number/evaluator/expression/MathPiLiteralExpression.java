package com.lambda.lambda.common.util.number.evaluator.expression;

import com.lambda.lambda.common.helper.string.CharacterHelper;
import com.lambda.lambda.common.util.json.JsonMapObject;
import com.lambda.lambda.common.util.number.ComplexNumber;

public final class MathPiLiteralExpression extends MathExpression {
    // New Instance Method
    public static MathPiLiteralExpression newInstance() {
        return new MathPiLiteralExpression();
    }

    // Constructor Method
    private MathPiLiteralExpression() {
        super();
    }

    @Override
    public ComplexNumber evaluate(MathExpressionArguments arguments) {
        return ComplexNumber.newInstance(Math.PI, 0);
    }

    @Override
    public String toMathString() {
        return CharacterHelper.PI + "";
    }

    @Override
    protected void appendJsonObject(JsonMapObject map) {
        // Do Nothing
    }
}
