package com.lambda.lambda.app.util.file;

import java.io.File;
import java.util.List;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.MapHelper;
import com.lambda.lambda.common.helper.file.FileHelper;
import com.lambda.lambda.common.helper.file.FilePathHelper;
import com.lambda.lambda.common.helper.string.StringCodeHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.helper.string.StringReplacementHelper;
import com.lambda.lambda.common.helper.string.StringTrimmerHelper;
import com.lambda.lambda.common.util.string.StringList;
import com.lambda.lambda.common.util.string.StringMap;
import com.lambda.lambda.common.util.string.StringMapMap;

public final class FileTextReplacer {
    // Instance Fields
    private String rootFolderPath;
    private String targetText;
    private String replacementText;

    // New Instance Method
    public static FileTextReplacer newInstance() {
        return new FileTextReplacer();
    }

    // Constructor Method
    private FileTextReplacer() {
        super();
    }

    // Main Instance Method
    public StringList replace(String rootFolderPath, String targetText, String replacementText) {
        this.reset(rootFolderPath, targetText, replacementText);
        StringList replacements = StringList.newInstance();
        FileHelper.forEachFileDescendant(rootFolderPath, (File file) -> {
            String path = FilePathHelper.getAbsolutePath(file);
            boolean isTextExtension = FilePathHelper.hasTextFileExtension(path);
            ConditionalHelper.ifThen(isTextExtension, () -> this.replace(replacements, path));
        });
        return replacements;
    }

    // Major Methods
    private void replace(StringList replacements, String filePath) {
        String text = FileHelper.getText(filePath);
        ConditionalHelper.ifThen(StringHelper.contains(text, this.targetText), () -> {
            String relativePath = FilePathHelper.getRelativePath(this.rootFolderPath, filePath);
            ListHelper.add(replacements, relativePath);
            String replaced =
                    StringReplacementHelper.replace(text, this.targetText, this.replacementText);
            FileHelper.exportText(replaced, filePath);
        });
    }

    // Initialization Methods
    private void reset(String rootFolderPath, String targetText, String replacementText) {
        this.rootFolderPath = StringTrimmerHelper.trimSurroundingWhiteSpace(rootFolderPath);
        this.targetText = targetText;
        this.replacementText = replacementText;
    }
}
