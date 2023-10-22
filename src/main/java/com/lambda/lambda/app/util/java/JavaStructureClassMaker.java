package com.lambda.lambda.app.util.java;

import java.util.List;
import java.util.function.Supplier;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.string.StringCodeHelper;
import com.lambda.lambda.common.helper.string.StringDeleterHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.helper.string.StringTrimmerHelper;
import com.lambda.lambda.common.util.string.StringIterator;
import com.lambda.lambda.common.util.string.StringList;
import com.lambda.lambda.common.util.wrapper.BooleanWrapper;

/**
 * Utility class for creating java structure classes from short class input
 */
public final class JavaStructureClassMaker {
    // Instance Fields
    private StringList inputLines;
    private boolean isMakingSetters;
    private int inputLineIndex;
    private StringList classLines;
    private StringList packageLines;
    private StringList importLines;
    private StringList blockCommentLines;
    private StringList lineCommentLines;
    private StringList classDeclarationLines;
    private ClassDeclaration classDeclaration;
    private List<InstanceField> instanceFields;

    // New Instance Method
    public static JavaStructureClassMaker newInstance() {
        return new JavaStructureClassMaker();
    }

    // Constructor Method
    private JavaStructureClassMaker() {
        super();
    }

    // Main Instance Method
    public StringList make(String inputText, boolean isMakingSetters) {
        this.reset(inputText, isMakingSetters);
        this.collect();
        this.compile();
        return this.classLines;
    }

    // Major Methods
    private void collect() {
        this.collectPackage();
        this.collectImports();
        this.collectBlockCommentLines();
        this.collectLineComments();
        this.collectClassDeclarationLines();
        this.collectClassDeclaration();
        this.collectInstanceFields();
    }

    private void compile() {
        this.compilePackage();
        this.compileImports();
        this.compileBlockCommentLines();
        this.compileLineComments();
        this.compileClassDeclarationLines();
        this.compileInstanceFields();
        this.compileNewInstanceMethod();
        this.compileConstructorMethod();
        this.compileAccessorMethods();
        this.compileMutatorMethods();
        this.compileClassBlockEndLine();
    }

    // Collecting Methods
    private void collectPackage() {
        this.collectSingleLineList(this::isPackageLine, this.packageLines);
    }

    private void collectImports() {
        this.collectSingleLineList(this::isImportLine, this.importLines);
    }

    private void collectBlockCommentLines() {
        System.out.println("BC_START: " + this.inputLineIndex);
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
        ListHelper.addAll(this.classLines, this.packageLines);
        ListHelper.add(this.classLines, "");
    }

    private void compileImports() {
        ListHelper.addAll(this.classLines, this.importLines);
        ListHelper.add(this.classLines, "");
    }

    private void compileBlockCommentLines() {
        ListHelper.addAll(this.classLines, this.blockCommentLines);
    }

    private void compileLineComments() {
        ListHelper.addAll(this.classLines, this.lineCommentLines);
    }

    private void compileClassDeclarationLines() {
        ListHelper.addAll(this.classLines, this.makeFinalizedClassDeclarationLines());
    }

    private void compileInstanceFields() {
        ListHelper.add(this.classLines, "\t// Instance Fields");
        ListHelper.forEach(this.instanceFields, (InstanceField field) -> {
            this.classLines.add("\tprivate " + field.dataType + " " + field.getName() + ";");
        });
        ListHelper.add(this.classLines, "");
    }

    private void compileNewInstanceMethod() {
        ConditionalHelper.ifThen(!this.classDeclaration.isAbstract(), () -> {
            ListHelper.add(this.classLines, "\t// New Instance Method");
            ListHelper.add(this.classLines, this.makeNewInstanceDeclarationLine());
            ListHelper.add(this.classLines, this.makeNewInstanceReturnLine());
            ListHelper.add(this.classLines, "\t}");
            ListHelper.add(this.classLines, "");
        });
    }

    private void compileConstructorMethod() {
        ListHelper.add(this.classLines, "\t// Constructor Method");
        ListHelper.add(this.classLines, this.makeConstructorDeclarationLine());
        ListHelper.add(this.classLines, "\t\tsuper();");
        ListHelper.addAll(this.classLines, this.makeConstructorInitializerLines());
        ListHelper.add(this.classLines, "\t}");
    }

    private void compileAccessorMethods() {
        ListHelper.add(this.classLines, "");
        ListHelper.add(this.classLines, "\t// Accessor Methods");
        ListHelper.forEach(this.instanceFields, (InstanceField field) -> {
            ListHelper.addAll(this.classLines, this.makeGetterMethodLines(field));
            ListHelper.add(this.classLines, "");
        });
        ListHelper.removeLastItems(this.classLines, 1);
    }

    private void compileMutatorMethods() {
        ConditionalHelper.ifThen(this.isMakingSetters, () -> {
            ListHelper.add(this.classLines, "");
            ListHelper.add(this.classLines, "\t// Mutator Methods");
            ListHelper.forEach(this.instanceFields, (InstanceField field) -> {
                ListHelper.addAll(this.classLines, this.makeSetterMethodLines(field));
                ListHelper.add(this.classLines, "");
            });
            ListHelper.removeLastItems(this.classLines, 1);
        });
    }

    private void compileClassBlockEndLine() {
        ListHelper.add(this.classLines, "}");
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
            System.out.println("MULTI_" + this.inputLineIndex);
            System.out.println(" line: " + StringCodeHelper.toCode(this.getLine()));
            System.out.println(" isMatchingStart: " + isMatchingStart);
            System.out.println(" isMatchingEnd: " + isMatchingEnd);
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

    private String makeNewInstanceDeclarationLine() {
        StringBuilder line = StringHelper.newBuilder();
        boolean isGeneric = this.classDeclaration.parametrizedTypesText != null;
        String parametrizedTypesText = this.classDeclaration.getParametrizedTypesText();
        line.append("\tpublic static ");
        ConditionalHelper.ifThen(isGeneric, () -> line.append(parametrizedTypesText + " "));
        line.append(this.classDeclaration.getName());
        ConditionalHelper.ifThen(isGeneric, () -> line.append(parametrizedTypesText));
        line.append(" newInstance(");
        ListHelper.forEach(this.instanceFields, (InstanceField field) -> line
                .append(field.getDataType() + " " + field.getName() + ", "));
        StringDeleterHelper.deleteLastCharacters(line, 2);
        line.append(") {");
        return line.toString();
    }

    private String makeNewInstanceReturnLine() {
        StringBuilder line = StringHelper.newBuilder();
        boolean isGeneric = this.classDeclaration.parametrizedTypesText != null;
        line.append("\t\t return new ");
        line.append(this.classDeclaration.getName());
        ConditionalHelper.ifThen(isGeneric, () -> line.append("<>"));
        line.append("(");
        ListHelper.forEach(this.instanceFields,
                (InstanceField field) -> line.append(field.getName() + ", "));
        StringDeleterHelper.deleteLastCharacters(line, 2);
        line.append(");");
        return line.toString();
    }

    private String makeConstructorDeclarationLine() {
        StringBuilder line = StringHelper.newBuilder();
        boolean isAbstract = this.classDeclaration.isAbstract;
        line.append("\t");
        line.append(ConditionalHelper.ifReturnElse(isAbstract, "protected ", "private "));
        line.append(this.classDeclaration.getName());
        line.append("(");
        ListHelper.forEach(this.instanceFields, (InstanceField field) -> line
                .append(field.getDataType() + " " + field.getName() + ", "));
        StringDeleterHelper.deleteLastCharacters(line, 2);
        line.append(") {");
        return line.toString();
    }

    private StringList makeConstructorInitializerLines() {
        StringList lines = StringHelper.newStringList();
        ListHelper.forEach(this.instanceFields, (InstanceField field) -> {
            ListHelper.add(lines, "\t\tthis." + field.getName() + " = " + field.getName() + ";");
        });
        return lines;
    }

    private StringList makeGetterMethodLines(InstanceField field) {
        StringBuilder linesText = StringHelper.newBuilder();
        String dataType = field.getDataType();
        String name = field.getName();
        boolean isAbstract = this.classDeclaration.isAbstract();
        linesText.append("\tpublic ");
        ConditionalHelper.ifThen(isAbstract, () -> linesText.append("final "));
        linesText.append(dataType + " ");
        linesText.append(ConditionalHelper.ifReturnElse(dataType.equals("boolean"), name,
                "get" + StringHelper.capitalizeFirstLetter(name)));
        linesText.append("() {\n");
        linesText.append("\t\treturn this." + name + ";\n");
        linesText.append("\t}");
        return StringHelper.split(linesText.toString(), "[\n]");
    }

    private StringList makeSetterMethodLines(InstanceField field) {
        StringBuilder linesText = StringHelper.newBuilder();
        String dataType = field.getDataType();
        String name = field.getName();
        boolean isAbstract = this.classDeclaration.isAbstract();
        linesText.append("\tpublic ");
        ConditionalHelper.ifThen(isAbstract, () -> linesText.append("final "));
        linesText.append("void set" + StringHelper.capitalizeFirstLetter(name));
        linesText.append("(" + dataType + " " + name + ") {\n");
        linesText.append("\t\tthis." + name + " = " + name + ";\n");
        linesText.append("\t}");
        return StringHelper.split(linesText.toString(), "[\n]");
    }

    private String cleanText(String inputText) {
        return StringDeleterHelper.deleteAllInstances(inputText, '\r');
    }

    // Initialization Methods
    private void reset(String inputText, boolean isMakingSetters) {
        this.inputLines = StringHelper.split(this.cleanText(inputText), "[\n]");
        this.isMakingSetters = isMakingSetters;
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

    // Internal class used to store instance field data
    private static class InstanceField {
        // Instance Fields
        private String dataType;
        private String name;

        // New Instance Method
        public static InstanceField newInstance(String dataType, String name) {
            return new InstanceField(dataType, name);
        }

        // Constructor Method
        private InstanceField(String dataType, String name) {
            this.dataType = dataType;
            this.name = name;
        }

        // Accessor Methods
        public String getDataType() {
            return this.dataType;
        }

        public String getName() {
            return this.name;
        }
    }
}
