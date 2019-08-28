package com.hknight.text.gui;

import javax.swing.*;

public class Window extends JFrame {

    public Window() {
        setTitle("Text Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setJMenuBar(new CreateMenuBar());
    }
}
