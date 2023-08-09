package org.example.file;

import java.io.File;

public final class TextFileFactory {

    private TextFileFactory() {}

    public static TextFile create(String filePath) {
        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("Filepath cannot be null or blank");
        }

        File file = new File(filePath);
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist in path: " + filePath);
        }

        String extension = FileUtils.getExtension(file.getName());

        if (extension.equalsIgnoreCase("doc") || extension.equalsIgnoreCase("docx")) {
            return new DocFile(filePath);
        }
        if (extension.equalsIgnoreCase("pdf")) {
            return new PdfFile(filePath);
        }
        if (extension.equalsIgnoreCase("txt")) {
            return new TxtFile(filePath);
        }
        throw new IllegalArgumentException("File is not of a textual format:" + filePath);
    }
}
