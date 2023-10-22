package com.lambda.lambda.app.controller.java;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lambda.lambda.app.helper.CodeHelper;
import com.lambda.lambda.app.util.LambdaArguments;
import com.lambda.lambda.app.util.java.JavaStructureClassMaker;
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
}
