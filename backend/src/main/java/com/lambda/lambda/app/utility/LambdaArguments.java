package com.lambda.lambda.app.utility;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import com.lambda.lambda.app.helper.CodeHelper;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.MapHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.helper.string.StringTrimmerHelper;
import com.lambda.lambda.common.util.string.StringList;

public final class LambdaArguments {
    // Instance Fields
    private List<String> arguments;
    private String bodyText;
    private Map<String, String> environment;

    // New Instance Method
    public static LambdaArguments newInstance(List<String> arguments, String bodyText,
            Map<String, String> environment) {
        return new LambdaArguments(arguments, bodyText, environment);
    }

    // Constructor Method
    private LambdaArguments(List<String> arguments, String bodyText,
            Map<String, String> environment) {
        super();
        this.arguments = CodeHelper.toText(arguments);
        this.bodyText = CodeHelper.toText(bodyText);
        this.environment = CodeHelper.toText(environment);
    }

    // Accessor Methods
    public StringList getArgumentsClone() {
        return StringList.newInstance(this.arguments);
    }

    public int getArgumentsSize() {
        return this.arguments.size();
    }

    public String getArgument(int index) {
        return this.arguments.get(index);
    }

    public int getIntegerArgument(int index) {
        return Integer.parseInt(this.getArgument(index));
    }

    public double getDoubleArgument(int index) {
        return Double.parseDouble(this.getArgument(index));
    }

    public boolean getBooleanArgument(int index) {
        return Boolean.parseBoolean(this.getArgument(index));
    }

    public boolean argumentsContain(String text) {
        return this.arguments.contains(text);
    }

    public String getBodyText() {
        return this.bodyText;
    }

    public StringList getBodyLines() {
        return StringHelper.split(this.bodyText, "[\n]");
    }

    public StringList getNonEmptyBodyLines() {
        return StringList.newInstance(ListHelper.filterClone(this.getBodyLines(),
                (String line) -> !StringTrimmerHelper.trimLeadingWhiteSpace(line).isEmpty()));
    }

    public int getEnvironmentSize() {
        return this.environment.size();
    }

    public String getEnvironmentValue(String key) {
        return this.environment.get(key);
    }

    public int getEnvironmentIntegerValue(String key) {
        return Integer.parseInt(this.getEnvironmentValue(key));
    }

    public double getEnvironmentDoubleValue(String key) {
        return Double.parseDouble(this.getEnvironmentValue(key));
    }

    public boolean getEnvironmentBooleanValue(String key) {
        return Boolean.parseBoolean(this.getEnvironmentValue(key));
    }

    public boolean environmentContainsKey(String key) {
        return this.environment.containsKey(key);
    }

    // Iteration Methods
    public void forEachArgument(Consumer<String> action) {
        ListHelper.forEach(this.arguments, action);
    }

    public void forEachArgument(BiConsumer<Integer, String> action) {
        ListHelper.forEach(this.arguments, action);
    }

    public void forEachBodyLine(Consumer<String> action) {
        ListHelper.forEach(this.getBodyLines(), action);
    }

    public void forEachEnvironmentEntry(BiConsumer<String, String> action) {
        MapHelper.forEach(this.environment, action);
    }

    // To String Method
    @Override
    public String toString() {
        StringBuilder argumentsText = StringHelper.newBuilder();
        argumentsText.append("<<arguments>>\n" + this.arguments + "\n");
        argumentsText.append(StringHelper.repeatText("-", 30));
        argumentsText.append("<<bodyText>>\n" + this.bodyText + "\n");
        argumentsText.append(StringHelper.repeatText("-", 30));
        argumentsText.append("<<environment>>\n" + this.environment + "\n");
        return argumentsText.toString();
    }
}
