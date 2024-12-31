package com.saeed.editor.ui.menu;

import javax.swing.*;

public class EditorMenuBar extends JMenuBar {

    public EditorMenuBar() {
        add(new FileMenu());
        add(new HelpMenu());
    }
}
