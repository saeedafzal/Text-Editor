package com.saeed.editor.ui.util;

import java.awt.Component;
import java.util.List;

import com.saeed.editor.ui.Window;
import com.saeed.editor.ui.dialog.preferences.PreferencesDialog;

import static java.util.Arrays.asList;

public class GlobalCompRef {

    public static Window window;
    public static PreferencesDialog preferencesDialog;

    public static List<Component> components() {
        return asList(window, preferencesDialog);
    }
}
