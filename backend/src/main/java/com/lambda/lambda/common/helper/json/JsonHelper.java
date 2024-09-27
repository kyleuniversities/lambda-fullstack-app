package com.lambda.lambda.common.helper.json;

import java.util.List;
import java.util.Map;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.MapHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.util.json.JsonArrayObject;
import com.lambda.lambda.common.util.json.JsonBooleanLiteralObject;
import com.lambda.lambda.common.util.json.JsonDoubleLiteralObject;
import com.lambda.lambda.common.util.json.JsonIntegerLiteralObject;
import com.lambda.lambda.common.util.json.JsonMapObject;
import com.lambda.lambda.common.util.json.JsonNullLiteralObject;
import com.lambda.lambda.common.util.json.JsonObject;
import com.lambda.lambda.common.util.json.JsonStringLiteralObject;
import com.lambda.lambda.common.util.string.StringList;

/**
 * Helper class for Json Operations
 */
public final class JsonHelper {
    /**
     * Class Fields
     */
    private static final Map<Integer, String> INDENTED_TEXT_MAP = MapHelper.newLinkedHashMap();

    /**
     * Get Indented Text
     */
    public static String getIndentedText(int indent) {
        return MapHelper.getInitialize(JsonHelper.INDENTED_TEXT_MAP, indent,
                () -> StringHelper.repeatText("  ", indent));
    }

    /**
     * Puts an Boolean entry into a Json Map Object
     */
    public static void put(JsonMapObject map, String key, boolean value) {
        map.put(key, JsonBooleanLiteralObject.newInstance(value));
    }

    /**
     * Puts an Double entry into a Json Map Object
     */
    public static void put(JsonMapObject map, String key, double value) {
        map.put(key, JsonDoubleLiteralObject.newInstance(value));
    }

    /**
     * Puts an Integer entry into a Json Map Object
     */
    public static void put(JsonMapObject map, String key, int value) {
        map.put(key, JsonIntegerLiteralObject.newInstance(value));
    }

    /**
     * Puts a String entry into a Json Map Object
     */
    public static void put(JsonMapObject map, String key, String value) {
        map.put(key, JsonStringLiteralObject.newInstance(value));
    }

    /**
     * Puts a Json Object entry into a Json Map Object
     */
    public static void put(JsonMapObject map, String key, JsonObject jsonObject) {
        map.put(key, jsonObject);
    }

    /**
     * Puts a Null entry into a Json Map Object
     */
    public static void putNullEntry(JsonMapObject map, String key) {
        map.put(key, JsonNullLiteralObject.newInstance());
    }

    /**
     * Puts a String List entry into a Json Map Object
     */
    public static void putStringList(JsonMapObject map, String key, List<String> list) {
        JsonArrayObject array = JsonArrayObject.newInstance();
        ListHelper.forEach(list, (String string) -> ListHelper.add(array,
                JsonStringLiteralObject.newInstance(string)));
        map.put(key, array);
    }

    /**
     * To Json String List
     */
    public static StringList toStringList(JsonObject object) {
        return StringHelper.split(object.toJsonString(0), "[\n]");
    }

    /**
     * Private Constructor to prevent instantiation
     */
    private JsonHelper() {
        super();
    }
}
