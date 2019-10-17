package com.hknight.text.gui.menubar;

import javax.swing.JMenuBar;

public final class CreateMenuBar extends JMenuBar {

    public CreateMenuBar() {
        this.add(new CreateFileMenu());
        this.add(new CreateThemeMenu());
    }
}
