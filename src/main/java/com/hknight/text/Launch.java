package com.hknight.text;

import com.hknight.text.gui.Window;

import javax.swing.*;

public class Launch {

    public static void main(String[] args) {
        if (System.getProperty("os.name").startsWith("Mac")) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("apple.awt.application.name", "Text Editor");
        }

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                final Window window = new Window();
                SwingUtilities.updateComponentTreeUI(window);
                window.setLocationRelativeTo(null);
                window.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
