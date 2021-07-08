package com.saeed.editor.ui.menu;

import javax.swing.JMenuBar;

public class EditorMenuBar extends JMenuBar {
    public EditorMenuBar() {
        add(new FileMenu());
    }
}
