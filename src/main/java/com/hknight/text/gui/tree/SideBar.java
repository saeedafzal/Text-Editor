package com.hknight.text.gui.tree;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class SideBar extends JPanel {

    public SideBar() {
        setLayout(new BorderLayout());
    }

    public void populateDirectory(File directory) {
        removeAll();

        DefaultMutableTreeNode root = new DefaultMutableTreeNode(directory.getName());
        JTree fileTree = new JTree(new DefaultTreeModel(getNodes(directory, root)));
        add(fileTree);

        revalidate();
        repaint();
    }

    private DefaultMutableTreeNode getNodes(File directory, DefaultMutableTreeNode root) {
        File[] files = directory.listFiles();

        if (files != null) {
            for (File f : files) {
                if (f.getName().startsWith(".")) {
                    continue;
                }
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(f.getName());

                root.add(node);
                if (f.isDirectory()) {
                    getNodes(f, node);
                }
            }
        }

        return root;
    }
}
