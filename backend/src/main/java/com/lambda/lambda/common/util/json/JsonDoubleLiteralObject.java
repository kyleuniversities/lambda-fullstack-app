package com.lambda.lambda.common.util.json;

/**
 * Utility structure class for JSON double literal object
 */
public final class JsonDoubleLiteralObject implements JsonObject {
    // Instance Fields
    private double value;

    // New Instance Method
    public static JsonDoubleLiteralObject newInstance(double value) {
        return new JsonDoubleLiteralObject(value);
    }

    // Constructor Method
    private JsonDoubleLiteralObject(double value) {
        super();
        this.value = value;
    }

    // Accessor Methods
    public double getValue() {
        return this.value;
    }

    // Overridden Operant Methods
    @Override
    public String toJsonString(int indent) {
        return this.value + "";
    }
}
