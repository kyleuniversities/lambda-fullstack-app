package com.lambda.lambda.common.util.number.evaluator.lexer;

import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.IterationHelper;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.number.IntegerHelper;
import com.lambda.lambda.common.helper.number.evaluator.MathTokenHelper;
import com.lambda.lambda.common.helper.string.CharacterHelper;
import com.lambda.lambda.common.helper.string.StringDeleterHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.helper.string.StringReplacementHelper;
import com.lambda.lambda.common.util.number.evaluator.util.MathToken;
import com.lambda.lambda.common.util.number.evaluator.util.MathTokens;

public final class MathLexer implements Iterator<MathToken> {
    // Instance Fields
    private String text;
    private int index;
    private List<MathToken> tokens;

    // New Instance Method
    public static MathLexer newInstance() {
        return new MathLexer();
    }

    // Constructor Method
    private MathLexer() {
        super();
    }

    // Main Instance Method
    public List<MathToken> tokenize(String text) {
        this.reset(text);
        ConditionalHelper.whileLoopUntilFalse(() -> {
            ListHelper.add(this.tokens, this.next());
            return this.hasNext();
        });
        ListHelper.add(this.tokens, this.newToken(MathTokens.END, ""));
        return ListHelper.clone(this.tokens);
    }

    @Override
    public boolean hasNext() {
        return this.index < this.text.length();
    }

    @Override
    public MathToken next() {
        if (this.isI()) {
            return this.lexI();
        }
        if (this.isNumberLiteral()) {
            return this.lexNumberLiteral();
        }
        if (this.isVariable()) {
            return this.lexVariable();
        }
        if (this.isPi()) {
            return this.lexPi();
        }
        if (this.isE()) {
            return this.lexE();
        }
        if (this.isSymbol()) {
            return this.lexSymbol();
        }
        String keywordText = this.nextWordText();
        return this.lexFunctionName(keywordText);
    }

    // Conditional Methods
    private boolean isI() {
        return this.substringEquals("i");
    }

    private boolean isNumberLiteral() {
        return this.isDigit() || this.charIsEqualTo('i') || (this.hasNextChar()
                && (IntegerHelper.isDigit(this.getNextChar()) && (this.charIsEqualTo('-', '.'))));
    }

    private boolean isVariable() {
        return this.charIsEqualTo('x', 'y');
    }

    private boolean isPi() {
        return this.substringEquals("\\pi");
    }

    private boolean isE() {
        return this.substringEquals("e");
    }

    private boolean isSymbol() {
        return MathTokenHelper.isSymbolString(this.getCharString());
    }

    // Action Methods
    private MathToken lexI() {
        return this.newToken(MathTokens.NUMBER_LITERAL, this.popChar());
    }

    private MathToken lexNumberLiteral() {
        return this.lexIteratedText(1, MathTokens.NUMBER_LITERAL,
                this::isIterableNumberLiteralCharacter);
    }

    private MathToken lexVariable() {
        return this.newToken(MathTokenHelper.getKeywordType(this.getCharString()),
                this.popCharString());
    }

    private MathToken lexPi() {
        return this.newToken(MathTokens.PI,
                this.popChar() + "" + this.popChar() + "" + this.popChar());
    }

    private MathToken lexE() {
        return this.newToken(MathTokens.E, this.popChar());
    }

    private MathToken lexSymbol() {
        return this.newToken(MathTokenHelper.getSymbolType(this.getCharString()), this.popChar());
    }

    private MathToken lexFunctionName(String keywordText) {
        return this.newToken(MathTokenHelper.getKeywordType(keywordText), keywordText);
    }

    private MathToken lexIteratedText(int numberOfInitialPops, MathTokens type,
            Supplier<Boolean> iterationCondition) {
        StringBuilder textBuilder = StringHelper.newBuilder();
        IterationHelper.forEach(numberOfInitialPops,
                () -> StringHelper.appendText(textBuilder, this.popChar()));
        IterationHelper.whileLoop(iterationCondition,
                () -> StringHelper.appendText(textBuilder, this.popChar()));
        return this.newToken(type, textBuilder.toString());
    }

    // Iteration Methods
    private boolean isIterableNumberLiteralCharacter() {
        return this.hasChar() && (this.isDigit() || this.charIsEqualTo('.'));
    }

    private boolean isIterableIdentifierCharacter() {
        return this.hasChar() && (CharacterHelper.isAlphabetical(this.getChar()));
    }

    // Private Helper Methods
    protected String nextWordText() {
        StringBuilder textBuilder = StringHelper.newBuilder();
        IterationHelper.forEach(1, () -> StringHelper.appendText(textBuilder, this.popChar()));
        IterationHelper.whileLoop(this::isIterableIdentifierCharacter,
                () -> StringHelper.appendText(textBuilder, this.popChar()));
        return textBuilder.toString();
    }

    private MathToken newToken(MathTokens type, char ch) {
        return this.newToken(type, ch + "");
    }

    private MathToken newToken(MathTokens type, String text) {
        int startIndex = this.index - text.length();
        return this.newToken(type, startIndex, this.index, text);
    }

    private MathToken newTokenWithIncrement(MathTokens type, String text) {
        IterationHelper.forEach(text.length(), () -> this.increment());
        return this.newToken(type, text);
    }

    private MathToken newToken(MathTokens type, int startIndex, int upToIndex, String text) {
        return MathToken.newInstance(type, startIndex, upToIndex, 0, text);
    }

    private boolean substringEquals(String target) {
        return StringHelper.substringEquals(this.text, target, this.index);
    }

    private char getChar() {
        return this.text.charAt(this.index);
    }

    private String getCharString() {
        return this.text.charAt(this.index) + "";
    }

    private char getNextChar() {
        return this.text.charAt(this.index + 1);
    }

    private char popChar() {
        return this.text.charAt(this.index++);
    }

    private String popCharString() {
        return this.popChar() + "";
    }

    private void increment() {
        this.index++;
    }

    private boolean hasChar() {
        return this.hasNext();
    }

    private boolean hasNextChar() {
        return this.index + 1 > this.text.length();
    }

    private boolean charIsEqualTo(char... targets) {
        return ListHelper.isTrueForAny(ListHelper.toCharacterList(targets),
                (Character ch) -> ch == this.getChar());
    }

    protected boolean isDigit() {
        return IntegerHelper.isDigit(this.getChar());
    }

    // Initialization Methods
    private void reset(String text) {
        this.text = StringReplacementHelper.replace(StringDeleterHelper
                .deleteAllInstances(text, ' ').toLowerCase().replaceAll("\\\\pi", "\u0C30")
                .replaceAll("pi", "\u0C30").replaceAll("π", "\\\\pi"), "\u0C30", "\\pi");
        this.index = 0;
        this.tokens = ListHelper.newArrayList();
    }
}
