package com.hknight.text.gui.model;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import java.io.File;

public class CompVault {

    private static CompVault instance;
    private RSyntaxTextArea textArea;
    private File file;

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

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
