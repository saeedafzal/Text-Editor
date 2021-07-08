package com.saeed.editor.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.saeed.editor.ui.menu.EditorMenuBar;
import com.saeed.editor.ui.util.GlobalCompRef;

public class Window extends JFrame {

    public Window() {
        TabbedEditorPane tabbedEditorPane = new TabbedEditorPane();
        GlobalCompRef.window = this;

        setFrameProperties();
        setJMenuBar(new EditorMenuBar());

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(tabbedEditorPane.getTabbedPane(), BorderLayout.CENTER);
        setContentPane(contentPane);

        tabbedEditorPane.generateNewEditorTab();
    }

    private void setFrameProperties() {
        setTitle("Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
