package com.hknight.text.gui;

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
    private TextEditor textArea;

    public Window getWindow() {
        return window;
    }

    public TextEditor getTextArea() {
        return textArea;
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public void setTextArea(TextEditor textArea) {
        this.textArea = textArea;
    }
}
