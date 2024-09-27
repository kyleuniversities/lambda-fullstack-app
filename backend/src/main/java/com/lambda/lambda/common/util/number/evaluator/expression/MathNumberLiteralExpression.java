package com.lambda.lambda.common.util.number.evaluator.expression;

import com.lambda.lambda.common.helper.json.JsonHelper;
import com.lambda.lambda.common.util.json.JsonMapObject;
import com.lambda.lambda.common.util.number.ComplexNumber;

public final class MathNumberLiteralExpression extends MathExpression {
    // Instance Fields
    private ComplexNumber value;

    // New Instance Method
    public static MathNumberLiteralExpression newInstance(ComplexNumber value) {
        return new MathNumberLiteralExpression(value);
    }

    // Constructor Method
    private MathNumberLiteralExpression(ComplexNumber value) {
        super();
        this.value = value;
    }

    // Accessor Methods
    public ComplexNumber getValue() {
        return this.value;
    }

    @Override
    public ComplexNumber evaluate(MathExpressionArguments arguments) {
        return this.value;
    }

    @Override
    public String toMathString() {
        return this.value.toString();
    }

    @Override
    protected void appendJsonObject(JsonMapObject map) {
        JsonHelper.put(map, "value", this.toMathString());
    }
}
