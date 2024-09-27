package com.lambda.lambda.common.util.number.evaluator.component;

import com.lambda.lambda.common.helper.json.JsonHelper;
import com.lambda.lambda.common.util.json.JsonMapObject;
import com.lambda.lambda.common.util.json.JsonObject;

public abstract class MathComponent {
    // Constructor Method
    protected MathComponent() {
        super();
    }

    // Abstract Methods
    public abstract String toMathString();

    protected abstract void appendJsonObject(JsonMapObject map);

    // Accessor Methods
    public final String getClassName() {
        return this.getClass().getSimpleName();
    }

    // To String Methods
    @Override
    public final String toString() {
        return this.toMathString();
    }

    // To Json Object Methods
    public final JsonObject toJsonObject() {
        JsonMapObject map = JsonMapObject.newInstance();
        JsonHelper.put(map, "className", this.getClassName());
        this.appendJsonObject(map);
        return map;
    }
}
