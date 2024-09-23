package com.lambda.lambda.app.utility.java;

import com.lambda.lambda.app.utility.java.util.InstanceField;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.util.string.StringList;

/**
 * Utility class for creating java structure classes from short class input
 */
public final class JavaFunctionClassMaker extends JavaClassMaker {
    // New Instance Method
    public static JavaFunctionClassMaker newInstance() {
        return new JavaFunctionClassMaker();
    }

    // Constructor Method
    private JavaFunctionClassMaker() {
        super();
    }

    // Main Instance Methods
    public StringList make(String inputText) {
        this.reset(inputText);
        this.collect();
        this.compile();
        return this.getClassLines();
    }

    @Override
    protected void compileClassContent() {
        this.compileInstanceFields();
        this.compileNewInstanceMethod();
        this.compileConstructorMethod();
        this.compileMainInstanceMethod();
        this.compileResetMethod();
    }

    // Compiling Methods
    private void compileNewInstanceMethod() {
        ConditionalHelper.ifThen(!this.isAbstract(), () -> {
            this.addClassLine("\t// New Instance Method");
            this.addClassLine(this.makeNewInstanceDeclarationLine());
            this.addClassLine(this.makeNewInstanceReturnLine());
            this.addClassLine("\t}");
            this.addClassLine("");
        });
    }

    private void compileConstructorMethod() {
        this.addClassLine("\t// Constructor Method");
        this.addClassLine(this.makeConstructorDeclarationLine());
        this.addClassLine("\t\tsuper();");
        this.addClassLine("\t}");
        this.addClassLine("");
    }

    private void compileMainInstanceMethod() {
        ConditionalHelper.ifThen(!this.isAbstract(), () -> {
            this.addClassLine("\t// Main Instance Method");
            this.addClassLine(this.makeMainInstanceDeclarationLine());
            this.addClassLine(this.makeResetInvocationLine());
            this.addClassLine("\t}");
            this.addClassLine("");
        });
    }

    private void compileResetMethod() {
        this.addClassLine("\t// Initialization Methods");
        this.addClassLine(this.makeResetDeclarationLine());
        this.addClassLines(this.makeResetInitializerLines());
        this.addClassLine("\t}");
    }

    // Private Helper Methods
    private String makeNewInstanceDeclarationLine() {
        StringBuilder line = StringHelper.newBuilder();
        boolean isGeneric = this.isGeneric();
        String parametrizedTypesText = this.getParametrizedTypesText();
        line.append("\tpublic static ");
        ConditionalHelper.ifThen(isGeneric, () -> line.append(parametrizedTypesText + " "));
        line.append(this.getName());
        ConditionalHelper.ifThen(isGeneric, () -> line.append(parametrizedTypesText));
        line.append(" newInstance() {");
        return line.toString();
    }

    private String makeNewInstanceReturnLine() {
        StringBuilder line = StringHelper.newBuilder();
        boolean isGeneric = this.isGeneric();
        line.append("\t\treturn new ");
        line.append(this.getName());
        ConditionalHelper.ifThen(isGeneric, () -> line.append("<>"));
        line.append("();");
        return line.toString();
    }

    private String makeConstructorDeclarationLine() {
        StringBuilder line = StringHelper.newBuilder();
        boolean isAbstract = this.isAbstract();
        line.append("\t");
        line.append(ConditionalHelper.ifReturnElse(isAbstract, "protected ", "private "));
        line.append(this.getName());
        line.append("() {");
        return line.toString();
    }

    private String makeMainInstanceDeclarationLine() {
        StringBuilder line = StringHelper.newBuilder();
        line.append("\tpublic void perform(");
        this.forEachInstanceField((InstanceField field) -> line
                .append(field.getDataType() + " " + field.getName() + ", "));
        this.removeLastTrailingCharacters(line, 2);
        line.append(") {");
        return line.toString();
    }

    private String makeResetInvocationLine() {
        StringBuilder line = StringHelper.newBuilder();
        line.append("\t\tthis.reset(");
        this.forEachInstanceField((InstanceField field) -> line.append(field.getName() + ", "));
        this.removeLastTrailingCharacters(line, 2);
        line.append(");");
        return line.toString();
    }

    private String makeResetDeclarationLine() {
        StringBuilder line = StringHelper.newBuilder();
        boolean isAbstract = this.isAbstract();
        line.append("\t");
        line.append(ConditionalHelper.ifReturnElse(isAbstract, "protected ", "private "));
        line.append("void reset(");
        this.forEachInstanceField((InstanceField field) -> line
                .append(field.getDataType() + " " + field.getName() + ", "));
        this.removeLastTrailingCharacters(line, 2);
        line.append(") {");
        return line.toString();
    }

    private StringList makeResetInitializerLines() {
        StringList lines = StringHelper.newStringList();
        this.forEachInstanceField((InstanceField field) -> {
            ListHelper.add(lines, "\t\tthis." + field.getName() + " = " + field.getName() + ";");
        });
        return lines;
    }
}
