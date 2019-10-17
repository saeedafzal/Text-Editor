package com.hknight.text.gui.menubar;

import javax.swing.JMenu;

class CreateThemeMenu extends JMenu {

    CreateThemeMenu() {
        this.setText("Theme");

        this.add(new CreateWindowMenu());
    }
}
