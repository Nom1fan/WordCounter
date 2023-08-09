package org.example.ui;

import lombok.extern.slf4j.Slf4j;
import org.example.file.TextFileFactory;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class WordCountLogic implements FileSelectionListener {

    @Override
    public void onFileSelected(String filePath) {
        log.info("Processing file: {}", filePath);
        try {
            var text = TextFileFactory.create(filePath).getText();
            String sanitizedText = sanitizeString(text);
            String[] words = sanitizedText.split(" ");
            List<String> wordsList = Arrays.stream(words).filter(word -> word.length() > 1).toList();

            Map<String, Integer> word2Count = new HashMap<>();

            for (String word : wordsList) {
                if (word2Count.computeIfPresent(word, (w, c) -> c + 1) == null) {
                    word2Count.put(word, 1);
                }
            }

            SwingUtilities.invokeLater(() -> new HashMapViewer(word2Count).setVisible(true));
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, "Please select textual files only!", "Error", JOptionPane.ERROR_MESSAGE));
        } catch (IOException e) {
            log.error("Failed to process file: {}", filePath, e);
            throw new IllegalArgumentException("Failed to process file:" + filePath);
        }
    }

    private static String sanitizeString(String input) {
        input = input.replace("\n", " ");

        // Regular expression pattern to match characters other than white spaces and English letters
        String regex = "[^\\p{IsLatin}\\p{IsHebrew}\\s]";

        // Compile the pattern to create a regex matcher
        Pattern pattern = Pattern.compile(regex);

        // Use the matcher to find and replace non-matching characters with an empty string
        Matcher matcher = pattern.matcher(input);
        return matcher.replaceAll("");
    }
}
