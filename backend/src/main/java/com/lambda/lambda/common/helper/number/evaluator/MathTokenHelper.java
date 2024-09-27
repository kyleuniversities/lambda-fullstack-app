package com.lambda.lambda.common.helper.number.evaluator;

import java.util.List;
import java.util.Map;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.MapHelper;
import com.lambda.lambda.common.util.map.BiMap;
import com.lambda.lambda.common.util.number.evaluator.operator.MathAddOperator;
import com.lambda.lambda.common.util.number.evaluator.operator.MathBinaryOperator;
import com.lambda.lambda.common.util.number.evaluator.operator.MathDivideOperator;
import com.lambda.lambda.common.util.number.evaluator.operator.MathExponentOperator;
import com.lambda.lambda.common.util.number.evaluator.operator.MathMultiplyOperator;
import com.lambda.lambda.common.util.number.evaluator.operator.MathSubtractOperator;
import com.lambda.lambda.common.util.number.evaluator.util.MathTokens;

/**
 * Helper class for Math Token Operations
 */
public final class MathTokenHelper {
    /**
     * Class Fields
     */
    private static final BiMap<MathTokens, String> SYMBOL_TYPE_MAP =
            MathTokenHelper.getSymbolTypeMap();
    private static final BiMap<MathTokens, String> KEYWORD_TYPE_MAP =
            MathTokenHelper.getKeywordTypeMap();
    private static final Map<MathTokens, Integer> BINARY_OPERATOR_DEPTH_MAP =
            MathTokenHelper.getBinaryOperatorDepthMap();
    private static final Map<MathTokens, MathBinaryOperator> BINARY_OPERATOR_MAP =
            MathTokenHelper.getBinaryOperatorMap();

    /**
     * Gets the binary operator depth
     */
    public static MathBinaryOperator getBinaryOperator(MathTokens type) {
        return MathTokenHelper.BINARY_OPERATOR_MAP.get(type);
    }

    /**
     * Gets the binary operator depth
     */
    public static int getBinaryOperatorDepth(MathTokens type) {
        return MathTokenHelper.BINARY_OPERATOR_DEPTH_MAP.get(type);
    }

    /**
     * Gets the binary operator depth map
     */
    public static Map<MathTokens, Integer> getBinaryOperatorDepthMap() {
        Map<MathTokens, Integer> binaryOperatorDepthMap = MapHelper.newLinkedHashMap();
        MapHelper.put(binaryOperatorDepthMap, MathTokens.PLUS, 0);
        MapHelper.put(binaryOperatorDepthMap, MathTokens.MINUS, 0);
        MapHelper.put(binaryOperatorDepthMap, MathTokens.TIMES, 1);
        MapHelper.put(binaryOperatorDepthMap, MathTokens.DIVIDED_BY, 1);
        MapHelper.put(binaryOperatorDepthMap, MathTokens.TO_THE_POWER_OF, 2);
        return binaryOperatorDepthMap;
    }

    /**
     * Gets the binary operator map
     */
    public static Map<MathTokens, MathBinaryOperator> getBinaryOperatorMap() {
        Map<MathTokens, MathBinaryOperator> binaryOperatorMap = MapHelper.newLinkedHashMap();
        MapHelper.put(binaryOperatorMap, MathTokens.PLUS, MathAddOperator.newInstance());
        MapHelper.put(binaryOperatorMap, MathTokens.MINUS, MathSubtractOperator.newInstance());
        MapHelper.put(binaryOperatorMap, MathTokens.TIMES, MathMultiplyOperator.newInstance());
        MapHelper.put(binaryOperatorMap, MathTokens.DIVIDED_BY, MathDivideOperator.newInstance());
        MapHelper.put(binaryOperatorMap, MathTokens.TO_THE_POWER_OF,
                MathExponentOperator.newInstance());
        return binaryOperatorMap;
    }

    /**
     * Gets the keyword type
     */
    public static MathTokens getKeywordType(String text) {
        return MathTokenHelper.KEYWORD_TYPE_MAP.getReverse(text);
    }

    /**
     * Gets the symbol string list
     */
    public static List<String> getKeywordStringList() {
        return MapHelper.toValueList(MathTokenHelper.getKeywordTypeMap());
    }

    /**
     * Gets the keyword token type list
     */
    public static List<MathTokens> getKeywordTypeList() {
        List<MathTokens> keywordTypeList = ListHelper.newArrayList();
        ListHelper.add(keywordTypeList, MathTokens.X);
        ListHelper.add(keywordTypeList, MathTokens.Y);
        ListHelper.add(keywordTypeList, MathTokens.PI);
        ListHelper.add(keywordTypeList, MathTokens.E);
        ListHelper.add(keywordTypeList, MathTokens.LN);
        ListHelper.add(keywordTypeList, MathTokens.COS);
        ListHelper.add(keywordTypeList, MathTokens.SIN);
        ListHelper.add(keywordTypeList, MathTokens.TAN);
        ListHelper.add(keywordTypeList, MathTokens.SEC);
        ListHelper.add(keywordTypeList, MathTokens.CSC);
        ListHelper.add(keywordTypeList, MathTokens.COT);
        return keywordTypeList;
    }

    /**
     * Gets the keyword token type map
     */
    public static BiMap<MathTokens, String> getKeywordTypeMap() {
        BiMap<MathTokens, String> keywordTypeMap = MapHelper.newBiMap();
        List<MathTokens> keywordTypeList = MathTokenHelper.getKeywordTypeList();
        ListHelper.forEach(keywordTypeList, (MathTokens type) -> MapHelper.put(keywordTypeMap, type,
                type.toString().toLowerCase()));
        return keywordTypeMap;
    }

    /**
     * Gets the symbol string list
     */
    public static List<String> getSymbolStringList() {
        return MapHelper.toValueList(MathTokenHelper.getSymbolTypeMap());
    }

    /**
     * Gets the symbol type
     */
    public static MathTokens getSymbolType(String text) {
        return MathTokenHelper.SYMBOL_TYPE_MAP.getReverse(text);
    }

    /**
     * Gets the symbol type
     */
    public static String getSymbolText(MathTokens type) {
        return MathTokenHelper.SYMBOL_TYPE_MAP.get(type);
    }

    /**
     * Gets the symbol token type map
     */
    public static BiMap<MathTokens, String> getSymbolTypeMap() {
        BiMap<MathTokens, String> symbolTypeMap = MapHelper.newBiMap();
        MapHelper.put(symbolTypeMap, MathTokens.PLUS, "+");
        MapHelper.put(symbolTypeMap, MathTokens.MINUS, "-");
        MapHelper.put(symbolTypeMap, MathTokens.TIMES, "*");
        MapHelper.put(symbolTypeMap, MathTokens.DIVIDED_BY, "/");
        MapHelper.put(symbolTypeMap, MathTokens.TO_THE_POWER_OF, "^");
        MapHelper.put(symbolTypeMap, MathTokens.LEFT_PARENTHESES, "(");
        MapHelper.put(symbolTypeMap, MathTokens.RIGHT_PARENTHESES, ")");
        MapHelper.put(symbolTypeMap, MathTokens.LEFT_BRACKET, "[");
        MapHelper.put(symbolTypeMap, MathTokens.RIGHT_BRACKET, "]");
        MapHelper.put(symbolTypeMap, MathTokens.LEFT_BRACE, "{");
        MapHelper.put(symbolTypeMap, MathTokens.RIGHT_BRACE, "}");
        return symbolTypeMap;
    }

    /**
     * Contains the binary operator
     */
    public static boolean isBinaryOperator(MathTokens type) {
        return MathTokenHelper.BINARY_OPERATOR_MAP.containsKey(type);
    }

    /**
     * Contains the keyword text
     */
    public static boolean isKeywordString(String text) {
        return MathTokenHelper.KEYWORD_TYPE_MAP.containsReverseKey(text);
    }

    /**
     * Contains the keyword text
     */
    public static boolean isSymbolString(String text) {
        return MathTokenHelper.SYMBOL_TYPE_MAP.containsReverseKey(text);
    }

    /**
     * Private Constructor to prevent instantiation
     */
    private MathTokenHelper() {
        super();
    }
}
