package com.lambda.lambda.common.util.number.evaluator.operator;

import com.lambda.lambda.common.helper.json.JsonHelper;
import com.lambda.lambda.common.util.json.JsonMapObject;
import com.lambda.lambda.common.util.number.ComplexNumber;
import com.lambda.lambda.common.util.number.evaluator.component.MathComponent;
import com.lambda.lambda.common.util.number.evaluator.expression.MathExpression;
import com.lambda.lambda.common.util.number.evaluator.expression.MathExpressionArguments;

public final class MathBinaryOperation extends MathComponent {
    // Instance Fields
    private MathBinaryOperator operator;
    private MathExpression term;

    // New Instance Method
    public static MathBinaryOperation newInstance(MathBinaryOperator operator,
            MathExpression term) {
        return new MathBinaryOperation(operator, term);
    }

    // Constructor Method
    private MathBinaryOperation(MathBinaryOperator operator, MathExpression term) {
        super();
        this.operator = operator;
        this.term = term;
    }

    // Accessor Methods
    public MathBinaryOperator getOperator() {
        return this.operator;
    }

    public MathExpression getTerm() {
        return this.term;
    }

    // Operant Methods
    public ComplexNumber evaluate(ComplexNumber term, MathExpressionArguments arguments) {
        return this.operator.operate(term, this.term.evaluate(arguments));
    }

    // Overridden Math Methods
    @Override
    public String toMathString() {
        return String.format(" %s %s", this.operator.toMathString(), this.term.toMathString());
    }

    @Override
    protected void appendJsonObject(JsonMapObject map) {
        JsonHelper.put(map, "operator", this.operator.toMathString());
        JsonHelper.put(map, "term", this.term.toJsonObject());
    }
}
