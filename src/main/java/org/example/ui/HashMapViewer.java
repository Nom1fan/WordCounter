package org.example.ui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.Map;

public class HashMapViewer extends JFrame {

    public HashMapViewer(Map<String, Integer> hashMap) {
        setTitle("Word Histogram");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        String[] columnNames = {"Word", "Count"};
        Object[][] data = new Object[hashMap.size()][2];

        int row = 0;
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            data[row][0] = entry.getKey();
            data[row][1] = entry.getValue();
            row++;
        }

        // Sort the data by the integer values in descending order
        data = Arrays.stream(data, 0, data.length)
                .sorted((a, b) -> Integer.compare((Integer) b[1], (Integer) a[1]))
                .toArray(Object[][]::new);

        JTable table = new JTable(data, columnNames);

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }
}
