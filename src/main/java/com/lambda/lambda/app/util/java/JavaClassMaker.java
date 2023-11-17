package com.lambda.lambda.app.util.java;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import com.lambda.lambda.app.util.java.util.InstanceField;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.string.StringDeleterHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.helper.string.StringTrimmerHelper;
import com.lambda.lambda.common.util.string.StringIterator;
import com.lambda.lambda.common.util.string.StringList;
import com.lambda.lambda.common.util.wrapper.BooleanWrapper;

/**
 * Utility class for creating java classes from short class input
 */
abstract class JavaClassMaker {
    // Instance Fields
    private StringList inputLines;
    private int inputLineIndex;
    private StringList classLines;
    private StringList packageLines;
    private StringList importLines;
    private StringList blockCommentLines;
    private StringList lineCommentLines;
    private StringList classDeclarationLines;
    private ClassDeclaration classDeclaration;
    private List<InstanceField> instanceFields;

    // Constructor Method
    protected JavaClassMaker() {
        super();
    }

    // Abstract Methods
    protected abstract void compileClassContent();

    // Main Instance Methods
    protected final void collect() {
        this.collectPackage();
        this.collectImports();
        this.collectBlockCommentLines();
        this.collectLineComments();
        this.collectClassDeclarationLines();
        this.collectClassDeclaration();
        this.collectInstanceFields();
    }

    protected final void compile() {
        this.compilePackage();
        this.compileImports();
        this.compileBlockCommentLines();
        this.compileLineComments();
        this.compileClassDeclarationLines();
        this.compileClassContent();
        this.compileClassBlockEndLine();
    }

    protected final StringList getClassLines() {
        return this.classLines.copy();
    }

    // Collecting Methods
    private void collectPackage() {
        this.collectSingleLineList(this::isPackageLine, this.packageLines);
    }

    private void collectImports() {
        this.collectSingleLineList(this::isImportLine, this.importLines);
    }

    private void collectBlockCommentLines() {
        this.collectMultiLineList(this::isBlockCommentStartLine, this::isBlockCommentEndLine,
                this.blockCommentLines);
    }

    private void collectLineComments() {
        this.collectSingleLineList(this::isLineCommentLine, this.lineCommentLines);
    }

    private void collectClassDeclarationLines() {
        this.collectMultiLineList(this::isClassDeclarationStartLine,
                this::isClassDeclarationEndLine, this.classDeclarationLines);
    }

    private void collectClassDeclaration() {
        StringIterator iterator = StringIterator.newInstance(this.classDeclarationLines.toString());
        boolean isPublic = iterator.popIfStartsWithWithPresenceFallthrough("public ");
        boolean isAbstract = iterator.popIfStartsWithWithPresenceFallthrough("abstract ");
        boolean isFinal = iterator.popIfStartsWithWithPresenceFallthrough("final ");
        iterator.popExpect("class ");
        String name = iterator.popNextWord();
        iterator.popWhiteSpace();
        String parametrizedTypesText = this.collectParametrizedTypesText(iterator);
        ClassDeclaration classDeclaration = ClassDeclaration.newInstance(isPublic, isAbstract,
                isFinal, name, parametrizedTypesText);
        this.classDeclaration = classDeclaration;
    }

    private void collectInstanceFields() {
        this.forEachLine(() -> {
            boolean isInstanceFieldLine = this.isInstanceFieldLine();
            ConditionalHelper.ifThen(isInstanceFieldLine, () -> this.collectInstanceFieldLine());
            return true;
        });
    }

    // Compiling Methods
    private void compilePackage() {
        this.addClassLines(this.packageLines);
        this.addClassLine("");
    }

    private void compileImports() {
        this.addClassLines(this.importLines);
        this.addClassLine("");
    }

    private void compileBlockCommentLines() {
        this.addClassLines(this.blockCommentLines);
    }

    private void compileLineComments() {
        this.addClassLines(this.lineCommentLines);
    }

    private void compileClassDeclarationLines() {
        this.addClassLines(this.makeFinalizedClassDeclarationLines());
    }

    private void compileClassBlockEndLine() {
        this.addClassLine("}");
    }

    // Conditional Methods
    private boolean isEmptyLine() {
        return this.getLine().isEmpty();
    }

    private boolean isPackageLine() {
        return StringHelper.startsWith(this.getLine(), "package ");
    }

    private boolean isImportLine() {
        return StringHelper.startsWith(this.getLine(), "import ");
    }

    private boolean isBlockCommentStartLine() {
        return StringHelper.startsWith(this.getLine(), "/*");
    }

    private boolean isBlockCommentEndLine() {
        return StringHelper.endsWith(this.getTrimmedLine(), "*/");
    }

    private boolean isLineCommentLine() {
        return StringHelper.startsWith(this.getLine(), "//");
    }

    private boolean isClassDeclarationStartLine() {
        StringIterator iterator = StringIterator.newInstance(this.getLine());
        iterator.popIfStartsWithWithPresenceFallthrough("public ");
        iterator.popIfStartsWithWithPresenceFallthrough("abstract ");
        iterator.popIfStartsWithWithPresenceFallthrough("final ");
        return iterator.nextStartsWith("class");
    }

    private boolean isClassDeclarationEndLine() {
        return StringHelper.endsWith(this.getTrimmedLine(), "{");
    }

    private boolean isInstanceFieldLine() {
        String trimmedLine = this.getTrimmedLine();
        return StringHelper.startsWith(trimmedLine, "private ")
                && StringHelper.contains(trimmedLine, ";")
                && !StringHelper.contains(trimmedLine, " static ")
                && !StringHelper.contains(trimmedLine, " final ")
                && !StringHelper.contains(trimmedLine, "(")
                && !StringHelper.contains(trimmedLine, "{");
    }

    // Protected Accessor Methods
    protected final boolean isPublic() {
        return this.classDeclaration.isPublic();
    }

    protected final boolean isAbstract() {
        return this.classDeclaration.isAbstract();
    }

    protected final boolean isFinal() {
        return this.classDeclaration.isFinal();
    }

    protected final boolean isGeneric() {
        return this.classDeclaration.parametrizedTypesText != null;
    }

    protected final String getName() {
        return this.classDeclaration.getName();
    }

    protected final String getParametrizedTypesText() {
        return this.classDeclaration.getParametrizedTypesText();
    }

    // Protected Compiling Methods
    protected final void compileInstanceFields() {
        this.addClassLine("\t// Instance Fields");
        this.forEachInstanceField((InstanceField field) -> {
            this.classLines.add("\tprivate " + field.getDataType() + " " + field.getName() + ";");
        });
        this.addClassLine("");
    }

    // Protected Operant Methods
    protected final void addClassLine(String line) {
        ListHelper.add(this.classLines, line);
    }

    protected final void addClassLines(StringList lines) {
        ListHelper.addAll(this.classLines, lines);
    }

    protected final void removeLastTrailingCharacters(StringBuilder builder, int length) {
        ConditionalHelper.ifThen(!this.instanceFields.isEmpty(),
                () -> StringDeleterHelper.deleteLastCharacters(builder, length));
    }

    protected final void removeLastTrailingClassLines(int length) {
        ConditionalHelper.ifThen(!this.instanceFields.isEmpty(),
                () -> ListHelper.removeLastItems(this.classLines, length));
    }

    protected final void forEachInstanceField(Consumer<InstanceField> action) {
        ListHelper.forEach(this.instanceFields, action);
    }

    // Private Helper Methods
    private String getLine() {
        return this.inputLines.get(this.inputLineIndex);
    }

    private String getTrimmedLine() {
        return StringTrimmerHelper.trimSurroundingWhiteSpace(this.getLine());
    }

    private void forEachLine(Supplier<Boolean> action) {
        ConditionalHelper.whileLoopUntilFalse(() -> {
            boolean isIterating = action.get();
            ConditionalHelper.ifThen(isIterating, () -> this.incrementLineIndex());
            return isIterating && this.inputLineIndex < this.inputLines.size();
        });
    }

    private void incrementLineIndex() {
        this.inputLineIndex++;
    }

    private void collectSingleLineList(Supplier<Boolean> isMatchingSupplier, StringList lineList) {
        this.forEachLine(() -> {
            boolean isMatching = isMatchingSupplier.get();
            ConditionalHelper.ifThen(isMatching, () -> lineList.add(this.getLine()));
            return isMatching || this.isEmptyLine();
        });
    }

    private void collectMultiLineList(Supplier<Boolean> isMatchingStartSupplier,
            Supplier<Boolean> isMatchingEndSupplier, StringList lineList) {
        BooleanWrapper isMatching = BooleanWrapper.newFalseInstance();
        this.forEachLine(() -> {
            boolean isMatchingStart = isMatchingStartSupplier.get();
            boolean isMatchingEnd = isMatchingEndSupplier.get();
            boolean isEmptyLine = this.isEmptyLine();
            ConditionalHelper.ifThen(isMatchingStart, () -> isMatching.setValueToTrue());
            ConditionalHelper.ifThen(isMatching.isTrue(), () -> lineList.add(this.getLine()));
            ConditionalHelper.ifThen(isMatchingEnd, () -> isMatching.setValueToFalse());
            ConditionalHelper.ifThen(isMatchingEnd, () -> this.incrementLineIndex());
            return isMatching.isTrue() || isEmptyLine;
        });
    }

    private String collectParametrizedTypesText(StringIterator iterator) {
        return ConditionalHelper.newTernaryOperation(iterator.peek() == '<',
                () -> iterator.popNextParentheticToken('<'), () -> null);
    }

    private StringList makeFinalizedClassDeclarationLines() {
        StringBuilder linesText = StringHelper.newBuilder();
        linesText.append(this.classDeclarationLines.toString());
        boolean isAbstract = this.classDeclaration.isAbstract();
        boolean isFinal = this.classDeclaration.isFinal();
        boolean needsFinal = !isAbstract && !isFinal;
        ConditionalHelper.ifThen(needsFinal, () -> {
            boolean isPublic = this.classDeclaration.isPublic();
            int insertionIndex = ConditionalHelper.ifReturnElse(isPublic, 7, 0);
            linesText.insert(insertionIndex, "final ");
        });
        return StringHelper.split(linesText.toString(), "[\n]");
    }

    private void collectInstanceFieldLine() {
        String trimmedLine = this.getTrimmedLine();
        StringList parts = StringHelper.split(trimmedLine, "[ ;]+");
        String privateText = ListHelper.get(parts, 0);
        String nameText = ListHelper.getWithReverseIndex(parts, 0);
        String rawDataTypeText =
                StringHelper.substringFromEndpointTexts(trimmedLine, privateText, nameText + ";");
        String dataTypeText = StringTrimmerHelper.trimSurroundingWhiteSpace(rawDataTypeText);
        InstanceField field = InstanceField.newInstance(dataTypeText, nameText);
        ListHelper.add(this.instanceFields, field);
    }

    private String cleanText(String inputText) {
        return StringDeleterHelper.deleteAllInstances(inputText, '\r');
    }

    // Initialization Methods
    protected void reset(String inputText) {
        this.inputLines = StringHelper.split(this.cleanText(inputText), "[\n]");
        this.inputLineIndex = 0;
        this.classLines = StringHelper.newStringList();
        this.packageLines = StringHelper.newStringList();
        this.importLines = StringHelper.newStringList();
        this.blockCommentLines = StringHelper.newStringList();
        this.lineCommentLines = StringHelper.newStringList();
        this.classDeclarationLines = StringHelper.newStringList();
        this.instanceFields = ListHelper.newArrayList();
    }

    // Internal class used to store class declaration data
    private static class ClassDeclaration {
        // Instance Fields
        private boolean isPublic;
        private boolean isAbstract;
        private boolean isFinal;
        private String name;
        private String parametrizedTypesText;

        // New Instance Method
        public static ClassDeclaration newInstance(boolean isPublic, boolean isAbstract,
                boolean isFinal, String name, String parametrizedTypesText) {
            return new ClassDeclaration(isPublic, isAbstract, isFinal, name, parametrizedTypesText);
        }

        // Constructor Method
        private ClassDeclaration(boolean isPublic, boolean isAbstract, boolean isFinal, String name,
                String parametrizedTypesText) {
            this.isPublic = isPublic;
            this.isAbstract = isAbstract;
            this.isFinal = isFinal;
            this.name = name;
            this.parametrizedTypesText = parametrizedTypesText;
        }

        // Accessor Methods
        public boolean isPublic() {
            return this.isPublic;
        }

        public boolean isAbstract() {
            return this.isAbstract;
        }

        public boolean isFinal() {
            return this.isFinal;
        }

        public String getName() {
            return this.name;
        }

        public String getParametrizedTypesText() {
            return this.parametrizedTypesText;
        }
    }
}
