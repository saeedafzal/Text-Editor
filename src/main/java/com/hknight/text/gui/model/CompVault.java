package com.hknight.text.gui.model;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class CompVault {

    private static CompVault instance;
    private RSyntaxTextArea textArea;

    public static CompVault getInstance() {
        if (instance == null) {
            instance = new CompVault();
        }

        return instance;
    }

    public RSyntaxTextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(RSyntaxTextArea textArea) {
        this.textArea = textArea;
    }
}
