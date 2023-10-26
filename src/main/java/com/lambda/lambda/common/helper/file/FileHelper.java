package com.lambda.lambda.common.helper.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;
import com.lambda.lambda.common.helper.ArrayHelper;
import com.lambda.lambda.common.helper.ConditionalHelper;
import com.lambda.lambda.common.helper.ExceptionHelper;
import com.lambda.lambda.common.helper.FunctionHelper;
import com.lambda.lambda.common.helper.ListHelper;
import com.lambda.lambda.common.helper.string.StringHelper;
import com.lambda.lambda.common.util.string.StringList;

/**
 * Helper class for File Operations
 */
public class FileHelper {
    /**
     * Exports lines as into a text file
     */
    public static void exportStringList(Iterable<String> lines, String path) {
        try {
            PrintWriter writer = FileHelper.newPrintWriter(path);
            for (String line : lines) {
                writer.println(line);
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionHelper.throwNewIllegalStateException(e.getMessage());
        }
    }

    /**
     * Exports lines as into a text file
     */
    public static void exportText(String text, String path) {
        FileHelper.exportStringList(StringHelper.split(text, "[\n]"), path);
    }

    /**
     * Iterates through each file in directly within a directory
     */
    public static void forEachFile(String folderPath, Consumer<File> action) {
        File folder = FileHelper.newFile(folderPath);
        List<File> children = FileHelper.listFiles(folder);
        ListHelper.forEach(children, action);
    }

    /**
     * Iterates through each file in recursively within a directory
     */
    public static void forEachFileDescendant(String rootFolderPath, Consumer<File> action) {
        FileHelper.forEachFileDescendant(rootFolderPath, false, action);
    }

    /**
     * Iterates through each file in recursively within a directory
     */
    public static void forEachFileDescendant(String rootFolderPath, boolean includeDirectories,
            Consumer<File> action) {
        FileHelper.forEachFile(rootFolderPath, (File file) -> {
            boolean isDirectory = file.isDirectory();
            boolean isProcessable = !isDirectory || includeDirectories;
            ConditionalHelper.ifThen(isProcessable, () -> action.accept(file));
            ConditionalHelper.ifThen(isDirectory, () -> FileHelper
                    .forEachFileDescendant(file.getAbsolutePath(), includeDirectories, action));
        });
    }

    /**
     * Gets the file lines of a text file as String List
     */
    public static StringList getStringList(File file) {
        return FileHelper.getStringList(FilePathHelper.getAbsolutePath(file));
    }

    /**
     * Gets the file lines of a text file as String List
     */
    public static StringList getStringList(String path) {
        StringList list = StringList.newInstance();
        try {
            BufferedReader reader = FileHelper.newBufferedReader(path);
            String line = reader.readLine();
            while (line != null) {
                ListHelper.add(list, line);
                line = reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionHelper.throwNewIllegalStateException(e.getMessage());
        }
        return list;
    }

    /**
     * Gets the lines of a text file as a String
     */
    public static String getText(String path) {
        return StringHelper.join(FileHelper.getStringList(path), "\n");
    }

    /**
     * Lists the file children of a folder File
     */
    public static List<File> listFiles(File file) {
        return ListHelper.toList(file.listFiles());
    }

    /**
     * Creates a new Buffered Reader object
     */
    public static BufferedReader newBufferedReader(String path) throws IOException {
        return new BufferedReader(new FileReader(FileHelper.newFile(path)));
    }

    /**
     * Creates a new File object
     */
    public static File newFile(String path) {
        return new File(path);
    }

    /**
     * Creates a new Print Writer object
     */
    public static PrintWriter newPrintWriter(String path) throws IOException {
        return new PrintWriter(path);
    }

    /**
     * Private Constructor to prevent instantiation
     */
    private FileHelper() {
        super();
    }
}
