package com.lambda.lambda.common.util.number.evaluator.expression;

import com.lambda.lambda.common.helper.json.JsonHelper;
import com.lambda.lambda.common.util.json.JsonMapObject;
import com.lambda.lambda.common.util.number.ComplexNumber;

public final class MathVariableExpression extends MathExpression {
    // Instance Fields
    private String name;

    // New Instance Method
    public static MathVariableExpression newInstance(String name) {
        return new MathVariableExpression(name);
    }

    // Constructor Method
    private MathVariableExpression(String name) {
        super();
        this.name = name;
    }

    // Accessor Methods
    public String getName() {
        return this.name;
    }

    @Override
    public ComplexNumber evaluate(MathExpressionArguments arguments) {
        return arguments.get(this.name);
    }

    @Override
    public String toMathString() {
        return this.name;
    }

    @Override
    protected void appendJsonObject(JsonMapObject map) {
        JsonHelper.put(map, "name", this.toMathString());
    }
}
