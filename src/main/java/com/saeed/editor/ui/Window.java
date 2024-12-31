package com.saeed.editor.ui;

import java.awt.BorderLayout;
import java.awt.Desktop;

import javax.swing.*;

import com.saeed.editor.ui.dialog.preferences.PreferencesDialog;
import com.saeed.editor.ui.menu.EditorMenuBar;
import com.saeed.editor.ui.util.GlobalState;

import static com.formdev.flatlaf.util.SystemInfo.isMacFullWindowContentSupported;

public class Window extends JFrame {

    private final PreferencesDialog preferencesDialog;

    public Window() {
        GlobalState.window = this;
        preferencesDialog = new PreferencesDialog(this);

        setFrameProperties();
        setDesktop();

        TabbedEditorPane tabbedEditorPane = new TabbedEditorPane();

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(tabbedEditorPane.getTabbedPane(), BorderLayout.CENTER);
        setContentPane(contentPane);

        tabbedEditorPane.generateNewEditorTab();

        setVisible(true);
    }

    private void setFrameProperties() {
        setTitle("Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void setDesktop() {
        EditorMenuBar editorMenuBar = new EditorMenuBar();

        Desktop desktop = Desktop.getDesktop();
        if (isMacFullWindowContentSupported) {
            desktop.setDefaultMenuBar(editorMenuBar);
            desktop.setAboutHandler(_ -> JOptionPane.showMessageDialog(this, "Editor - vX.X"));
            desktop.setPreferencesHandler(_ -> preferencesDialog.setVisible(true));
        } else {
            setJMenuBar(editorMenuBar);
        }
    }
}
