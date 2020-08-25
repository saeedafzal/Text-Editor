package com.hknight.editor.ui;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import com.hknight.editor.ui.menubar.EditorMenuBar;

import static com.hknight.editor.util.FileUtil.getFileExtension;
import static com.hknight.editor.util.SyntaxMap.getSyntaxFromExt;

public class Window extends JFrame {

    private static final RSyntaxTextArea TEXT_AREA = new RSyntaxTextArea();

    public Window() {
        setTitle("Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // TODO: Set maximised based on settings
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

        // Content pane initialisation
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        contentPane.add(editorPanel(), BorderLayout.CENTER);
        setContentPane(contentPane);

        // Menubar initialisation
        setJMenuBar(new EditorMenuBar(this));
    }

    public void readFile(File file) {
        try (FileReader fileReader = new FileReader(file)) {
            String extension = getFileExtension(file.getName()).orElse(null);
            if (extension == null) {
                extension = "plain";
            }

            TEXT_AREA.setSyntaxEditingStyle(getSyntaxFromExt(extension));

            TEXT_AREA.read(fileReader, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JPanel editorPanel() {
        JPanel editorPanel = new JPanel();
        editorPanel.setLayout(new BorderLayout());

        RTextScrollPane scrollPane = new RTextScrollPane(TEXT_AREA);

        editorPanel.add(scrollPane, BorderLayout.CENTER);
        return editorPanel;
    }
}
