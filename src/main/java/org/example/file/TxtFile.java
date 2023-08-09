package org.example.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TxtFile implements TextFile {

    private final String filePath;

    public TxtFile(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getText() throws IOException {
        return Files.readString(Path.of(filePath));
    }
}
