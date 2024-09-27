package com.lambda.lambda.common.util.number.evaluator.expression;

import com.lambda.lambda.common.util.number.ComplexNumber;
import com.lambda.lambda.common.util.number.evaluator.component.MathComponent;

public abstract class MathExpression extends MathComponent {
    // Constructor Method
    protected MathExpression() {
        super();
    }

    // Abstract Methods
    public abstract ComplexNumber evaluate(MathExpressionArguments arguments);
}
