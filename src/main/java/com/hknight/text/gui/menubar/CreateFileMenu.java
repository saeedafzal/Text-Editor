package com.hknight.text.gui.menubar;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import com.hknight.text.gui.GlobalComp;

import static java.awt.event.KeyEvent.VK_O;

class CreateFileMenu extends JMenu {

    private static final int CTRL_MASK_KEY = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
    private final GlobalComp globalComp = GlobalComp.getInstance();
    private final JFileChooser chooser = new JFileChooser();

    CreateFileMenu() {
        this.setText("File");

        this.add(createOpenMenu());
        this.addSeparator();
        this.add(createExitItem());
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
                        globalComp.getTextArea().read(fileReader, file.getName());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        return openItem;
    }

    private JMenuItem createExitItem() {
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        return exitItem;
    }
}
