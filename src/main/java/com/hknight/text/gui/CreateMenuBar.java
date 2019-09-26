package com.hknight.text.gui;

import com.hknight.text.gui.model.CompVault;
import com.hknight.text.model.SyntaxParser;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static com.google.common.io.Files.getFileExtension;

final class CreateMenuBar extends JMenuBar {

    private CompVault compVault = CompVault.getInstance();
    private JFileChooser chooser = new JFileChooser();
    private SyntaxParser syntaxParser = new SyntaxParser();
    private int shortcutKeyMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
    private File previousFile;
    private FileWriter saveWriter = null;

    CreateMenuBar() {
        add(createFileMenu());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (saveWriter != null) {
                try {
                    System.out.println("Closing file resources.");
                    saveWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));
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

                FileReader fileWriter = null;

                try {
                    fileWriter = new FileReader(file);
                    compVault.getTextArea().read(fileWriter, file);
                    compVault.setFile(file);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        if (fileWriter != null) {
                            fileWriter.close();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        final JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setAccelerator(KeyStroke.getKeyStroke('S', shortcutKeyMask));
        saveItem.addActionListener(e -> {
            if (compVault.getFile() != null) {
                if (compVault.getFile().exists()) {

                    try {
                        if (previousFile == null) saveWriter = new FileWriter(compVault.getFile());
                        compVault.getTextArea().write(saveWriter);
                        saveWriter.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                // New file
                chooser.setDialogTitle("Save new file...");
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);
                chooser.setMultiSelectionEnabled(false);

                int choice = chooser.showSaveDialog(null);
                if (choice == JFileChooser.APPROVE_OPTION) {
                    File createdFile = chooser.getSelectedFile();
                    String extension = getFileExtension(createdFile.getName());

                    compVault.getTextArea().setSyntaxEditingStyle(syntaxParser.getSyntax(extension));
                    compVault.setFile(createdFile);
                    previousFile = createdFile;

                    if (saveWriter != null) {
                        try {
                            saveWriter.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        try {
                            saveWriter = new FileWriter(createdFile);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }

                    try {
                        saveWriter.write(compVault.getTextArea().getText());
                        saveWriter.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        final JMenuItem closeFileItem = new JMenuItem("Close File");
        closeFileItem.addActionListener(e -> {
            compVault.getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
            compVault.getTextArea().setText("");
            previousFile = null;
            compVault.setFile(null);
            try {
                saveWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        final JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(closeFileItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        return fileMenu;
    }
}
