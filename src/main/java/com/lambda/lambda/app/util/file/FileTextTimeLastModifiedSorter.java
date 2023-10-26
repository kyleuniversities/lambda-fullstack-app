package com.lambda.lambda.app.util.file;

import java.io.File;
import java.util.List;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.TimeHelper;
import com.lambda.lambda.common.helper.file.FileHelper;
import com.lambda.lambda.common.helper.file.FilePathHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.helper.string.StringTrimmerHelper;
import com.lambda.lambda.common.util.string.StringList;

public final class FileTextTimeLastModifiedSorter {
    // Instance Fields
    private List<String> rootFolderPaths;
    private int maxPrintNumber;
    private List<FileEntry> textFileEntries;
    private StringList sorted;

    // New Instance Method
    public static FileTextTimeLastModifiedSorter newInstance() {
        return new FileTextTimeLastModifiedSorter();
    }

    // Constructor Method
    private FileTextTimeLastModifiedSorter() {
        super();
    }

    // Main Instance Methods
    public StringList sort(List<String> rootFolderPaths) {
        return this.sort(rootFolderPaths, 100);
    }

    public StringList sort(List<String> rootFolderPaths, int maxPrintNumber) {
        this.reset(rootFolderPaths, maxPrintNumber);
        this.compileFileEntries();
        this.sortFileEntries();
        this.compiledSortedLines();
        return StringHelper.copyStringList(this.sorted);
    }

    // Major Methods
    private void compileFileEntries() {
        ListHelper.forEach(this.rootFolderPaths, (String rootFolderPath) -> {
            FileHelper.forEachFileDescendant(rootFolderPath, (File file) -> {
                this.appendFileEntryIfTextFile(rootFolderPath, file);
            });
        });
    }

    private void sortFileEntries() {
        ListHelper.sort(this.textFileEntries);
    }

    private void compiledSortedLines() {
        ListHelper.forEach(this.textFileEntries, (Integer i, FileEntry entry) -> {
            if (i >= this.maxPrintNumber) {
                return false;
            }
            ListHelper.add(this.sorted, entry.filePath);
            ListHelper.add(this.sorted, "  " + FilePathHelper.getFileName(entry.filePath));
            ListHelper.add(this.sorted, "  " + entry.getTimeLastModifiedTimeText());
            ListHelper.add(this.sorted, "");
            ListHelper.add(this.sorted, "");
            return true;
        });
    }

    // Private Helper Methods
    private void appendFileEntryIfTextFile(String rootFolderPath, File file) {
        String filePath = file.getAbsolutePath();
        boolean isTextFile = FilePathHelper.hasTextFileExtension(file.getAbsolutePath());
        ConditionalHelper.ifThen(isTextFile, () -> {
            ListHelper.add(this.textFileEntries, FileEntry.newInstance(rootFolderPath, filePath,
                    FileHelper.getTimeLastModifiedInEpochMilliseconds(file)));
        });
    }

    private boolean isValidRootFolderPath(String rootFolderPath) {
        return !StringTrimmerHelper.trimSurroundingWhiteSpace(rootFolderPath).isEmpty();
    }

    // Initialization Methods
    private void reset(List<String> rootFolderPaths, int maxPrintNumber) {
        this.rootFolderPaths = ListHelper.filterClone(rootFolderPaths, this::isValidRootFolderPath);
        this.maxPrintNumber = maxPrintNumber;
        this.textFileEntries = ListHelper.newArrayList();
        this.sorted = StringHelper.newStringList();
    }

    // Internal Class used for storing file entries
    private static class FileEntry implements Comparable<FileEntry> {
        // Instance Fields
        private String rootFolderPath;
        private String filePath;
        private long timeLastModified;

        // New Instance Method
        public static FileEntry newInstance(String rootFolderPath, String filePath,
                long timeLastModified) {
            return new FileEntry(rootFolderPath, filePath, timeLastModified);
        }

        // Constructor Method
        private FileEntry(String rootFolderPath, String filePath, long timeLastModified) {
            super();
            this.rootFolderPath = rootFolderPath;
            this.filePath = filePath;
            this.timeLastModified = timeLastModified;
        }

        // Accessor Methods
        public String getRelativePath() {
            return FilePathHelper.getRelativePath(this.rootFolderPath, this.filePath);
        }

        public String getTimeLastModifiedTimeText() {
            return TimeHelper.getTimeText(this.timeLastModified);
        }

        // Compare To Method
        @Override
        public int compareTo(FileEntry entry) {
            return -Long.compare(this.timeLastModified, entry.timeLastModified);
        }
    }
}
