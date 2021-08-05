package com.saeed.editor.ui.dialog.preferences.theme;

import java.util.Locale;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.saeed.editor.event.Event;
import com.saeed.editor.event.EventBus;
import com.saeed.editor.ui.util.GlobalCompRef;

import lombok.extern.slf4j.Slf4j;

import static java.util.Arrays.stream;

@Slf4j
public enum Themes {

    LIGHT("Light", () -> {
        updateTheme(new FlatLightLaf(), "LIGHT");
    }),

    DARK("Dark", () -> {
        updateTheme(new FlatDarkLaf(), "DARK");
    }),

    NATIVE("Native", () -> {
        updateTheme(UIManager.getSystemLookAndFeelClassName(), "NATIVE");
    });

    private final String themeName;
    private final Runnable task;

    Themes(String themeName, Runnable task) {
        this.themeName = themeName;
        this.task = task;
    }

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
            GlobalCompRef.components().forEach(SwingUtilities::updateComponentTreeUI);
            EventBus.getDefault().publish(Event.THEME, Themes.valueOf(theme));
        } catch (UnsupportedLookAndFeelException e) {
            log.error("Failed to apply look and feel: {}", e.getMessage(), e);
        }
    }

    private static void updateTheme(String lookAndFeel, String theme) {
        try {
            UIManager.setLookAndFeel(lookAndFeel);
            GlobalCompRef.components().forEach(SwingUtilities::updateComponentTreeUI);
            EventBus.getDefault().publish(Event.THEME, Themes.valueOf(theme));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            log.error("Failed to apply look and feel: {}", e.getMessage(), e);
        }
    }
}
