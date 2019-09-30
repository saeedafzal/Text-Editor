package com.hknight.text.gui.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import org.fife.ui.rsyntaxtextarea.Theme;

public final class ThemeChanger implements ActionListener {

    private CompVault compVault = CompVault.getInstance();

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            InputStream stream = this.getClass().getResourceAsStream("/themes/" + e.getActionCommand());
            Theme theme = Theme.load(stream);
            theme.apply(compVault.getTextArea());
            stream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
