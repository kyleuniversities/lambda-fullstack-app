package com.lambda.lambda.app.utility.java;

import java.util.List;
import com.lambda.lambda.app.utility.java.util.InstanceField;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.helper.string.StringTrimmerHelper;
import com.lambda.lambda.common.util.string.StringList;

/**
 * Utility class for making instance field methods
 */
public abstract class JavaInstanceFieldMethodsMaker {
    // Instance Fields
    private String text;
    private StringList lines;
    private List<InstanceField> instanceFields;
    private StringList methodLines;

    // Constructor Method
    protected JavaInstanceFieldMethodsMaker() {
        super();
    }

    // Abstract Methods
    protected abstract String getMethodsTitle();

    protected abstract void addInstanceFieldMethodLines(String dataType, String name);

    // Main Instance Methods
    public final StringList make(String text) {
        this.reset(text);
        this.makeCompileInstanceFields();
        this.makeCompileMethodLines();
        return StringHelper.copyStringList(this.methodLines);
    }

    public final StringList make(List<InstanceField> instanceFields) {
        this.reset(instanceFields);
        this.makeCompileMethodLines();
        return StringHelper.copyStringList(this.methodLines);
    }

    // Major Methods
    private void makeCompileInstanceFields() {
        ListHelper.forEach(this.lines, (String line) -> {
            String trimmedLine = StringTrimmerHelper.trimLeadingWhiteSpace(line);
            ConditionalHelper.ifThen(StringHelper.isNotEmpty(trimmedLine), () -> {
                int firstSpaceIndex = StringHelper.indexOf(trimmedLine, ' ');
                int lastSpaceIndex = StringHelper.lastIndexOf(trimmedLine, ' ');
                int lastIndex = trimmedLine.length() - 1;
                String dataType = StringTrimmerHelper.trimSurroundingWhiteSpace(
                        trimmedLine.substring(firstSpaceIndex + 1, lastSpaceIndex));
                String variableName = trimmedLine.substring(lastSpaceIndex + 1, lastIndex);
                ListHelper.add(this.instanceFields,
                        InstanceField.newInstance(dataType, variableName));
            });
        });
    }

    private void makeCompileMethodLines() {
        this.addTitularMethodsComment();
        this.compileMethodLines();
        this.removeLastMethodLineIfPopulated();
    }

    // Minor Methods
    private void addTitularMethodsComment() {
        ListHelper.add(this.methodLines, "\t// " + this.getMethodsTitle());
    }

    private void compileMethodLines() {
        ListHelper.forEach(this.instanceFields, (InstanceField field) -> {
            this.addInstanceFieldMethodLines(field.getDataType(), field.getName());
        });
    }

    private void removeLastMethodLineIfPopulated() {
        ConditionalHelper.ifThen(ListHelper.isNotEmpty(this.instanceFields),
                () -> ListHelper.removeLastItems(this.methodLines, 1));
    }

    // Minor Methods
    protected final void addMethodLine(String line) {
        ListHelper.add(this.methodLines, line);
    }

    // Initialization Methods
    private void reset(String text) {
        this.text = text;
        this.lines = StringHelper.split(this.text, "\n");
        this.reset(ListHelper.newArrayList());
    }

    private void reset(List<InstanceField> instanceFields) {
        this.instanceFields = ListHelper.newArrayList();
        this.methodLines = StringHelper.newStringList();
    }
}
