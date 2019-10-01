package com.hknight.text.gui.model;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.hknight.text.model.Themes;

public class AppearanceChanger implements ActionListener {

    private final Themes themes;
    private final JFileChooser chooser;

    public AppearanceChanger(Themes themes, JFileChooser chooser) {
        this.themes = themes;
        this.chooser = chooser;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            if (actionEvent.getActionCommand().equalsIgnoreCase("default")) {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } else {
                UIManager.setLookAndFeel(themes.getAppearance().get(actionEvent.getActionCommand()));
            }

            for (Window w : Window.getWindows()) {
                SwingUtilities.updateComponentTreeUI(w);
            }

            SwingUtilities.updateComponentTreeUI(chooser);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
