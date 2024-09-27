package com.lambda.lambda.app.utility.string;

import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.util.string.StringList;
import com.lambda.lambda.common.util.wrapper.IntegerWrapper;

public final class StringTabifier {
    // New Instance Method
    public static StringTabifier newInstance() {
        return new StringTabifier();
    }

    // Constructor Method
    private StringTabifier() {
        super();
    }

    // Main Instance Method
    public String tabify(String text, int indent) {
        StringList tabified = StringList.newInstance();
        StringList lines =
                StringHelper.split(text.replaceAll("\t", "    ").replaceAll("    ", "\t"), "[\n]+");
        IntegerWrapper minimumTabAmount = IntegerWrapper.newInstance(Integer.MAX_VALUE);
        ListHelper.forEach(lines, (String line) -> {
            IntegerWrapper tabAmount = IntegerWrapper.newInstance();
            StringHelper.forEach(line, (Character ch) -> {
                if (ch == '\t') {
                    tabAmount.increment();
                    return true;
                }
                return false;
            });
            ConditionalHelper.ifThen(tabAmount.isLessThan(minimumTabAmount.getValue()),
                    () -> minimumTabAmount.setValue(tabAmount.getValue()));
        });
        ListHelper.forEach(lines,
                (String line) -> ListHelper.add(tabified, StringHelper.repeatText("\t", indent)
                        + line.substring(minimumTabAmount.getValue())));
        return tabified.toString();
    }
}
