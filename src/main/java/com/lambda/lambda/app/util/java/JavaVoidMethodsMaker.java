package com.lambda.lambda.app.util.java;

import com.lambda.lambda.app.util.file.FileTextReplacer;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.TimeHelper;
import com.lambda.lambda.common.helper.file.FileHelper;
import com.lambda.lambda.common.helper.file.FilePathHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.helper.string.StringTrimmerHelper;
import com.lambda.lambda.common.util.string.StringList;
import com.lambda.lambda.common.util.wrapper.StringWrapper;

/**
 * Utility class for making void methods
 */
public final class JavaVoidMethodsMaker {
    // Instance Fields
    private String text;
    private StringList lines;
    private StringList methodNames;
    private StringList methodLines;

    // New Instance Method
    public static JavaVoidMethodsMaker newInstance() {
        return new JavaVoidMethodsMaker();
    }

    // Constructor Method
    private JavaVoidMethodsMaker() {
        super();
    }

    // Main Instance Method
    public StringList make(String text) {
        this.reset(text);
        this.makeCompileMethodNames();
        this.makeCompileMethodLines();
        return StringHelper.copyStringList(this.methodLines);
    }

    // Major Methods
    private void makeCompileMethodNames() {
        ListHelper.forEach(this.lines, (String line) -> {
            String trimmedLine = StringTrimmerHelper.trimLeadingWhiteSpace(line);
            ConditionalHelper.ifThen(StringHelper.isNotEmpty(trimmedLine), () -> {
                int start = StringHelper.indexOf(line, '.') + 1;
                int upTo = StringHelper.indexOf(line, '(');
                ListHelper.add(this.methodNames, line.substring(start, upTo));
            });
        });
    }

    private void makeCompileMethodLines() {
        this.compileMethodLines();
        this.removeLastMethodLineIfPopulated();
    }

    // Minor Methods
    private void compileMethodLines() {
        ListHelper.forEach(this.methodNames, (String methodName) -> {
            ListHelper.add(this.methodLines, "\tprivate void " + methodName + "() {");
            ListHelper.add(this.methodLines, "\t\t// TODO");
            ListHelper.add(this.methodLines, "\t}");
            ListHelper.add(this.methodLines, "");
        });
    }

    private void removeLastMethodLineIfPopulated() {
        ConditionalHelper.ifThen(ListHelper.isNotEmpty(this.methodNames),
                () -> ListHelper.removeLastItems(this.methodLines, 1));
    }

    // Initialization Methods
    private void reset(String text) {
        this.text = text;
        this.lines = StringHelper.split(text, "\n");
        this.methodNames = StringHelper.newStringList();
        this.methodLines = StringHelper.newStringList();
    }
}
