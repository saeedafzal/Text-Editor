package com.hknight.editor;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.formdev.flatlaf.FlatLightLaf;

import com.hknight.editor.ui.Window;

public class Launch {

    private static final Logger LOG = LoggerFactory.getLogger(Launch.class);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // TODO: Set the initial LAF from the settings
                UIManager.setLookAndFeel(new FlatLightLaf());

                final Window window = new Window();
                window.setVisible(true);
            } catch (UnsupportedLookAndFeelException e) {
                LOG.error("Look and feel is not supported: {}", e.getMessage(), e);
            }
        });
    }
}
