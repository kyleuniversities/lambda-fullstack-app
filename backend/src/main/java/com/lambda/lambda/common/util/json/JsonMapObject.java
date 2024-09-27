package com.lambda.lambda.common.util.json;

import java.util.LinkedHashMap;
import java.util.List;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.MapHelper;
import com.lambda.lambda.common.helper.json.JsonHelper;
import com.lambda.lambda.common.helper.string.StringCodeHelper;
import com.lambda.lambda.common.helper.string.StringDeleterHelper;
import com.lambda.lambda.common.helper.string.StringHelper;

/**
 * Utility structure class for JSON array object
 */
public final class JsonMapObject extends LinkedHashMap<String, JsonObject> implements JsonObject {
    // Serial Id
    private static final long serialVersionUID = -4991229319996645607L;

    // New Instance Method
    public static JsonMapObject newInstance() {
        return new JsonMapObject();
    }

    // Constructor Method
    private JsonMapObject() {
        super();
    }

    // Overridden Operant Methods
    @Override
    public String toJsonString(int indent) {
        StringBuilder textBuilder = StringHelper.newBuilder();
        StringHelper.appendText(textBuilder, "{");
        boolean isEmpty = MapHelper.isEmpty(this);
        ConditionalHelper.ifThen(isEmpty, () -> StringHelper.appendText(textBuilder, "}"));
        ConditionalHelper.ifThen(!isEmpty, () -> {
            int newIndent = indent + 1;
            String indentText = JsonHelper.getIndentedText(indent);
            String newIndentText = JsonHelper.getIndentedText(newIndent);
            List<String> keyList = MapHelper.toKeyList(this);
            ListHelper.forEach(keyList, (Integer i, String key) -> {
                JsonObject value = MapHelper.get(this, key);
                StringHelper.appendText(textBuilder, "\n" + newIndentText);
                StringHelper.appendText(textBuilder, StringCodeHelper.toCode(key));
                StringHelper.appendText(textBuilder, ": ");
                StringHelper.appendText(textBuilder, value.toJsonString(newIndent));
                StringHelper.appendText(textBuilder, ",");
            });
            StringDeleterHelper.deleteLastCharacters(textBuilder, 1);
            StringHelper.appendText(textBuilder, "\n");
            StringHelper.appendText(textBuilder, indentText + "}");
        });
        return textBuilder.toString();
    }
}
