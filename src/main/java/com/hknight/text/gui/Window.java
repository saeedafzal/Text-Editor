package com.hknight.text.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.hknight.text.gui.menubar.CreateMenuBar;

public class Window extends JFrame {

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
        contentPane.add(createFooter(), BorderLayout.SOUTH);
        this.setContentPane(contentPane);

        this.setJMenuBar(new CreateMenuBar());
        addNewTab();
    }

    private JPanel createFooter() {
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());

        return footerPanel;
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
}
