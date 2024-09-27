package com.lambda.lambda.common.util.json;

import com.lambda.lambda.common.helper.string.StringCodeHelper;

/**
 * Utility structure class for JSON integer literal object
 */
public final class JsonStringLiteralObject implements JsonObject {
    // Instance Fields
    private String text;

    // New Instance Method
    public static JsonStringLiteralObject newInstance(String text) {
        return new JsonStringLiteralObject(text);
    }

    // Constructor Method
    private JsonStringLiteralObject(String text) {
        super();
        this.text = text + "";
    }

    // Accessor Methods
    public String getText() {
        return this.text;
    }

    // Overridden Operant Methods
    @Override
    public String toJsonString(int indent) {
        return StringCodeHelper.toCode(this.text);
    }
}
