package com.saeed.editor.ui.menu;

import java.awt.Toolkit;
import java.io.File;

import javax.swing.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saeed.editor.event.Event;
import com.saeed.editor.event.EventBus;
import com.saeed.editor.ui.util.FileChooserUtil;

import static java.awt.event.InputEvent.SHIFT_DOWN_MASK;

public class FileMenu extends JMenu {

    private static final Logger LOG = LoggerFactory.getLogger(FileMenu.class);
    private static final int MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();
    private final EventBus eventBus;

    public FileMenu() {
        super("File");
        this.eventBus = EventBus.getDefault();

        add(newItem());
        add(openItem());
        add(closeFileItem());
        addSeparator();
        add(saveItem());
        add(saveAsItem());
        addSeparator();
        add(exitItem());
    }

    private JMenuItem newItem() {
        JMenuItem newItem = new JMenuItem("New");
        newItem.setAccelerator(KeyStroke.getKeyStroke('N', MASK));
        newItem.addActionListener(e -> EventBus.getDefault().publish(Event.GENERATE_NEW_TAB));
        return newItem;
    }

    private JMenuItem openItem() {
        JMenuItem openItem = new JMenuItem("Open");
        openItem.setAccelerator(KeyStroke.getKeyStroke('O', MASK));
        openItem.addActionListener(e -> {
            File file = FileChooserUtil.display("Open File", JFileChooser.OPEN_DIALOG);
            if (file != null) {
                LOG.info("Opened file: {}", file.getName());
                eventBus.publish(Event.OPEN_FILE, file);
            }
        });
        return openItem;
    }

    private JMenuItem closeFileItem() {
        JMenuItem newItem = new JMenuItem("Close File");
        newItem.setAccelerator(KeyStroke.getKeyStroke('W', MASK));
        newItem.addActionListener(e -> eventBus.publish(Event.CLOSE_CURRENT_TAB));
        return newItem;
    }

    private JMenuItem saveItem() {
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.setAccelerator(KeyStroke.getKeyStroke('S', MASK));
        saveItem.addActionListener(e -> eventBus.publish(Event.SAVE_FILE));
        return saveItem;
    }

    private JMenuItem saveAsItem() {
        JMenuItem saveAsItem = new JMenuItem("Save As");
        saveAsItem.setAccelerator(KeyStroke.getKeyStroke('S', MASK + SHIFT_DOWN_MASK));
        saveAsItem.addActionListener(e -> {
            File file = FileChooserUtil.display("Save File", JFileChooser.SAVE_DIALOG);
            if (file != null) {
                LOG.info("Selected file: {}", file.getName());
                eventBus.publish(Event.SAVE_FILE, file);
            }
        });
        return saveAsItem;
    }

    private JMenuItem exitItem() {
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        return exitItem;
    }
}
