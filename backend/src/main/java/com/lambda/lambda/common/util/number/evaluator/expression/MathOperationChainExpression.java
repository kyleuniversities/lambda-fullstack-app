package com.lambda.lambda.common.util.number.evaluator.expression;

import java.util.List;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.json.JsonHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.util.json.JsonMapObject;
import com.lambda.lambda.common.util.number.ComplexNumber;
import com.lambda.lambda.common.util.number.evaluator.operator.MathBinaryOperation;
import com.lambda.lambda.common.util.wrapper.OrdinaryWrapper;
import com.lambda.lambda.common.util.wrapper.Wrapper;

public final class MathOperationChainExpression extends MathExpression {
    // Instance Fields
    private MathExpression term;
    private List<MathBinaryOperation> operations;

    // New Instance Method
    public static MathOperationChainExpression newInstance(MathExpression term,
            List<MathBinaryOperation> operations) {
        return new MathOperationChainExpression(term, operations);
    }

    // Constructor Method
    private MathOperationChainExpression(MathExpression term,
            List<MathBinaryOperation> operations) {
        super();
        this.term = term;
        this.operations = operations;
    }

    // Accessor Methods
    public MathExpression getTerm() {
        return this.term;
    }

    public List<MathBinaryOperation> getOperations() {
        return this.operations;
    }

    // Overridden Math Methods
    @Override
    protected void appendJsonObject(JsonMapObject map) {
        JsonHelper.put(map, "term", this.term.toJsonObject());
        JsonHelper.put(map, "numberOfOperations", this.operations.size());
        ListHelper.forEach(this.operations, (Integer i, MathBinaryOperation operation) -> {
            JsonHelper.put(map, "operation_" + i, operation.toJsonObject());
        });
    }

    @Override
    public String toMathString() {
        StringBuilder mathStringBuilder = StringHelper.newBuilder();
        StringHelper.appendText(mathStringBuilder, this.term.toMathString());
        ListHelper.forEach(this.operations, (Integer i, MathBinaryOperation operation) -> {
            StringHelper.appendText(mathStringBuilder, " " + operation.toMathString());
        });
        return mathStringBuilder.toString();
    }

    @Override
    public ComplexNumber evaluate(MathExpressionArguments arguments) {
        Wrapper<ComplexNumber> value = OrdinaryWrapper.newInstance(this.term.evaluate(arguments));
        ListHelper.forEach(this.operations, (Integer i, MathBinaryOperation operation) -> {
            value.setValue(operation.evaluate(value.getValue(), arguments));
        });
        return value.getValue();
    }
}
