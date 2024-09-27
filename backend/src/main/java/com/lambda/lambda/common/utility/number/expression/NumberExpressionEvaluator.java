package com.lambda.lambda.common.utility.number.expression;

/**
 * Function class for evaluating an expresion
 */
public final class NumberExpressionEvaluator {
    // Instance Fields
    private String expressionText;

    // New Instance Method
    public static NumberExpressionEvaluator newInstance() {
        return new NumberExpressionEvaluator();
    }

    // Constructor Method
    private NumberExpressionEvaluator() {
        super();
    }

    // Main Instance Method
    public String evaluate(String expressionText) {
        this.reset(expressionText);
        return null;
    }

    // Initialization Methods
    private void reset(String expressionText) {
        this.expressionText = expressionText;
    }
}
