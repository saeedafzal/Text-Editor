package com.hknight.text.gui;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.hknight.text.gui.menubar.CreateMenuBar;
import com.hknight.text.gui.popup.IndentPopup;

public class Window extends JFrame {

    private final GlobalComp globalComp = GlobalComp.getInstance();
    private final JTabbedPane tabbedPane = new JTabbedPane();

    public Window() {
        globalComp.setWindow(this);

        this.setTitle("Text Editor");
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

        IndentPopup indentPopup = new IndentPopup();

        JButton button = new JButton("Indents");
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                indentPopup.show(e.getComponent(), e.getX(), e.getY());
            }
        });

        footerPanel.add(button, BorderLayout.EAST);
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
