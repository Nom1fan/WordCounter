package org.example.file;

import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.extractor.POITextExtractor;

import java.io.File;
import java.io.IOException;

public class DocFile implements TextFile {

    private final String filePath;

    public DocFile(String filePath) {
        this.filePath = filePath;
    }
    @Override
    public String getText() throws IOException {
        File file = new File(filePath);
        try (POITextExtractor extractor = ExtractorFactory.createExtractor(file)) {
            return extractor.getText();
        }
    }
}
