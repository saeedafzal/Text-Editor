package com.saeed.editor.ui.dialog.preferences.theme;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.saeed.editor.event.Event;
import com.saeed.editor.event.EventBus;
import com.saeed.editor.ui.util.GlobalState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.util.Locale;

import static java.util.Arrays.stream;

@Slf4j
@RequiredArgsConstructor
public enum Themes {

    LIGHT("Light", () -> updateTheme(new FlatLightLaf(), "LIGHT")),
    DARK("Dark", () -> updateTheme(new FlatDarkLaf(), "DARK")),
    NATIVE("Native", () -> updateTheme(UIManager.getSystemLookAndFeelClassName()));

    private final String themeName;
    private final Runnable task;

    public static Object[] getThemes() {
        return stream(Themes.values()).map(theme -> theme.themeName).toArray();
    }

    public static void apply(String theme) {
        Themes t = Themes.valueOf(theme.toUpperCase(Locale.ROOT));
        t.task.run();
    }

    private static void updateTheme(LookAndFeel lookAndFeel, String theme) {
        try {
            UIManager.setLookAndFeel(lookAndFeel);
            GlobalState.components().forEach(SwingUtilities::updateComponentTreeUI);
            EventBus.getDefault().publish(Event.THEME, Themes.valueOf(theme));
        } catch (UnsupportedLookAndFeelException e) {
            log.error("Failed to apply look and feel: {}", e.getMessage(), e);
        }
    }

    private static void updateTheme(String lookAndFeel) {
        try {
            UIManager.setLookAndFeel(lookAndFeel);
            GlobalState.components().forEach(SwingUtilities::updateComponentTreeUI);
            EventBus.getDefault().publish(Event.THEME, Themes.valueOf("NATIVE"));
        } catch (Exception e) {
            log.error("Failed to apply look and feel: {}", e.getMessage(), e);
        }
    }
}
