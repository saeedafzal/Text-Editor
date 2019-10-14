package com.hknight.text.gui;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JPanel;

import org.fife.rsta.ac.LanguageSupportFactory;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

public class EditorPanel extends JPanel {

    private final TextEditor textEditor = new TextEditor();
    private File file;

    EditorPanel() {
        this.setLayout(new BorderLayout());

        add(new RTextScrollPane(createEditor()));
    }

    private TextEditor createEditor() {
        LanguageSupportFactory.get().register(textEditor);
        textEditor.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
        textEditor.setTabsEmulated(true);
        textEditor.setTabSize(4);
        return textEditor;
    }

    public TextEditor getTextEditor() {
        return textEditor;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
