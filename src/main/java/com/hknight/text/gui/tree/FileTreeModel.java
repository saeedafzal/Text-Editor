package com.hknight.text.gui.tree;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class FileTreeModel implements TreeModel {

    private final File root;

    FileTreeModel(File root) {
        this.root = root;
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object o, int i) {
        File f = (File) o;
        return Objects.requireNonNull(f.listFiles())[i];
    }

    @Override
    public int getChildCount(Object o) {
        File f = (File) o;
        return f.isDirectory() ? Objects.requireNonNull(f.list()).length : 0;
    }

    @Override
    public boolean isLeaf(Object o) {
        File f = (File) o;
        return !f.isDirectory();
    }

    @Override
    public void valueForPathChanged(TreePath treePath, Object o) {
        File oldFile = (File) o;
        System.out.println(oldFile.getPath());
    }

    @Override
    public int getIndexOfChild(Object o, Object o1) {
        File p = (File) o;
        File c = (File) o1;
        return Arrays.asList(Objects.requireNonNull(p.listFiles())).indexOf(c);
    }

    @Override
    public void addTreeModelListener(TreeModelListener treeModelListener) {}

    @Override
    public void removeTreeModelListener(TreeModelListener treeModelListener) {}
}
