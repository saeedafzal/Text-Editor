package com.hknight.text.gui.model;

import java.io.File;

import javax.swing.JFrame;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

public class CompVault {

    private static CompVault instance;
    private JFrame root;
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

    public JFrame getRoot() {
        return root;
    }

    public void setRoot(JFrame root) {
        this.root = root;
    }
}
