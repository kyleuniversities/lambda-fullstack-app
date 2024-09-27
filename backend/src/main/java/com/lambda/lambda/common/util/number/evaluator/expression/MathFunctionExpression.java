package com.lambda.lambda.common.util.number.evaluator.expression;

import com.lambda.lambda.common.helper.json.JsonHelper;
import com.lambda.lambda.common.util.json.JsonMapObject;
import com.lambda.lambda.common.util.number.ComplexNumber;

public abstract class MathFunctionExpression extends MathExpression {
    // Instance Fields
    private MathExpression argument;

    // Constructor Method
    protected MathFunctionExpression(MathExpression argument) {
        super();
        this.argument = argument;
    }

    // Accessor Methods
    public MathExpression getArgument() {
        return this.argument;
    }

    // Abstract Methods
    public abstract String getName();

    protected abstract ComplexNumber evaluateFunction(ComplexNumber argument);

    @Override
    public ComplexNumber evaluate(MathExpressionArguments arguments) {
        return this.evaluateFunction(this.argument.evaluate(arguments));
    }

    @Override
    public String toMathString() {
        return String.format("%s(%s)", this.getName(), this.argument.toMathString());
    }

    @Override
    protected void appendJsonObject(JsonMapObject map) {
        JsonHelper.put(map, "name", this.getName());
        JsonHelper.put(map, "argument", this.argument.toJsonObject());
    }
}
