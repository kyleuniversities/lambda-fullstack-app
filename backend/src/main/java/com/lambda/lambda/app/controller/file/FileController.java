package com.lambda.lambda.app.controller.file;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lambda.lambda.app.helper.CodeHelper;
import com.lambda.lambda.app.utility.LambdaArguments;
import com.lambda.lambda.app.utility.file.FileTextFinder;
import com.lambda.lambda.app.utility.file.FileTextReplacer;
import com.lambda.lambda.app.utility.file.FileTextTimeLastModifiedSorter;
import com.lambda.lambda.app.utility.java.JavaStructureClassMaker;
import com.lambda.lambda.common.helper.string.StringTrimmerHelper;
import com.lambda.lambda.common.util.string.StringList;

@CrossOrigin
@RestController
@RequestMapping("/file")
public final class FileController {
    @PostMapping("/find")
    public String find(@RequestBody LambdaArguments lambdaArguments) {
        StringList findingsLines = FileTextFinder.newInstance().find(lambdaArguments.getBodyText(),
                lambdaArguments.getArgument(0));
        String findingsLinesText = findingsLines.toString();
        return CodeHelper.toCode(findingsLinesText);
    }

    @PostMapping("/replace")
    public String replace(@RequestBody LambdaArguments lambdaArguments) {
        StringList findingsLines =
                FileTextReplacer.newInstance().replace(lambdaArguments.getBodyText(),
                        lambdaArguments.getArgument(0), lambdaArguments.getArgument(1));
        String findingsLinesText = findingsLines.toString();
        return CodeHelper.toCode(findingsLinesText);
    }

    @PostMapping("/text/sort-by-last-modified")
    public String textSortByLastModified(@RequestBody LambdaArguments lambdaArguments) {
        StringList sortedLines =
                FileTextTimeLastModifiedSorter.newInstance().sort(lambdaArguments.getBodyLines());
        String sortedLinesText = sortedLines.toString();
        return CodeHelper.toCode(sortedLinesText);
    }
}
