package com.hknight.text.gui;

import javax.swing.*;

final class CreateMenuBar extends JMenuBar {

    CreateMenuBar() {
        add(createFileMenu());
    }

    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");

        final JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(exitItem);
        return fileMenu;
    }
}
