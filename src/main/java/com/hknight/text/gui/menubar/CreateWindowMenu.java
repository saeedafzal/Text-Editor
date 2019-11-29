package com.hknight.text.gui.menubar;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import org.fife.ui.rsyntaxtextarea.Theme;

import com.hknight.text.gui.GlobalComp;
import com.hknight.text.model.ThemeStyle;

class CreateWindowMenu extends JMenu {

    private final ButtonGroup buttonGroup = new ButtonGroup();
    private GlobalComp globalComp = GlobalComp.getInstance();
    private Map<ThemeStyle, String> themeMap = new HashMap<>();

    CreateWindowMenu() {
        this.setText("Window");

        this.add(light());
        this.add(dark());
        this.add(defaultOS());

        themeMap.put(ThemeStyle.LIGHT, "/themes/default.xml");
        themeMap.put(ThemeStyle.DARK, "/themes/dark.xml");
    }

    private JRadioButtonMenuItem defaultOS() {
        JRadioButtonMenuItem defaultOSItem = new JRadioButtonMenuItem("Default OS");
        defaultOSItem.addActionListener(e -> changeUI(UIManager.getSystemLookAndFeelClassName()));

        buttonGroup.add(defaultOSItem);
        return defaultOSItem;
    }

    private JRadioButtonMenuItem light() {
        JRadioButtonMenuItem lightItem = new JRadioButtonMenuItem("Light");
        lightItem.addActionListener(e -> changeUI(new FlatLightLaf()));
        lightItem.setSelected(true);

        buttonGroup.add(lightItem);
        return lightItem;
    }

    private JRadioButtonMenuItem dark() {
        JRadioButtonMenuItem darkItem = new JRadioButtonMenuItem("Dark");
        darkItem.addActionListener(e -> changeUI(new FlatDarkLaf()));
        darkItem.setSelected(true);

        buttonGroup.add(darkItem);
        return darkItem;
    }

    private void changeUI(LookAndFeel lookAndFeel) {
        try {
            UIManager.setLookAndFeel(lookAndFeel);
            SwingUtilities.updateComponentTreeUI(globalComp.getWindow());
            SwingUtilities.updateComponentTreeUI(globalComp.getFileChooser());

            changeTheme(lookAndFeel instanceof FlatDarkLaf ? ThemeStyle.DARK : ThemeStyle.LIGHT);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private void changeUI(String className) {
        try {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(globalComp.getWindow());
            SwingUtilities.updateComponentTreeUI(globalComp.getFileChooser());

            changeTheme(ThemeStyle.LIGHT);
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void changeTheme(ThemeStyle themeStyle) {
        try {
            Theme theme = Theme.load(getClass().getResourceAsStream(themeMap.get(themeStyle)));
            globalComp.getEditors().forEach(editor -> theme.apply(editor.getTextEditor()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
