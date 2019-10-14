package com.hknight.text.gui.popup;

import javax.swing.ButtonGroup;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;

import com.hknight.text.gui.GlobalComp;
import com.hknight.text.gui.TextEditor;

public class IndentPopup extends JPopupMenu {

    private final GlobalComp globalComp = GlobalComp.getInstance();
    private final ButtonGroup buttonGroup = new ButtonGroup();

    public IndentPopup() {
        // Default
        add(default4Spaces());
        add(default2Spaces());
        add(custom());
    }

    private JRadioButtonMenuItem default4Spaces() {
        JRadioButtonMenuItem item = new JRadioButtonMenuItem("4 Spaces");
        item.setSelected(true);
        item.addActionListener(e -> {
            TextEditor editor = globalComp.getCurrentEditor();
            editor.setTabsEmulated(true);
            editor.setTabSize(4);
        });

        buttonGroup.add(item);

        return item;
    }

    private JRadioButtonMenuItem default2Spaces() {
        JRadioButtonMenuItem item = new JRadioButtonMenuItem("2 Spaces");
        item.addActionListener(e -> {
            TextEditor editor = globalComp.getCurrentEditor();
            editor.setTabsEmulated(true);
            editor.setTabSize(2);
        });

        buttonGroup.add(item);

        return item;
    }

    private JRadioButtonMenuItem custom() {
        JRadioButtonMenuItem item = new JRadioButtonMenuItem("Custom");

        buttonGroup.add(item);

        return item;
    }
}
