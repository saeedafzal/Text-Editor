package com.hknight.editor.ui.menubar;

import javax.swing.JMenuBar;

import com.hknight.editor.ui.Window;

public class EditorMenuBar extends JMenuBar {

    public EditorMenuBar(Window window) {
        add(new FileMenu(window));
    }
}
