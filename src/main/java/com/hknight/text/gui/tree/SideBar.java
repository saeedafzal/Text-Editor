package com.hknight.text.gui.tree;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class SideBar extends JPanel {

    public SideBar() {
        setLayout(new BorderLayout());
    }

    public void populateDirectory(File directory) {
        removeAll();

        SideBarPopup sideBarPopup = new SideBarPopup();

        DefaultMutableTreeNode root = new DefaultMutableTreeNode(directory.getName());
        JTree fileTree = new JTree(new DefaultTreeModel(getNodes(directory, root)));

        fileTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int row = fileTree.getClosestRowForLocation(e.getX(), e.getY());
                    fileTree.setSelectionRow(row);
                    sideBarPopup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

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
