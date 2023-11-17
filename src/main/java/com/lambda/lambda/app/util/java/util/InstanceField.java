package com.lambda.lambda.app.util.java.util;

/**
 * Utility structure for instance fields
 */
public final class InstanceField {
    // Instance Fields
    private String dataType;
    private String name;

    // New Instance Method
    public static InstanceField newInstance(String dataType, String name) {
        return new InstanceField(dataType, name);
    }

    // Constructor Method
    private InstanceField(String dataType, String name) {
        super();
        this.dataType = dataType;
        this.name = name;
    }

    // Accessor Methods
    public String getDataType() {
        return this.dataType;
    }

    public String getName() {
        return this.name;
    }
}
