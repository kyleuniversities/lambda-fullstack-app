package com.lambda.lambda.common.util.number.evaluator.util;

import com.lambda.lambda.common.helper.string.StringCodeHelper;

public final class MathToken {
    // Instance Fields
    private MathTokens type;
    private int start;
    private int upTo;
    private int line;
    private String text;

    // New Instance Method
    public static MathToken newInstance(MathTokens type, int start, int upTo, int line,
            String text) {
        return new MathToken(type, start, upTo, line, text);
    }

    // Constructor Method
    private MathToken(MathTokens type, int start, int upTo, int line, String text) {
        super();
        this.type = type;
        this.start = start;
        this.upTo = upTo;
        this.line = line;
        this.text = text;
    }

    // Accessor Methods
    public MathTokens getType() {
        return this.type;
    }

    public int getStart() {
        return this.start;
    }

    public int getUpTo() {
        return this.upTo;
    }

    public int getLine() {
        return this.line;
    }

    public String getText() {
        return this.text;
    }

    // To String Method
    @Override
    public final String toString() {
        return String.format("{ type: %s, line: %d, start: %d, upTo: %d, text: %s }",
                this.type.toString(), this.line, this.start, this.upTo, StringCodeHelper.toCode(
                        this.text.length() < 50 ? this.text : this.text.substring(0, 50) + "..."));
    }
}
