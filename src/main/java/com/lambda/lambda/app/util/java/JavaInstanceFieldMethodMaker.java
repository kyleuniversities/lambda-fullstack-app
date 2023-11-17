package com.lambda.lambda.app.util.java;

import java.util.List;
import java.util.function.Consumer;
import com.lambda.lambda.app.util.java.util.InstanceField;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.string.StringDeleterHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.util.string.StringList;

/**
 * Utility class for making instance field methods
 */
public abstract class JavaInstanceFieldMethodMaker {
    // Instance Fields
    private String className;
    private String parametrizedTypesText;
    private List<InstanceField> fields;
    private boolean isAbstract;
    private boolean isGeneric;
    private StringList classLines;

    // Constructor Method
    protected JavaInstanceFieldMethodMaker() {
        super();
    }

    // Abstract Method
    protected abstract void compile();

    // Accessor Methods
    public final String getClassName() {
        return this.className;
    }

    public final boolean isAbstract() {
        return this.isAbstract;
    }

    public final String getParametrizedTypesText() {
        return this.parametrizedTypesText;
    }

    public final List<InstanceField> getFields() {
        return this.fields;
    }

    public final boolean isGeneric() {
        return this.isGeneric;
    }

    // Main Instance Method
    public final StringList make(String className, boolean isAbstract, String parametrizedTypesText,
            List<InstanceField> fields) {
        this.reset(className, isAbstract, parametrizedTypesText, fields);
        this.compile();
        return this.classLines.copy();
    }

    // Protected Helper Methods
    protected final void addClassLine(String line) {
        ListHelper.add(this.classLines, line);
    }

    protected final void addClassLines(List<String> lines) {
        ListHelper.addAll(this.classLines, lines);
    }

    protected final void forEachInstanceField(Consumer<InstanceField> action) {
        ListHelper.forEach(this.fields, action);
    }

    protected final void removeLastTrailingCharacters(StringBuilder line, int length) {
        StringDeleterHelper.deleteLastCharacters(line, length);
    }

    // Initialization Methods
    private void reset(String className, boolean isAbstract, String parametrizedTypesText,
            List<InstanceField> fields) {
        this.className = className;
        this.isAbstract = isAbstract;
        this.parametrizedTypesText = parametrizedTypesText;
        this.fields = fields;
        this.isGeneric = this.parametrizedTypesText != null;
        this.classLines = StringHelper.newStringList();
    }
}
