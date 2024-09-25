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
import com.lambda.lambda.app.utility.java.JavaStructureClassMaker;
import com.lambda.lambda.app.utility.java.JavaVoidMethodsMaker;
import com.lambda.lambda.common.util.string.StringList;

@CrossOrigin
@RestController
@RequestMapping("/java/spring-boot")
public final class SpringBootController {
    @PostMapping("/table")
    public String table(@RequestBody LambdaArguments lambdaArguments) {
        return CodeHelper.toCode("Stub");
    }
}
