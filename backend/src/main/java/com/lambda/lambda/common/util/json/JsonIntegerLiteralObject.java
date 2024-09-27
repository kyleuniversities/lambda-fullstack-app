package com.lambda.lambda.common.util.json;

/**
 * Utility structure class for JSON integer literal object
 */
public final class JsonIntegerLiteralObject implements JsonObject {
    // Instance Fields
    private int value;

    // New Instance Method
    public static JsonIntegerLiteralObject newInstance(int value) {
        return new JsonIntegerLiteralObject(value);
    }

    // Constructor Method
    private JsonIntegerLiteralObject(int value) {
        super();
        this.value = value;
    }

    // Accessor Methods
    public int getValue() {
        return this.value;
    }

    // Overridden Operant Methods
    @Override
    public String toJsonString(int indent) {
        return this.value + "";
    }
}
