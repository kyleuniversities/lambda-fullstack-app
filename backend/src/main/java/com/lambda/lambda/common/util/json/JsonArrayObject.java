package com.lambda.lambda.common.util.json;

import java.util.ArrayList;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.json.JsonHelper;
import com.lambda.lambda.common.helper.string.StringDeleterHelper;
import com.lambda.lambda.common.helper.string.StringHelper;

/**
 * Utility structure class for JSON map object
 */
public final class JsonArrayObject extends ArrayList<JsonObject> implements JsonObject {
    // Serial Id
    private static final long serialVersionUID = -4991229319996645605L;

    // New Instance Method
    public static JsonArrayObject newInstance() {
        return new JsonArrayObject();
    }

    // Constructor Method
    private JsonArrayObject() {
        super();
    }

    // Overridden Operant Methods
    @Override
    public String toJsonString(int indent) {
        StringBuilder textBuilder = StringHelper.newBuilder();
        StringHelper.appendText(textBuilder, "[");
        boolean isEmpty = ListHelper.isEmpty(this);
        ConditionalHelper.ifThen(isEmpty, () -> StringHelper.appendText(textBuilder, "]"));
        ConditionalHelper.ifThen(!isEmpty, () -> {
            int newIndent = indent + 1;
            String indentText = JsonHelper.getIndentedText(indent);
            String newIndentText = JsonHelper.getIndentedText(newIndent);
            ListHelper.forEach(this, (Integer i, JsonObject item) -> {
                StringHelper.appendText(textBuilder, "\n" + newIndentText);
                StringHelper.appendText(textBuilder, item.toJsonString(newIndent));
                StringHelper.appendText(textBuilder, ",");
            });
            StringDeleterHelper.deleteLastCharacters(textBuilder, 1);
            StringHelper.appendText(textBuilder, indentText + "]");
        });
        return textBuilder.toString();
    }
}
