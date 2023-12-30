package com.lambda.lambda.app.util.string;

import java.io.File;
import java.util.List;
import java.util.function.BiConsumer;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.MapHelper;
import com.lambda.lambda.common.helper.file.FileHelper;
import com.lambda.lambda.common.helper.file.FilePathHelper;
import com.lambda.lambda.common.helper.string.StringCodeHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.helper.string.StringTrimmerHelper;
import com.lambda.lambda.common.util.string.StringList;
import com.lambda.lambda.common.util.string.StringMap;
import com.lambda.lambda.common.util.string.StringMapMap;

public final class StringSerialReplacer {
    // Instance Fields
    private String target;
    private int replacementsPerInstance;
    private StringList replacements;
    private String text;
    private StringBuilder replaced;
    private int replacementIndex;
    private int numberOfReplacementsLeft;
    private int i;

    // New Instance Method
    public static StringSerialReplacer newInstance() {
        return new StringSerialReplacer();
    }

    // Constructor Method
    private StringSerialReplacer() {
        super();
    }

    // Main Instance Method
    public String replace(String target, int replacementsPerInstance, List<String> replacements,
            String text) {
        this.reset(target, replacementsPerInstance, replacements, text);
        this.forEachTextCharacter((Integer i, Character ch) -> {
            boolean targetIsDetected = StringHelper.substringEquals(this.text, this.target, i);
            ConditionalHelper.ifThen(targetIsDetected, () -> {
                String replacement = this.replacements.get(this.replacementIndex);
                this.replaced.append(replacement);
                this.forwardReplacementIndexing();
                this.i += this.target.length() - 1;
            });
            ConditionalHelper.ifThen(!targetIsDetected, () -> this.replaced.append(ch));
        });
        return this.replaced.toString();
    }

    // Private Helper Methods
    private void forwardReplacementIndexing() {
        this.numberOfReplacementsLeft--;
        if (this.numberOfReplacementsLeft == 0) {
            this.replacementIndex = (this.replacementIndex + 1) % this.replacements.size();
            this.numberOfReplacementsLeft = this.replacementsPerInstance;
        }
    }

    // Iteration Methods
    private void forEachTextCharacter(BiConsumer<Integer, Character> action) {
        ConditionalHelper.whileLoop(() -> this.i < this.text.length(), () -> {
            action.accept(this.i, this.text.charAt(this.i));
            this.i++;
        });
    }

    // Initialization Methods
    private void reset(String target, int replacementsPerInstance, List<String> replacements,
            String text) {
        this.target = target;
        this.replacementsPerInstance = replacementsPerInstance;
        this.replacements = StringList.newInstance(replacements);
        this.text = text;
        this.replaced = StringHelper.newBuilder();
        this.numberOfReplacementsLeft = this.replacementsPerInstance;
        this.i = 0;
    }
}
