package com.hknight.text.gui.tree;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

class SideBarPopup extends JPopupMenu {

    SideBarPopup() {
        createFileUtil();
    }

    private void createFileUtil() {
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem pasteItem = new JMenuItem("Paste");

        add(copyItem);
        add(cutItem);
        add(pasteItem);
    }
}
