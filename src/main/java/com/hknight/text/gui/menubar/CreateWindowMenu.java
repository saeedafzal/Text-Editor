package com.hknight.text.gui.menubar;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import com.hknight.text.gui.GlobalComp;

class CreateWindowMenu extends JMenu {

    private final ButtonGroup buttonGroup = new ButtonGroup();
    private GlobalComp globalComp = GlobalComp.getInstance();

    CreateWindowMenu() {
        this.setText("Window");

        this.add(light());
        this.add(dark());
        this.add(intelliJ());
        this.add(darcula());
        this.add(defaultOS());
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

    private JRadioButtonMenuItem intelliJ() {
        JRadioButtonMenuItem intellijItem = new JRadioButtonMenuItem("IntelliJ");
        intellijItem.addActionListener(e -> changeUI(new FlatIntelliJLaf()));
        intellijItem.setSelected(true);

        buttonGroup.add(intellijItem);
        return intellijItem;
    }

    private JRadioButtonMenuItem darcula() {
        JRadioButtonMenuItem darculaItem = new JRadioButtonMenuItem("Darcula");
        darculaItem.addActionListener(e -> changeUI(new FlatDarculaLaf()));
        darculaItem.setSelected(true);

        buttonGroup.add(darculaItem);
        return darculaItem;
    }

    private void changeUI(LookAndFeel lookAndFeel) {
        try {
            UIManager.setLookAndFeel(lookAndFeel);
            SwingUtilities.updateComponentTreeUI(globalComp.getWindow());
            SwingUtilities.updateComponentTreeUI(globalComp.getFileChooser());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private void changeUI(String className) {
        try {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(globalComp.getWindow());
            SwingUtilities.updateComponentTreeUI(globalComp.getFileChooser());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
