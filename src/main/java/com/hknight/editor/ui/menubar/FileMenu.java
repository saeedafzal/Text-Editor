package com.hknight.editor.ui.menubar;

import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.hknight.editor.ui.Window;

public class FileMenu extends JMenu {

    private static final int MASK_KEY = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();
    private static final JFileChooser FILE_CHOOSER = new JFileChooser();
    private final Window window;

    public FileMenu(Window window) {
        super("File");
        this.window = window;

        add(newItem());
        add(openItem());
    }

    private JMenuItem newItem() {
        JMenuItem newItem = new JMenuItem("New");
        newItem.setAccelerator(KeyStroke.getKeyStroke('N', MASK_KEY));
        return newItem;
    }

    private JMenuItem openItem() {
        JMenuItem openItem = new JMenuItem("Open");
        openItem.setAccelerator(KeyStroke.getKeyStroke('O', MASK_KEY));

        openItem.addActionListener(e -> {
            int choice = FILE_CHOOSER.showOpenDialog(window);
            if (choice == JFileChooser.APPROVE_OPTION) {
                File file = FILE_CHOOSER.getSelectedFile();
                window.readFile(file);
            }
        });

        return openItem;
    }
}
