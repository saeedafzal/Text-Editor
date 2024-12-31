package com.saeed.editor.ui.dialog.preferences;

import com.saeed.editor.event.Event;
import com.saeed.editor.event.EventBus;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class PreferencesTreeSelectionListener implements TreeSelectionListener {

    private static final EventBus eventBus = EventBus.getDefault();
    private final JTree tree;

    public PreferencesTreeSelectionListener(JTree tree) {
        this.tree = tree;
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (node != null) {
            String nodeInfo = (String) node.getUserObject();
            eventBus.publish(Event.PREFERENCE_VIEW, nodeInfo);
        }
    }
}
