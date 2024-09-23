package com.lambda.lambda.app.utility.java;

import com.lambda.lambda.app.utility.java.util.InstanceField;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.util.string.StringList;

/**
 * Utility class for creating java structure classes from short class input
 */
public final class JavaStructureClassMaker extends JavaClassMaker {
    // Instance Fields
    private boolean isMakingSetters;

    // New Instance Method
    public static JavaStructureClassMaker newInstance() {
        return new JavaStructureClassMaker();
    }

    // Constructor Method
    private JavaStructureClassMaker() {
        super();
    }

    // Main Instance Methods
    public StringList make(String inputText, boolean isMakingSetters) {
        this.reset(inputText, isMakingSetters);
        this.collect();
        this.compile();
        return this.getClassLines();
    }

    @Override
    protected void compileClassContent() {
        this.compileInstanceFields();
        this.compileNewInstanceMethod();
        this.compileConstructorMethod();
        this.compileAccessorMethods();
        this.compileMutatorMethods();
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
        this.addClassLines(this.makeConstructorInitializerLines());
        this.addClassLine("\t}");
    }

    private void compileAccessorMethods() {
        this.addClassLine("");
        this.addClassLine("\t// Accessor Methods");
        this.forEachInstanceField((InstanceField field) -> {
            this.addClassLines(this.makeGetterMethodLines(field));
            this.addClassLine("");
        });
        this.removeLastTrailingClassLines(1);
    }

    private void compileMutatorMethods() {
        ConditionalHelper.ifThen(this.isMakingSetters, () -> {
            this.addClassLine("");
            this.addClassLine("\t// Mutator Methods");
            this.forEachInstanceField((InstanceField field) -> {
                this.addClassLines(this.makeSetterMethodLines(field));
                this.addClassLine("");
            });
            this.removeLastTrailingClassLines(1);
        });
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
        line.append(this.getName());
        ConditionalHelper.ifThen(isGeneric, () -> line.append("<>"));
        line.append("(");
        this.forEachInstanceField((InstanceField field) -> line.append(field.getName() + ", "));
        this.removeLastTrailingCharacters(line, 2);
        line.append(");");
        return line.toString();
    }

    private String makeConstructorDeclarationLine() {
        StringBuilder line = StringHelper.newBuilder();
        boolean isAbstract = this.isAbstract();
        line.append("\t");
        line.append(ConditionalHelper.ifReturnElse(isAbstract, "protected ", "private "));
        line.append(this.getName());
        line.append("(");
        this.forEachInstanceField((InstanceField field) -> line
                .append(field.getDataType() + " " + field.getName() + ", "));
        this.removeLastTrailingCharacters(line, 2);
        line.append(") {");
        return line.toString();
    }

    private StringList makeConstructorInitializerLines() {
        StringList lines = StringHelper.newStringList();
        this.forEachInstanceField((InstanceField field) -> {
            ListHelper.add(lines, "\t\tthis." + field.getName() + " = " + field.getName() + ";");
        });
        return lines;
    }

    private StringList makeGetterMethodLines(InstanceField field) {
        StringBuilder linesText = StringHelper.newBuilder();
        String dataType = field.getDataType();
        String name = field.getName();
        boolean isAbstract = this.isAbstract();
        linesText.append("\tpublic ");
        ConditionalHelper.ifThen(isAbstract, () -> linesText.append("final "));
        linesText.append(dataType + " ");
        linesText.append(ConditionalHelper.ifReturnElse(dataType.equals("boolean"), name,
                "get" + StringHelper.capitalizeFirstLetter(name)));
        linesText.append("() {\n");
        linesText.append("\t\treturn this." + name + ";\n");
        linesText.append("\t}");
        return StringHelper.split(linesText.toString(), "[\n]");
    }

    private StringList makeSetterMethodLines(InstanceField field) {
        StringBuilder linesText = StringHelper.newBuilder();
        String dataType = field.getDataType();
        String name = field.getName();
        boolean isAbstract = this.isAbstract();
        linesText.append("\tpublic ");
        ConditionalHelper.ifThen(isAbstract, () -> linesText.append("final "));
        linesText.append("void set" + StringHelper.capitalizeFirstLetter(name));
        linesText.append("(" + dataType + " " + name + ") {\n");
        linesText.append("\t\tthis." + name + " = " + name + ";\n");
        linesText.append("\t}");
        return StringHelper.split(linesText.toString(), "[\n]");
    }

    // Initialization Methods
    private void reset(String inputText, boolean isMakingSetters) {
        super.reset(inputText);
        this.isMakingSetters = isMakingSetters;
    }
}
