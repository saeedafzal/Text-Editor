package com.hknight.text.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import com.hknight.text.gui.menubar.CreateMenuBar;

public class Window extends JFrame {

    private final TextEditor textArea = new TextEditor();

    public Window() {
        GlobalComp globalComp = GlobalComp.getInstance();
        globalComp.setWindow(this);
        globalComp.setTextArea(textArea);

        this.setTitle("Text Editor");
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        contentPane.add(createEditorPanel(), BorderLayout.CENTER);
        this.setContentPane(contentPane);

        this.setJMenuBar(new CreateMenuBar());
    }

    private JPanel createEditorPanel() {
        JPanel editorPanel = new JPanel();
        editorPanel.setLayout(new BorderLayout());

        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);

        editorPanel.add(new RTextScrollPane(textArea));
        return editorPanel;
    }
}
