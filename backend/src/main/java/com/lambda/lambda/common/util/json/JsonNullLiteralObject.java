package com.lambda.lambda.common.util.json;

/**
 * Utility structure class for JSON integer literal object
 */
public final class JsonNullLiteralObject implements JsonObject {
    // New Instance Method
    public static JsonNullLiteralObject newInstance() {
        return new JsonNullLiteralObject();
    }

    // Constructor Method
    private JsonNullLiteralObject() {
        super();
    }

    // Overridden Operant Methods
    @Override
    public String toJsonString(int indent) {
        return "null";
    }
}
