package com.lambda.lambda.app.utility.java;

import com.lambda.lambda.common.helper.string.StringHelper;

/**
 * Utility class for making getter methods
 */
public final class JavaSetterMethodsMaker extends JavaInstanceFieldMethodsMaker {
    // New Instance Method
    public static JavaSetterMethodsMaker newInstance() {
        return new JavaSetterMethodsMaker();
    }

    // Constructor Method
    private JavaSetterMethodsMaker() {
        super();
    }

    // Main Instance Methods
    @Override
    protected String getMethodsTitle() {
        return "Mutator Methods";
    }

    @Override
    protected void addInstanceFieldMethodLines(String dataType, String name) {
        String methodName = "set" + StringHelper.capitalizeFirstLetter(name);
        this.addMethodLine("\tpublic void " + methodName + "(" + dataType + " " + name + ") {");
        this.addMethodLine("\t\tthis." + name + " = " + name + ";");
        this.addMethodLine("\t}");
        this.addMethodLine("");
    }
}
