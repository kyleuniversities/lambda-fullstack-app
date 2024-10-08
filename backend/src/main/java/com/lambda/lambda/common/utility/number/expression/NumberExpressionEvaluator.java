package com.lambda.lambda.common.utility.number.expression;

import java.util.List;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.number.ComplexNumberHelper;
import com.lambda.lambda.common.helper.number.DoubleHelper;
import com.lambda.lambda.common.util.number.ComplexNumber;
import com.lambda.lambda.common.util.number.evaluator.expression.MathExpression;
import com.lambda.lambda.common.util.number.evaluator.expression.MathExpressionArguments;
import com.lambda.lambda.common.util.number.evaluator.lexer.MathLexer;
import com.lambda.lambda.common.util.number.evaluator.parser.MathParser;
import com.lambda.lambda.common.util.number.evaluator.util.MathToken;

/**
 * Function class for evaluating an expresion
 */
public final class NumberExpressionEvaluator {
    public static void main(String[] args) {
        System.out.println("\n\n<<Start>>\n\n");
        String expression = "0^0";
        String xText = "-2+i";
        String yText = "10";
        System.out.println(0.0 * Double.POSITIVE_INFINITY);
        System.out.println(DoubleHelper.ln(0));
        System.out.println(Math.exp(Double.NEGATIVE_INFINITY));
        System.out.println(xText);
        System.out.println(yText);
        System.out.println(expression);
        String output = NumberExpressionEvaluator.newInstance().evaluate(expression, xText, yText);
        System.out.println(output);
        System.out.println("\n\n<<Finished>>\n\n");
    }

    // New Instance Method
    public static NumberExpressionEvaluator newInstance() {
        return new NumberExpressionEvaluator();
    }

    // Constructor Method
    private NumberExpressionEvaluator() {
        super();
    }

    // Main Instance Methods
    public String evaluate(String expressionText, String xText, String yText) {
        return this.evaluate(expressionText, ComplexNumberHelper.parseComplexNumber(xText),
                ComplexNumberHelper.parseComplexNumber(yText));
    }

    public String evaluate(String expressionText, ComplexNumber x, ComplexNumber y) {
        try {
            List<MathToken> tokens = MathLexer.newInstance().tokenize(expressionText);
            MathExpression expression = MathParser.newInstance().parse(tokens, 0);
            MathExpressionArguments arguments = MathExpressionArguments.newInstance(x, y);
            ComplexNumber output = expression.evaluate(arguments);
            return output.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Unable to parse function expression.  Please check the formatting of your function.";
        }
    }
}
