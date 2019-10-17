package com.hknight.text.gui;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JFileChooser;

public class GlobalComp {

    private static GlobalComp instance;

    public static GlobalComp getInstance() {
        if (instance == null) {
            instance = new GlobalComp();
        }

        return instance;
    }

    // ---------------------------------------

    private Window window;
    private List<EditorPanel> editors = new LinkedList<>();
    private JFileChooser fileChooser;

    public Window getWindow() {
        return window;
    }

    public List<EditorPanel> getEditors() {
        return editors;
    }

    void setWindow(Window window) {
        this.window = window;
    }

    void putEditors(EditorPanel editorPanel) {
        editors.add(editorPanel);
    }

    public TextEditor getCurrentEditor() {
        return editors.get(window.getTabbedPane().getSelectedIndex()).getTextEditor();
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    public void setFileChooser(JFileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }
}
