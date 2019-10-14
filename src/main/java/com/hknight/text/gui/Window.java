package com.hknight.text.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import com.hknight.text.gui.menubar.CreateMenuBar;

public class Window extends JFrame {

    private static final String TITLE = "Text Editor";
    private final GlobalComp globalComp = GlobalComp.getInstance();
    private final JTabbedPane tabbedPane = new JTabbedPane();

    public Window() {
        globalComp.setWindow(this);

        this.setTitle(null);
        this.setSize(1280, 720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        contentPane.add(tabbedPane, BorderLayout.CENTER);
        this.setContentPane(contentPane);

        this.setJMenuBar(new CreateMenuBar());
        addNewTab();
    }

    public void addNewTab() {
        EditorPanel editorPanel = new EditorPanel();
        tabbedPane.addTab("Untitled", editorPanel);
        globalComp.putEditors(editorPanel);
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    @Override
    public void setTitle(String custom) {
        if (custom != null) {
            super.setTitle(custom + " - " + TITLE);
            return;
        }

        super.setTitle(TITLE);
    }
}
