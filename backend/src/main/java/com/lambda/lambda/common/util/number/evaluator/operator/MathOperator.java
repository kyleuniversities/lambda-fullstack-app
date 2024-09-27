package com.lambda.lambda.common.util.number.evaluator.operator;

import com.lambda.lambda.common.helper.json.JsonHelper;
import com.lambda.lambda.common.util.json.JsonMapObject;
import com.lambda.lambda.common.util.number.evaluator.component.MathComponent;

public abstract class MathOperator extends MathComponent {
    // Constructor Method
    protected MathOperator() {
        super();
    }

    // Overridden Math Methods
    @Override
    protected final void appendJsonObject(JsonMapObject map) {
        JsonHelper.put(map, "text", this.toMathString());
    }
}
