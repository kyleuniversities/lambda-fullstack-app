package com.lambda.lambda.app.util.java;

import com.lambda.lambda.app.util.java.util.InstanceField;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.string.StringHelper;

/**
 * Utility class for making new instance methods
 */
public final class JavaNewInstanceMethodMaker extends JavaInstanceFieldMethodMaker {
    // New Instance Method
    public static JavaNewInstanceMethodMaker newInstance() {
        return new JavaNewInstanceMethodMaker();
    }

    // Constructor Method
    private JavaNewInstanceMethodMaker() {
        super();
    }

    // Main Instance Method
    @Override
    protected void compile() {
        this.addClassLine("\t// New Instance Method");
        this.addClassLine(this.makeNewInstanceDeclarationLine());
        this.addClassLine(this.makeNewInstanceReturnLine());
        this.addClassLine("\t}");
        this.addClassLine("");
    }

    // Major Methods
    private String makeNewInstanceDeclarationLine() {
        StringBuilder line = StringHelper.newBuilder();
        boolean isGeneric = this.isGeneric();
        String parametrizedTypesText = this.getParametrizedTypesText();
        line.append("\tpublic static ");
        ConditionalHelper.ifThen(isGeneric, () -> line.append(parametrizedTypesText + " "));
        line.append(this.getClassName());
        ConditionalHelper.ifThen(isGeneric, () -> line.append(parametrizedTypesText));
        line.append(" newInstance(");
        this.forEachInstanceField((InstanceField field) -> line
                .append(field.getDataType() + " " + field.getName() + ", "));
        this.removeLastTrailingCharacters(line, 2);
        line.append(") {");
        return line.toString();
    }

    private String makeNewInstanceReturnLine() {
        StringBuilder line = StringHelper.newBuilder();
        boolean isGeneric = this.isGeneric();
        line.append("\t\treturn new ");
        line.append(this.getClassName());
        ConditionalHelper.ifThen(isGeneric, () -> line.append("<>"));
        line.append("(");
        this.forEachInstanceField((InstanceField field) -> line.append(field.getName() + ", "));
        this.removeLastTrailingCharacters(line, 2);
        line.append(");");
        return line.toString();
    }
}
