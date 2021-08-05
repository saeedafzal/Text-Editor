package com.saeed.editor.ui;

import java.awt.BorderLayout;
import java.awt.Desktop;

import javax.swing.*;

import com.saeed.editor.ui.dialog.preferences.PreferencesDialog;
import com.saeed.editor.ui.menu.EditorMenuBar;
import com.saeed.editor.ui.util.GlobalCompRef;

public class Window extends JFrame {

    private final PreferencesDialog preferencesDialog;

    public Window() {
        GlobalCompRef.window = this;
        preferencesDialog = new PreferencesDialog(this);

        setFrameProperties();
        setDesktop();

        TabbedEditorPane tabbedEditorPane = new TabbedEditorPane();

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(tabbedEditorPane.getTabbedPane(), BorderLayout.CENTER);
        setContentPane(contentPane);

        tabbedEditorPane.generateNewEditorTab();
    }

    private void setFrameProperties() {
        setTitle("Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void setDesktop() {
        Desktop desktop = Desktop.getDesktop();
        desktop.setDefaultMenuBar(new EditorMenuBar());
        desktop.setAboutHandler(e -> JOptionPane.showMessageDialog(this, "Editor - vX.X"));
        desktop.setPreferencesHandler(e -> preferencesDialog.setVisible(true));
    }
}
