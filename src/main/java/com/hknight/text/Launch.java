package com.hknight.text;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import com.hknight.text.gui.Window;

public class Launch {

    public static void main(String[] args) {
        if (System.getProperty("os.name").startsWith("Mac")) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("apple.awt.application.name", "Text Editor");
        }

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
                final Window window = new Window();
                window.setLocationRelativeTo(null);
                window.setExtendedState(window.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                window.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
