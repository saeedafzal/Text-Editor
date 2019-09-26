package com.hknight.text.gui;

import com.hknight.text.gui.model.CompVault;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private CompVault compVault = CompVault.getInstance();

    public Window() {
        setTitle("Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setJMenuBar(new CreateMenuBar());

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        contentPane.add(editorPanel(), BorderLayout.CENTER);
        setContentPane(contentPane);
    }

    private JPanel editorPanel() {
        JPanel editorPanel = new JPanel();
        editorPanel.setLayout(new BorderLayout());

        RSyntaxTextArea textArea = new RSyntaxTextArea();
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
        textArea.setCodeFoldingEnabled(true);
        textArea.setTabSize(4);
        textArea.setTabsEmulated(true);

        editorPanel.add(new RTextScrollPane(textArea), BorderLayout.CENTER);
        compVault.setTextArea(textArea);
        return editorPanel;
    }
}
