package com.saeed.editor.ui.dialog.preferences;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import com.saeed.editor.ui.dialog.preferences.theme.Themes;

public class ThemeHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox<?> comboBox = (JComboBox<?>) e.getSource();
        String theme = (String) comboBox.getSelectedItem();
        if (theme != null) {
            Themes.apply(theme);
        }
    }
}
