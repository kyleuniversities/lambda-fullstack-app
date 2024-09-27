package com.lambda.lambda.common.util.number.evaluator.parser;

import java.util.List;
import com.lambda.lambda.common.helper.ArrayHelper;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.number.evaluator.MathTokenHelper;
import com.lambda.lambda.common.helper.string.StringDeleterHelper;
import com.lambda.lambda.common.util.number.ComplexNumber;
import com.lambda.lambda.common.util.number.evaluator.expression.MathBracesExpression;
import com.lambda.lambda.common.util.number.evaluator.expression.MathBracketsExpression;
import com.lambda.lambda.common.util.number.evaluator.expression.MathCosecantFunctionExpression;
import com.lambda.lambda.common.util.number.evaluator.expression.MathCosineFunctionExpression;
import com.lambda.lambda.common.util.number.evaluator.expression.MathCotangentFunctionExpression;
import com.lambda.lambda.common.util.number.evaluator.expression.MathELiteralExpression;
import com.lambda.lambda.common.util.number.evaluator.expression.MathExpression;
import com.lambda.lambda.common.util.number.evaluator.expression.MathNaturalLogFunctionExpression;
import com.lambda.lambda.common.util.number.evaluator.expression.MathNumberLiteralExpression;
import com.lambda.lambda.common.util.number.evaluator.expression.MathOperationChainExpression;
import com.lambda.lambda.common.util.number.evaluator.expression.MathParenthesesExpression;
import com.lambda.lambda.common.util.number.evaluator.expression.MathPiLiteralExpression;
import com.lambda.lambda.common.util.number.evaluator.expression.MathSecantFunctionExpression;
import com.lambda.lambda.common.util.number.evaluator.expression.MathSineFunctionExpression;
import com.lambda.lambda.common.util.number.evaluator.expression.MathTangentFunctionExpression;
import com.lambda.lambda.common.util.number.evaluator.expression.MathUnaryExpression;
import com.lambda.lambda.common.util.number.evaluator.expression.MathVariableExpression;
import com.lambda.lambda.common.util.number.evaluator.operator.MathBinaryOperation;
import com.lambda.lambda.common.util.number.evaluator.operator.MathBinaryOperator;
import com.lambda.lambda.common.util.number.evaluator.operator.MathNegativeOperator;
import com.lambda.lambda.common.util.number.evaluator.util.MathToken;
import com.lambda.lambda.common.util.number.evaluator.util.MathTokens;
import com.lambda.lambda.common.util.wrapper.OrdinaryWrapper;
import com.lambda.lambda.common.util.wrapper.Wrapper;

public final class MathParser {
    // Instance Fields
    private List<MathToken> tokens;
    private int index;

    // New Instance Method
    public static MathParser newInstance() {
        return new MathParser();
    }

    // Constructor Method
    private MathParser() {
        super();
    }

    // Main Instance Methods
    public MathExpression parse(List<MathToken> tokens, int index) {
        this.reset(tokens, index);
        MathExpression unaryExpression = this.parseUnaryExpression();
        MathExpression multiTermExpression = this.parseItem(unaryExpression, 0);
        return multiTermExpression;
    }

    private MathExpression parseItem(MathExpression leftmostTerm, int depthIndex) {
        // Set Up
        List<MathExpression> expressions = ListHelper.newArrayList();
        List<MathBinaryOperator> operators = ListHelper.newArrayList();
        Wrapper<MathExpression> firstUnaryExpressionWrapper =
                OrdinaryWrapper.newInstance(leftmostTerm);
        // Iteration
        ConditionalHelper.whileLoopUntilFalse(() -> {
            // Set Up Iteration Expression
            MathExpression firstUnaryExpression = firstUnaryExpressionWrapper.getValue();
            MathTokens currentTokenType = this.getCurrentTokenType();
            int currentTokenDepth = this.getTokenDepth(currentTokenType);
            boolean postTermOperationTokenTypeIsTerminating =
                    this.postTermOperationTokenTypeIsTerminating();
            boolean postTermOperationTokenTypeIsShallowerDepth =
                    !postTermOperationTokenTypeIsTerminating && currentTokenDepth < depthIndex;
            boolean postTermOperationTokenTypeIsDeeperDepth =
                    !postTermOperationTokenTypeIsTerminating && currentTokenDepth > depthIndex;
            boolean postTermOperationTokenTypeIsSameDepth = !postTermOperationTokenTypeIsTerminating
                    && !postTermOperationTokenTypeIsShallowerDepth
                    && !postTermOperationTokenTypeIsDeeperDepth;
            // Set Up term operation expression
            MathExpression termOperationExpression =
                    ConditionalHelper.newTernaryOperation(postTermOperationTokenTypeIsDeeperDepth,
                            () -> this.parseItem(firstUnaryExpression, depthIndex + 1),
                            () -> firstUnaryExpression);
            // Handle deeper depth
            if (postTermOperationTokenTypeIsDeeperDepth) {
                firstUnaryExpressionWrapper.setValue(termOperationExpression);
                return true;
            }
            // Add expression to expression list
            ListHelper.add(expressions, termOperationExpression);
            // Handle same depth
            ConditionalHelper.ifThen(postTermOperationTokenTypeIsSameDepth, () -> {
                boolean isBinaryOperator = MathTokenHelper.isBinaryOperator(currentTokenType);
                ConditionalHelper.ifThen(isBinaryOperator, () -> this.popCurrentToken());
                MathTokens operationTokenType = ConditionalHelper.newTernaryOperation(
                        isBinaryOperator, () -> currentTokenType, () -> MathTokens.TIMES);
                MathBinaryOperator operator = MathTokenHelper.getBinaryOperator(operationTokenType);
                ListHelper.add(operators, operator);
                firstUnaryExpressionWrapper.setValue(this.parseUnaryExpression());
            });
            // Check iteration condition
            return postTermOperationTokenTypeIsSameDepth || postTermOperationTokenTypeIsDeeperDepth;
        });
        return this.compileMultiTermExpression(expressions, operators);
    }

    private MathExpression parseUnaryExpression() {
        if (this.currentTokenTypeIsEqualTo(MathTokens.MINUS)) {
            this.expect(MathTokens.MINUS);
            return MathUnaryExpression.newInstance(this.parseUnitaryExpression(),
                    MathNegativeOperator.newInstance(), true);
        }
        return this.parseUnitaryExpression();
    }

    private MathExpression parseUnitaryExpression() {
        // Handle Parentheses
        if (this.currentTokenTypeIsEqualTo(MathTokens.LEFT_PARENTHESES)) {
            this.expect(MathTokens.LEFT_PARENTHESES);
            MathExpression expression = this.parseExpression();
            this.expect(MathTokens.RIGHT_PARENTHESES);
            return MathParenthesesExpression.newInstance(expression);
        }
        // Handle Brackets
        if (this.currentTokenTypeIsEqualTo(MathTokens.LEFT_BRACKET)) {
            this.expect(MathTokens.LEFT_BRACKET);
            MathExpression expression = this.parseExpression();
            this.expect(MathTokens.RIGHT_BRACKET);
            return MathBracketsExpression.newInstance(expression);
        }
        // Handle Braces
        if (this.currentTokenTypeIsEqualTo(MathTokens.LEFT_BRACE)) {
            this.expect(MathTokens.LEFT_BRACE);
            MathExpression expression = this.parseExpression();
            this.expect(MathTokens.RIGHT_BRACE);
            return MathBracesExpression.newInstance(expression);
        }
        // Handle Number Literal
        if (this.currentTokenTypeIsEqualTo(MathTokens.NUMBER_LITERAL)) {
            String literalText = this.popCurrentTokenText();
            boolean isImaginary = literalText.contains("i");
            String rawComponentText = StringDeleterHelper.deleteAllInstances(literalText, 'i');
            String componentText = ConditionalHelper.ifReturnElse(
                    rawComponentText.equals("+") || rawComponentText.equals("-")
                            || rawComponentText.isEmpty(),
                    rawComponentText + "1", rawComponentText);
            double component = Double.parseDouble(componentText);
            double a = ConditionalHelper.ifReturnElse(!isImaginary, component, 0.0);
            double b = ConditionalHelper.ifReturnElse(isImaginary, component, 0.0);
            return MathNumberLiteralExpression.newInstance(ComplexNumber.newInstance(a, b));
        }
        // Handle X or Y
        if (this.currentTokenTypeIsEqualTo(MathTokens.X, MathTokens.Y)) {
            return MathVariableExpression.newInstance(this.popCurrentTokenText());
        }
        // Handle PI
        if (this.currentTokenTypeIsEqualTo(MathTokens.PI)) {
            this.popCurrentToken();
            return MathPiLiteralExpression.newInstance();
        }
        // Handle E
        if (this.currentTokenTypeIsEqualTo(MathTokens.E)) {
            this.popCurrentToken();
            return MathELiteralExpression.newInstance();
        }
        // Handle Function
        String functionName = this.getCurrentTokenText();
        MathExpression functionExpression = null;
        this.expect(MathTokens.LN, MathTokens.SIN, MathTokens.COS, MathTokens.TAN, MathTokens.SEC,
                MathTokens.CSC, MathTokens.COT);
        MathExpression exponent = null;
        if (this.currentTokenTypeIsEqualTo(MathTokens.TO_THE_POWER_OF)) {
            this.expect(MathTokens.TO_THE_POWER_OF);
            exponent = this.parseUnitaryExpression();
        }
        this.expect(MathTokens.LEFT_PARENTHESES);
        switch (functionName) {
            case "ln":
                functionExpression = MathNaturalLogFunctionExpression.newInstance(exponent,
                        this.parseExpression());
                break;
            case "sin":
                functionExpression =
                        MathSineFunctionExpression.newInstance(exponent, this.parseExpression());
                break;
            case "cos":
                functionExpression =
                        MathCosineFunctionExpression.newInstance(exponent, this.parseExpression());
                break;
            case "tan":
                functionExpression =
                        MathTangentFunctionExpression.newInstance(exponent, this.parseExpression());
                break;
            case "sec":
                functionExpression =
                        MathSecantFunctionExpression.newInstance(exponent, this.parseExpression());
                break;
            case "csc":
                functionExpression = MathCosecantFunctionExpression.newInstance(exponent,
                        this.parseExpression());
                break;
            case "cot":
                functionExpression = MathCotangentFunctionExpression.newInstance(exponent,
                        this.parseExpression());
                break;
            default:
                throw new IllegalStateException("Unexpected function: " + functionName);
        }
        this.expect(MathTokens.RIGHT_PARENTHESES);
        return functionExpression;
    }

    private MathExpression parseExpression() {
        MathParser subParser = MathParser.newInstance();
        MathExpression subExpression = subParser.parse(this.tokens, this.index);
        this.index = subParser.index;
        return subExpression;
    }

    // Major Methods
    private MathExpression compileMultiTermExpression(List<MathExpression> expressions,
            List<MathBinaryOperator> operators) {
        // Handle singular expression case
        if (expressions.size() == 1) {
            return expressions.get(0);
        }
        // Handle multiple expressions case
        MathExpression firstExpression = expressions.get(0);
        List<MathBinaryOperation> operations = ListHelper.newArrayList();
        ListHelper.forEach(operators,
                (Integer operatorIndex, MathBinaryOperator operator) -> ListHelper.add(operations,
                        MathBinaryOperation.newInstance(operator,
                                expressions.get(operatorIndex + 1))));
        return MathOperationChainExpression.newInstance(firstExpression, operations);
    }

    // Minor Methods
    private int getTokenDepth(MathTokens type) {
        if (MathTokenHelper.isBinaryOperator(type)) {
            return MathTokenHelper.getBinaryOperatorDepth(type);
        }
        return MathTokenHelper.getBinaryOperatorDepth(MathTokens.TIMES);
    }

    private boolean postTermOperationTokenTypeIsTerminating() {
        return this.currentTokenTypeIsEqualTo(MathTokens.RIGHT_BRACE, MathTokens.RIGHT_BRACKET,
                MathTokens.RIGHT_PARENTHESES, MathTokens.END);
    }

    // Private Helper Methods
    private MathToken getCurrentToken() {
        return this.tokens.get(this.index);
    }

    private String getCurrentTokenText() {
        return this.getCurrentToken().getText();
    }

    private MathTokens getCurrentTokenType() {
        return this.getCurrentToken().getType();
    }

    private boolean currentTokenTypeIsEqualTo(MathTokens... types) {
        return this.getMatchingType(types) != null;
    }

    private MathToken popCurrentToken() {
        return this.tokens.get(this.index++);
    }

    private MathTokens popCurrentTokenType() {
        return this.popCurrentToken().getType();
    }

    private String popCurrentTokenText() {
        return this.popCurrentToken().getText();
    }

    private void expect(MathTokens... types) {
        MathTokens currentType = this.getCurrentTokenType();
        if (this.popMatchingType(types) == null) {
            throw new IllegalStateException("Unexpected token type: " + currentType);
        }
    }

    private MathTokens popMatchingType(MathTokens... types) {
        MathTokens matchingType = this.getMatchingType(types);
        ConditionalHelper.ifThen(matchingType != null, this::increment);
        return matchingType;
    }

    private MathTokens getMatchingType(MathTokens... types) {
        Wrapper<MathTokens> typeWrapper = OrdinaryWrapper.newInstance(null);
        ArrayHelper.forEach(types, (Integer i, MathTokens type) -> {
            if (this.getCurrentTokenType() == type) {
                typeWrapper.setValue(type);
            }
        });
        return typeWrapper.getValue();
    }

    private void increment() {
        this.index++;
    }

    // Initialization Methods
    private void reset(List<MathToken> tokens, int index) {
        this.tokens = tokens;
        this.index = index;
    }
}
