package org.example.file;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PdfFile implements TextFile {

    private final String filePath;

    public PdfFile(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getText() throws IOException {
        PDDocument document = PDDocument.load(new File(filePath));
        PDFTextStripper stripper = new PDFTextStripper();
        return stripper.getText(document);
    }
}
