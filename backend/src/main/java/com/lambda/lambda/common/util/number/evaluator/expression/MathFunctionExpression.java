package com.lambda.lambda.common.util.number.evaluator.expression;

import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.json.JsonHelper;
import com.lambda.lambda.common.util.json.JsonMapObject;
import com.lambda.lambda.common.util.number.ComplexNumber;

public abstract class MathFunctionExpression extends MathExpression {
    // Instance Fields
    private MathExpression exponent;
    private MathExpression argument;

    // Constructor Method
    protected MathFunctionExpression(MathExpression exponent, MathExpression argument) {
        super();
        this.exponent = exponent;
        this.argument = argument;
    }

    // Accessor Methods
    public MathExpression getExponent() {
        return this.exponent;
    }

    public MathExpression getArgument() {
        return this.argument;
    }

    // Abstract Methods
    public abstract String getName();

    protected abstract ComplexNumber evaluateFunction(ComplexNumber argument);

    @Override
    public ComplexNumber evaluate(MathExpressionArguments arguments) {
        ComplexNumber result = this.evaluateFunction(this.argument.evaluate(arguments));
        if (this.exponent != null) {
            return result.power(this.exponent.evaluate(arguments));
        }
        return result;
    }

    @Override
    public String toMathString() {
        return String.format("%s%s(%s)", this.getName(),
                ConditionalHelper.newTernaryOperation(this.exponent == null, () -> "",
                        () -> this.exponent.toMathString()),
                this.argument.toMathString());
    }

    @Override
    protected void appendJsonObject(JsonMapObject map) {
        JsonHelper.put(map, "name", this.getName());
        ConditionalHelper.ifThen(this.exponent != null,
                () -> JsonHelper.put(map, "exponent", this.exponent.toJsonObject()));
        JsonHelper.put(map, "argument", this.argument.toJsonObject());
    }
}
