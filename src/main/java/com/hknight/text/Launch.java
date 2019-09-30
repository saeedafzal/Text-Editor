package com.hknight.text;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;

import com.hknight.text.gui.Window;

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
