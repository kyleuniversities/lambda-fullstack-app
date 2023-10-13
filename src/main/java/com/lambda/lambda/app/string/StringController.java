package com.lambda.lambda.app.string;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lambda.lambda.app.helper.CodeHelper;
import com.lambda.lambda.app.util.LambdaArguments;
import com.lambda.lambda.common.helper.IterationHelper;
import com.lambda.lambda.common.helper.string.StringHelper;

@CrossOrigin
@RestController
@RequestMapping("/string")
public final class StringController {
    @PostMapping("/capitalize")
    public String capitalize(@RequestBody LambdaArguments lambdaArguments) {
        return CodeHelper.toCode(lambdaArguments.getArgument(0).toUpperCase());
    }

    @PostMapping("/concat")
    public String concat(@RequestBody LambdaArguments lambdaArguments) {
        StringBuilder concatenated = StringHelper.newBuilder();
        IterationHelper.forEach(lambdaArguments.getArgumentsSize(),
                (Integer i) -> concatenated.append(lambdaArguments.getArgument(i)));
        return CodeHelper.toCode(concatenated.toString());
    }

    @PostMapping("/length")
    public String length(@RequestBody LambdaArguments lambdaArguments) {
        return CodeHelper.toCode(StringHelper.toString(lambdaArguments.getArgument(0).length()));
    }

    @PostMapping("/split")
    public String split(@RequestBody LambdaArguments lambdaArguments) {
        String bodyText = lambdaArguments.getBodyText();
        String regex = lambdaArguments.getArgument(0);
        List<String> parts = StringHelper.split(bodyText, regex);
        return CodeHelper.toCode(StringHelper.join(parts, "\n"));
    }
}
