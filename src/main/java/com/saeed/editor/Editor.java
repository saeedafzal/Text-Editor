package com.saeed.editor;

import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;
import com.saeed.editor.ui.Window;

public class Editor {

    public static void main(String[] args) {
        FlatLightLaf.setup();
        SwingUtilities.invokeLater(Window::new);
    }
}
