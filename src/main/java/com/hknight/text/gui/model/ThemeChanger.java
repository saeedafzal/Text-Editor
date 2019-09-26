package com.hknight.text.gui.model;

import org.fife.ui.rsyntaxtextarea.Theme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class ThemeChanger implements ActionListener {

    private final File themeFile;
    private CompVault compVault = CompVault.getInstance();

    public ThemeChanger(File themeFile) {
        this.themeFile = themeFile;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            FileInputStream inputStream = new FileInputStream(themeFile);
            Theme theme = Theme.load(inputStream);
            theme.apply(compVault.getTextArea());
            inputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
