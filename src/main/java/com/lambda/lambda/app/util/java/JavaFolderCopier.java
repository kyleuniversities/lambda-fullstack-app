package com.lambda.lambda.app.util.java;

import com.lambda.lambda.app.util.file.FileTextReplacer;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.JavaHelper;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.TimeHelper;
import com.lambda.lambda.common.helper.file.FileHelper;
import com.lambda.lambda.common.helper.file.FilePathHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.util.string.StringList;
import com.lambda.lambda.common.util.wrapper.StringWrapper;

/**
 * Utility class for copying a java folder
 */
public final class JavaFolderCopier {
    // Instance Fields
    private String sourceFolderPath;
    private String destinationFolderPath;
    private String backupRepositoryFolderPath;
    private String backupFolderPath;
    private String sourcePackageText;
    private String destinationPackageText;

    // New Instance Method
    public static JavaFolderCopier newInstance() {
        return new JavaFolderCopier();
    }

    // Constructor Method
    private JavaFolderCopier() {
        super();
    }

    // Main Instance Method
    public void copy(String sourceFolderPath, String destinationFolderPath,
            String backupRepositoryFolderPath) {
        this.reset(sourceFolderPath, destinationFolderPath, backupRepositoryFolderPath);
        this.backupDestinationFolderIfExists();
        this.copyFolder();
        this.changeRootFolderReferences();
    }

    // Major Methods
    private void backupDestinationFolderIfExists() {
        ConditionalHelper.ifThen(FileHelper.fileExists(this.destinationFolderPath), () -> {
            FileHelper.copyFolder(this.destinationFolderPath, this.backupFolderPath);
        });
    }

    private void copyFolder() {
        FileHelper.copyFolder(this.sourceFolderPath, this.destinationFolderPath);
    }

    private void changeRootFolderReferences() {
        FileTextReplacer.newInstance().replace(this.destinationFolderPath, this.sourcePackageText,
                this.destinationPackageText);
    }

    // Private Helper Methods
    private String getPackageText(String folderPath) {
        return JavaHelper.getPackageText(folderPath);
    }

    // Initialization Methods
    private void reset(String sourceFolderPath, String destinationFolderPath,
            String backupRepositoryFolderPath) {
        this.sourceFolderPath = sourceFolderPath;
        this.destinationFolderPath = destinationFolderPath;
        this.backupRepositoryFolderPath = backupRepositoryFolderPath;
        this.backupFolderPath = this.backupRepositoryFolderPath + "/" + TimeHelper.getTimeText();
        this.sourcePackageText = this.getPackageText(this.sourceFolderPath);
        this.destinationPackageText = this.getPackageText(this.destinationFolderPath);
    }
}
