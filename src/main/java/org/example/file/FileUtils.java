package org.example.file;

import lombok.experimental.UtilityClass;

import java.io.File;

@UtilityClass
public class FileUtils {
    public String getExtension(String name) {
        return name.split("\\.")[1];
    }
}
