package com.lambda.lambda.test;

import com.lambda.lambda.app.util.java.JavaStructureClassMaker;
import com.lambda.lambda.app.util.java.JavaFunctionClassMaker;

public class JavaStructureTester {
    public static void main(String[] args) {
        System.out.println("En Joji!");
        System.out.println(JavaFunctionClassMaker.newInstance().make(x()));
    }

    private static String x() {
        return "package com.lambda.lambda.app.util.java;\r\n" + //
                "\r\n" + //
                "import java.util.List;\r\n" + //
                "import java.util.function.Supplier;\r\n" + //
                "import com.lambda.lambda.common.helper.ConditionalHelper;\r\n" + //
                "import com.lambda.lambda.common.helper.ListHelper;\r\n" + //
                "import com.lambda.lambda.common.helper.string.StringDeleterHelper;\r\n" + //
                "import com.lambda.lambda.common.helper.string.StringHelper;\r\n" + //
                "import com.lambda.lambda.common.helper.string.StringTrimmerHelper;\r\n" + //
                "import com.lambda.lambda.common.util.string.StringIterator;\r\n" + //
                "import com.lambda.lambda.common.util.string.StringList;\r\n" + //
                "import com.lambda.lambda.common.util.wrapper.BooleanWrapper;\r\n" + //
                "\r\n" + //
                "/**\r\n" + //
                " * Utility class for creating java structure classes from short class input\r\n" + //
                " */\r\n" + //
                "class JavaStructureClassMaker {\r\n" + //
                "    // Instance Fields\r\n" + //
                "    private StringList inputLines;\r\n" + //
                "    private boolean isMakingSetters;\r\n" + //
                "    private int inputLineIndex;\r\n" + //
                "    private StringList classLines;\r\n" + //
                "    private StringList packageLines;\r\n" + //
                "}\r\n" + //
                "";
    }
}
