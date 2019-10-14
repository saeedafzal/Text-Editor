package com.hknight.text.gui.menubar;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import com.hknight.text.gui.GlobalComp;

import static java.awt.event.KeyEvent.VK_N;
import static java.awt.event.KeyEvent.VK_O;
import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_W;

class CreateFileMenu extends JMenu {

    private static final int CTRL_MASK_KEY = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
    private final GlobalComp globalComp = GlobalComp.getInstance();
    private final JFileChooser chooser = new JFileChooser();

    CreateFileMenu() {
        this.setText("File");

        this.add(createNewItem());
        this.addSeparator();
        this.add(createOpenMenu());
        this.add(createCloseTabMenu());
        this.addSeparator();
        this.add(createSaveItem());
        this.addSeparator();
        this.add(createExitItem());
    }

    private JMenuItem createNewItem() {
        JMenuItem newItem = new JMenuItem("New");
        newItem.setAccelerator(KeyStroke.getKeyStroke(VK_N, CTRL_MASK_KEY));
        newItem.addActionListener(e -> globalComp.getWindow().addNewTab());

        return newItem;
    }

    private JMenuItem createOpenMenu() {
        JMenuItem openItem = new JMenuItem("Open File");
        openItem.setAccelerator(KeyStroke.getKeyStroke(VK_O, CTRL_MASK_KEY));

        openItem.addActionListener(e -> {
            // Set chooser settings
            chooser.setDialogTitle("Open File");
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setAcceptAllFileFilterUsed(true);
            chooser.setMultiSelectionEnabled(false); // Enable when tabs are implemented

            int choice = chooser.showOpenDialog(globalComp.getWindow());

            if (choice == JFileChooser.APPROVE_OPTION) {
                final File file = chooser.getSelectedFile();
                if (file.isFile()) {
                    try (FileReader fileReader = new FileReader(file)) {
                        globalComp.getWindow().addNewTab();
                        JTabbedPane tabbedPane = globalComp.getWindow().getTabbedPane();
                        globalComp.getEditors().get(tabbedPane.getSelectedIndex()).getTextEditor().read(fileReader, file.getName());
                        globalComp.getEditors().get(tabbedPane.getSelectedIndex()).setFile(file);
                        tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), file.getName());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        return openItem;
    }

    private JMenuItem createCloseTabMenu() {
        JMenuItem closeTabItem = new JMenuItem("Close Tab");
        closeTabItem.setAccelerator(KeyStroke.getKeyStroke(VK_W, CTRL_MASK_KEY));

        closeTabItem.addActionListener(e -> {
            JTabbedPane tabbedPane = globalComp.getWindow().getTabbedPane();
            globalComp.getEditors().remove(tabbedPane.getSelectedIndex());
            tabbedPane.removeTabAt(tabbedPane.getSelectedIndex());
            if (tabbedPane.getTabCount() == 0) {
                System.exit(0);
            }
        });

        return closeTabItem;
    }

    private JMenuItem createSaveItem() {
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setAccelerator(KeyStroke.getKeyStroke(VK_S, CTRL_MASK_KEY));

        saveItem.addActionListener(e -> {
            int index = globalComp.getWindow().getTabbedPane().getSelectedIndex();

            if (globalComp.getEditors().get(index).getFile() != null) {
                // File exists
                try (FileWriter writer = new FileWriter(globalComp.getEditors().get(index).getFile())) {
                    globalComp.getEditors().get(index).getTextEditor().write(writer);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                // Save new file
                // Set chooser settings
                chooser.setDialogTitle("Save File");
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooser.setAcceptAllFileFilterUsed(true);

                int choice = chooser.showSaveDialog(globalComp.getWindow());

                if (choice == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    try (FileWriter writer = new FileWriter(file)) {
                        file.createNewFile();
                        globalComp.getEditors().get(index).setFile(file);
                        globalComp.getEditors().get(index).getTextEditor().saveAsSetSyntax(writer, file.getName());
                        globalComp.getWindow().getTabbedPane().setTitleAt(index, file.getName());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        return saveItem;
    }

    private JMenuItem createExitItem() {
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        return exitItem;
    }
}
