package com.saeed.editor.ui.util;

import java.io.File;

import javax.swing.JFileChooser;

public class FileChooserUtil {

    private static final JFileChooser FILE_CHOOSER = new JFileChooser();

    public static File display(String title, int dialogType) {
        FILE_CHOOSER.setDialogTitle(title);
        FILE_CHOOSER.setAcceptAllFileFilterUsed(true); // TODO: Have list of language extensions
        int choice = dialogType == JFileChooser.SAVE_DIALOG
            ? FILE_CHOOSER.showSaveDialog(GlobalState.window)
            : FILE_CHOOSER.showOpenDialog(GlobalState.window);
        if (choice == JFileChooser.APPROVE_OPTION) {
            return FILE_CHOOSER.getSelectedFile();
        }
        return null;
    }
}
