package com.lambda.lambda.app.controller.java;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lambda.lambda.app.helper.CodeHelper;
import com.lambda.lambda.app.utility.LambdaArguments;
import com.lambda.lambda.app.utility.java.JavaFolderCopier;
import com.lambda.lambda.app.utility.java.JavaFunctionClassMaker;
import com.lambda.lambda.app.utility.java.JavaGetterMethodsMaker;
import com.lambda.lambda.app.utility.java.JavaSetterMethodsMaker;
import com.lambda.lambda.app.utility.java.JavaStructureClassMaker;
import com.lambda.lambda.app.utility.java.JavaVoidMethodsMaker;
import com.lambda.lambda.common.util.string.StringList;

@CrossOrigin
@RestController
@RequestMapping("/java")
public final class JavaController {
    @PostMapping("/structure")
    public String structure(@RequestBody LambdaArguments lambdaArguments) {
        StringList classLines = JavaStructureClassMaker.newInstance()
                .make(lambdaArguments.getBodyText(), lambdaArguments.argumentsContain("-setters"));
        String classLinesText = classLines.toString();
        return CodeHelper.toCode(classLinesText);
    }

    @PostMapping("/function")
    public String function(@RequestBody LambdaArguments lambdaArguments) {
        StringList classLines =
                JavaFunctionClassMaker.newInstance().make(lambdaArguments.getBodyText());
        String classLinesText = classLines.toString();
        return CodeHelper.toCode(classLinesText);
    }

    @PostMapping("/folder/copy")
    public String copyFolder(@RequestBody LambdaArguments lambdaArguments) {
        StringList lines = lambdaArguments.getNonEmptyBodyLines();
        String sourceFolderPath = lines.get(0);
        String destinationFolderPath = lines.get(1);
        String backupRepositoryFolderPath = lines.get(2);
        JavaFolderCopier.newInstance().copy(sourceFolderPath, destinationFolderPath,
                backupRepositoryFolderPath);
        return CodeHelper.toCode("The folder has been copied");
    }

    @PostMapping("/void")
    public String voidMethods(@RequestBody LambdaArguments lambdaArguments) {
        StringList methodLines =
                JavaVoidMethodsMaker.newInstance().make(lambdaArguments.getBodyText());
        String methodLinesText = methodLines.toString();
        return CodeHelper.toCode(methodLinesText);
    }

    @PostMapping({"/get", "/getter", "/getters"})
    public String getterMethods(@RequestBody LambdaArguments lambdaArguments) {
        StringList methodLines =
                JavaGetterMethodsMaker.newInstance().make(lambdaArguments.getBodyText());
        String methodLinesText = methodLines.toString();
        return CodeHelper.toCode(methodLinesText);
    }

    @PostMapping({"/set", "/setter", "/setters"})
    public String setterMethods(@RequestBody LambdaArguments lambdaArguments) {
        StringList methodLines =
                JavaSetterMethodsMaker.newInstance().make(lambdaArguments.getBodyText());
        String methodLinesText = methodLines.toString();
        return CodeHelper.toCode(methodLinesText);
    }
}
