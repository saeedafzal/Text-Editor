package com.hknight.text.gui;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    public Window getWindow() {
        return window;
    }

    public List<EditorPanel> getEditors() {
        return editors;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public void putEditors(EditorPanel editorPanel) {
        editors.add(editorPanel);
    }

    public TextEditor getCurrentEditor() {
        return editors.get(window.getTabbedPane().getSelectedIndex()).getTextEditor();
    }
}
