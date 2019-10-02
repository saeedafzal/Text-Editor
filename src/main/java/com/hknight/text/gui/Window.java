package com.hknight.text.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import com.hknight.text.gui.model.CompVault;
import com.hknight.text.model.lang.JavascriptProvider;

public class Window extends JFrame {

    private CompVault compVault = CompVault.getInstance();

    public Window() {
        compVault.setRoot(this);

        setTitle("Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        final JSplitPane splitPane = new JSplitPane();
        splitPane.setResizeWeight(0.25);
        splitPane.setLeftComponent(new SideBar());
        splitPane.setRightComponent(editorPanel());

        contentPane.add(splitPane, BorderLayout.CENTER);
        setContentPane(contentPane);

        setJMenuBar(new CreateMenuBar());
    }

    private JPanel editorPanel() {
        JPanel editorPanel = new JPanel();
        editorPanel.setLayout(new BorderLayout());

        RSyntaxTextArea textArea = new RSyntaxTextArea();
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
        textArea.setCodeFoldingEnabled(true);
        textArea.setTabSize(4);
        textArea.setTabsEmulated(true);

        AutoCompletion autoCompletion = new AutoCompletion(new JavascriptProvider());
        autoCompletion.install(textArea);

        editorPanel.add(new RTextScrollPane(textArea), BorderLayout.CENTER);
        compVault.setTextArea(textArea);
        return editorPanel;
    }
}
