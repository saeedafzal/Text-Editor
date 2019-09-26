package com.hknight.text.gui;

import com.hknight.text.gui.model.CompVault;
import com.hknight.text.model.SyntaxParser;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.google.common.io.Files.getFileExtension;

final class CreateMenuBar extends JMenuBar {

    private CompVault compVault = CompVault.getInstance();
    private JFileChooser chooser = new JFileChooser();
    private SyntaxParser syntaxParser = new SyntaxParser();
    private int shortcutKeyMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

    CreateMenuBar() {
        add(createFileMenu());
    }

    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");

        final JMenuItem openItem = new JMenuItem("Open File");
        openItem.setAccelerator(KeyStroke.getKeyStroke('O', shortcutKeyMask));
        openItem.addActionListener(e -> {
            chooser.setDialogTitle("Open File");
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setMultiSelectionEnabled(false);
            chooser.setAcceptAllFileFilterUsed(true);

            int choice = chooser.showOpenDialog(null);
            if (choice == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                String extension = getFileExtension(file.getName());

                compVault.getTextArea().setSyntaxEditingStyle(syntaxParser.getSyntax(extension));
                try {
                    compVault.getTextArea().read(new FileReader(file), file);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        final JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(openItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        return fileMenu;
    }
}
