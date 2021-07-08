package com.saeed.editor;

import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;
import com.saeed.editor.ui.Window;

public class Editor {

    public static void main(String[] args) {
        // MacOS settings
        System.setProperty("apple.laf.useScreenMenuBar", "true");

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
                Window window = new Window();
                window.setVisible(true);
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
        });
    }
}
