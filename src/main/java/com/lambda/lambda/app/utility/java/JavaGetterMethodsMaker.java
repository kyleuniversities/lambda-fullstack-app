package com.lambda.lambda.app.utility.java;

import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.string.StringHelper;

/**
 * Utility class for making getter methods
 */
public final class JavaGetterMethodsMaker extends JavaInstanceFieldMethodsMaker {
    // New Instance Method
    public static JavaGetterMethodsMaker newInstance() {
        return new JavaGetterMethodsMaker();
    }

    // Constructor Method
    private JavaGetterMethodsMaker() {
        super();
    }

    // Main Instance Methods
    @Override
    protected String getMethodsTitle() {
        return "Accessor Methods";
    }

    @Override
    protected void addInstanceFieldMethodLines(String dataType, String name) {
        boolean isBoolean = dataType.equals("boolean");
        String methodName = ConditionalHelper.newTernaryOperation(isBoolean, () -> name,
                () -> "get" + StringHelper.capitalizeFirstLetter(name));
        this.addMethodLine("\tpublic " + dataType + " " + methodName + "() {");
        this.addMethodLine("\t\treturn this." + name + ";");
        this.addMethodLine("\t}");
        this.addMethodLine("");
    }
}
