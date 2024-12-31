package com.saeed.editor.ui.dialog.preferences;

import com.saeed.editor.event.Event;
import com.saeed.editor.event.EventBus;
import com.saeed.editor.ui.Window;
import com.saeed.editor.ui.dialog.preferences.theme.Themes;
import com.saeed.editor.ui.util.GlobalState;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;

public class PreferencesDialog extends JDialog {

    private static final EventBus eventBus = EventBus.getDefault();
    private final JPanel menuViewPanel;

    public PreferencesDialog(Window window) {
        super(window, "Preferences");

        GlobalState.preferencesDialog = this;

        setSize(700, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(window);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        menuViewPanel = new JPanel();
        menuViewPanel.setLayout(new CardLayout());
        menuViewPanel.add(appearanceNodePanel(), "Appearance");

        contentPane.add(mainPanel(), BorderLayout.CENTER);
        setContentPane(contentPane);

        eventBus.subscribe(Event.PREFERENCE_VIEW, (String name) -> ((CardLayout) menuViewPanel.getLayout()).show(menuViewPanel, name));
    }

    private JSplitPane mainPanel() {
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(0.2);

        splitPane.setLeftComponent(treeMenuPanel());
        splitPane.setRightComponent(menuViewPanel);
        return splitPane;
    }

    private JPanel treeMenuPanel() {
        JPanel treeMenuPanel = new JPanel();
        treeMenuPanel.setLayout(new BorderLayout());

        DefaultMutableTreeNode root = new DefaultMutableTreeNode();

        // Appearance
        DefaultMutableTreeNode appearanceNode = new DefaultMutableTreeNode("Appearance");

        root.add(appearanceNode);

        JTree tree = new JTree(root);
        tree.setRootVisible(false);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(new PreferencesTreeSelectionListener(tree));

        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) tree.getCellRenderer();
        renderer.setLeafIcon(null);
        renderer.setClosedIcon(null);
        renderer.setOpenIcon(null);

        treeMenuPanel.add(new JScrollPane(tree), BorderLayout.CENTER);
        return treeMenuPanel;
    }

    private JPanel appearanceNodePanel() {
        JPanel appearanceNodePanel = new JPanel();
        appearanceNodePanel.setLayout(new MigLayout());

        // Header
        JLabel titleLabel = new JLabel("Appearance");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD));
        appearanceNodePanel.add(titleLabel, "span");

        // Theme picker
        JComboBox<Object> themes = new JComboBox<>(Themes.getThemes());
        themes.addActionListener(new ThemeHandler());
        appearanceNodePanel.add(new JLabel("Theme:"));
        appearanceNodePanel.add(themes, "span");

        return appearanceNodePanel;
    }
}
