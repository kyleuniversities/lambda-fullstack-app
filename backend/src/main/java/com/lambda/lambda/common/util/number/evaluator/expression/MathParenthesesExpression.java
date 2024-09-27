package com.lambda.lambda.common.util.number.evaluator.expression;

import com.lambda.lambda.common.helper.json.JsonHelper;
import com.lambda.lambda.common.util.json.JsonMapObject;
import com.lambda.lambda.common.util.number.ComplexNumber;

public final class MathParenthesesExpression extends MathExpression {
    // Instance Fields
    private MathExpression expression;

    // New Instance Method
    public static MathParenthesesExpression newInstance(MathExpression expression) {
        return new MathParenthesesExpression(expression);
    }

    // Constructor Method
    private MathParenthesesExpression(MathExpression expression) {
        super();
        this.expression = expression;
    }

    // Accessor Methods
    public MathExpression getExpression() {
        return this.expression;
    }

    @Override
    public ComplexNumber evaluate(MathExpressionArguments arguments) {
        return this.expression.evaluate(arguments);
    }

    @Override
    public String toMathString() {
        return String.format("(%s)", this.expression.toMathString());
    }

    @Override
    protected void appendJsonObject(JsonMapObject map) {
        JsonHelper.put(map, "expression", this.expression.toJsonObject());
    }
}
