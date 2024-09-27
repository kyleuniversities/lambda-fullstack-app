package com.lambda.lambda.common.util.json;

/**
 * Utility structure class for JSON boolean literal object
 */
public final class JsonBooleanLiteralObject implements JsonObject {
    // Instance Fields
    private boolean value;

    // New Instance Method
    public static JsonBooleanLiteralObject newInstance(boolean value) {
        return new JsonBooleanLiteralObject(value);
    }

    // Constructor Method
    private JsonBooleanLiteralObject(boolean value) {
        super();
        this.value = value;
    }

    // Accessor Methods
    public boolean getValue() {
        return this.value;
    }

    // Overridden Operant Methods
    @Override
    public String toJsonString(int indent) {
        return this.value + "";
    }
}
