package com.hknight.text.gui;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import com.hknight.text.gui.model.AppearanceChanger;
import com.hknight.text.gui.model.CompVault;
import com.hknight.text.gui.model.ThemeChanger;
import com.hknight.text.model.SyntaxParser;
import com.hknight.text.model.Themes;

import static com.google.common.io.Files.getFileExtension;

final class CreateMenuBar extends JMenuBar {

    private CompVault compVault = CompVault.getInstance();
    private JFileChooser chooser = new JFileChooser();
    private SyntaxParser syntaxParser = new SyntaxParser();
    private Themes themes = new Themes();
    private File previousFile;
    private FileWriter saveWriter = null;
    private int shortcutKeyMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

    CreateMenuBar() {
        add(createFileMenu());
        add(createViewMenu());
        add(createFormatMenu());

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

            int choice = chooser.showOpenDialog(compVault.getRoot());
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

        final JMenuItem openDirectoryItem = new JMenuItem("Open Project Directory");
        openDirectoryItem.addActionListener(e -> {
            chooser.setDialogTitle("Open Project Directory");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setMultiSelectionEnabled(false);
            chooser.setAcceptAllFileFilterUsed(false);

            int choice = chooser.showOpenDialog(compVault.getRoot());

            if (choice == JFileChooser.APPROVE_OPTION) {
                File directory = chooser.getSelectedFile();
                compVault.getSideBar().populateDirectory(directory);
            }
        });

        final JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setAccelerator(KeyStroke.getKeyStroke('S', shortcutKeyMask));
        saveItem.addActionListener(e -> {
            if (compVault.getFile() != null) {
                if (compVault.getFile().exists()) {
                    try {
                        if (previousFile == null) saveWriter = new FileWriter(compVault.getFile(), false);
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
                            saveWriter = new FileWriter(createdFile, false);
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

                    compVault.getRoot().setTitle("Text Editor - " + createdFile.getName());
                }
            }
        });

        final JMenuItem closeFileItem = new JMenuItem("Close File");
        closeFileItem.addActionListener(e -> {
            if (saveWriter != null) {
                compVault.getTextArea().setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
                compVault.getTextArea().setText("");
                previousFile = null;
                compVault.setFile(null);
                try {
                    saveWriter.close();
                    saveWriter = null;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                compVault.getRoot().setTitle("Text Editor");
            }
        });

        final JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(openItem);
        fileMenu.add(openDirectoryItem);
        fileMenu.addSeparator();
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(closeFileItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        return fileMenu;
    }

    private JMenu createViewMenu() {
        JMenu viewMenu = new JMenu("View");

        // Window Appearance
        JMenu appearance = new JMenu("Appearance");

        AppearanceChanger appearanceChanger = new AppearanceChanger(themes, chooser);

        JMenuItem defaultItem = new JMenuItem("Default");
        defaultItem.setActionCommand("default");
        defaultItem.addActionListener(appearanceChanger);
        appearance.add(defaultItem);

        themes.getAppearance().keySet().forEach(theme -> {
            JMenuItem item = new JMenuItem(theme);
            item.setActionCommand(theme);
            item.addActionListener(appearanceChanger);
            appearance.add(item);
        });

        // Themes
        JMenu themeMenu = new JMenu("Editor Theme");
        themes.getThemes().forEach(theme -> {
            JMenuItem item = new JMenuItem(theme);
            item.setActionCommand(theme);
            item.addActionListener(new ThemeChanger());
            themeMenu.add(item);
        });

        viewMenu.add(appearance);
        viewMenu.add(themeMenu);
        return viewMenu;
    }

    private JMenu createFormatMenu() {
        JMenu formatMenu = new JMenu("Format");

        JPanel fontSizePanel = new JPanel();
        JSpinner spinner = new JSpinner();
        spinner.setValue(compVault.getTextArea().getFont().getSize());
        fontSizePanel.add(new JLabel("Font Size: "));
        fontSizePanel.add(spinner);

        JMenuItem fontSizeItem = new JMenuItem("Font Size");
        fontSizeItem.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(compVault.getRoot(), fontSizePanel, "Change Font Size", JOptionPane.OK_CANCEL_OPTION);
            if (choice == JOptionPane.OK_OPTION) {
                compVault.getTextArea().setFont(compVault
                        .getTextArea()
                        .getFont()
                        .deriveFont(Float.parseFloat(spinner.getValue().toString())));
            }
        });

        formatMenu.add(fontSizeItem);
        return formatMenu;
    }
}
