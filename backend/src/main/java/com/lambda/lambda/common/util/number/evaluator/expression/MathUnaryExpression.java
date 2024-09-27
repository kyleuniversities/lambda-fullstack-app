package com.lambda.lambda.common.util.number.evaluator.expression;

import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.json.JsonHelper;
import com.lambda.lambda.common.util.json.JsonMapObject;
import com.lambda.lambda.common.util.number.ComplexNumber;
import com.lambda.lambda.common.util.number.evaluator.operator.MathUnaryOperator;

public class MathUnaryExpression extends MathExpression {
    // Instance Fields
    private MathExpression term;
    private MathUnaryOperator operator;
    private boolean isPrepending;

    // New Instance Method
    public static MathUnaryExpression newInstance(MathExpression term, MathUnaryOperator operator,
            boolean isPrepending) {
        return new MathUnaryExpression(term, operator, isPrepending);
    }

    // Constructor Method
    protected MathUnaryExpression(MathExpression term, MathUnaryOperator operator,
            boolean isPrepending) {
        super();
        this.term = term;
        this.operator = operator;
        this.isPrepending = isPrepending;
    }

    // Accessor Methods
    public MathExpression getTerm() {
        return this.term;
    }

    public MathUnaryOperator getOperator() {
        return this.operator;
    }

    public boolean isPrepending() {
        return this.isPrepending;
    }

    // Overridden Math Methods
    @Override
    protected void appendJsonObject(JsonMapObject map) {
        JsonHelper.put(map, "operator", this.operator.toMathString());
        JsonHelper.put(map, "isPrepending", this.isPrepending);
        JsonHelper.put(map, "term", this.term.toJsonObject());
    }

    @Override
    public String toMathString() {
        String operatorString = this.operator.toMathString();
        String termString = this.term.toMathString();
        return ConditionalHelper.ifReturnElse(this.isPrepending, operatorString + termString,
                termString + operatorString);
    }

    @Override
    public ComplexNumber evaluate(MathExpressionArguments arguments) {
        return this.operator.operate(this.term.evaluate(arguments));
    }
}
