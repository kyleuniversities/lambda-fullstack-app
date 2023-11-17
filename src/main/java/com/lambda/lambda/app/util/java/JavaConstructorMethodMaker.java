package com.lambda.lambda.app.util.java;

import com.lambda.lambda.app.util.java.util.InstanceField;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.util.string.StringList;

/**
 * Utility class for making constructor methods
 */
public final class JavaConstructorMethodMaker extends JavaInstanceFieldMethodMaker {
    // New Instance Method
    public static JavaConstructorMethodMaker newInstance() {
        return new JavaConstructorMethodMaker();
    }

    // Constructor Method
    private JavaConstructorMethodMaker() {
        super();
    }

    // Main Instance Method
    @Override
    protected void compile() {
        this.addClassLine("\t// Constructor Method");
        this.addClassLine(this.makeConstructorDeclarationLine());
        this.addClassLine("\t\tsuper();");
        this.addClassLines(this.makeConstructorInitializerLines());
        this.addClassLine("\t}");
    }

    // Major Methods

    private String makeConstructorDeclarationLine() {
        StringBuilder line = StringHelper.newBuilder();
        boolean isAbstract = this.isAbstract();
        line.append("\t");
        line.append(ConditionalHelper.ifReturnElse(isAbstract, "protected ", "private "));
        line.append(this.getClassName());
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
}
