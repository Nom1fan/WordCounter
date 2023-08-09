package org.example.ui;

import lombok.extern.slf4j.Slf4j;
import org.example.file.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Set;

@Slf4j
public class FileChooser extends Frame {

    public FileChooser(Set<FileSelectionListener> fileSelectionListeners) throws HeadlessException {
        super("Word Count");

        FileDialog fileDialog = new FileDialog(this, "Select a File", FileDialog.LOAD);
        fileDialog.setDirectory(".");
        FilenameFilter filter = (dir, name) -> {
            String extension = FileUtils.getExtension(name);
            return extension.equalsIgnoreCase("txt") ||
                    extension.equalsIgnoreCase("doc") ||
                    extension.equalsIgnoreCase("docx") ||
                    extension.equalsIgnoreCase("pdf");
        };
        fileDialog.setFilenameFilter(filter);

        Button openButton = new Button("Open File Dialog");
        JLabel label = new JLabel("מאמי שלי תלחצי :)");
        openButton.addActionListener(e -> {
            fileDialog.setVisible(true);

            String directory = fileDialog.getDirectory();
            String file = fileDialog.getFile();

            if (directory == null || file == null) {
                log.error("Failed to find file. Either dir or filename were null");
                return;
            }

            for (FileSelectionListener fileSelectionListener : fileSelectionListeners) {
                fileSelectionListener.onFileSelected(directory + file);
            }
        });

        setLayout(new FlowLayout());
        add(openButton, BorderLayout.CENTER);
        add(label, BorderLayout.CENTER);
        setSize(400, 150);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }
}
