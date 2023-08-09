package org.example;


import org.example.ui.*;

import javax.swing.*;
import java.util.*;

/**
 *
 * 
 */
public class Main {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FileChooser(Set.of(new WordCountLogic())).setVisible(true));
    }

}

