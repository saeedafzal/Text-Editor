package com.saeed.editor.ui;

import java.awt.BorderLayout;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;

import com.saeed.editor.event.Event;
import com.saeed.editor.event.EventBus;
import com.saeed.editor.model.FileData;
import com.saeed.editor.ui.dialog.preferences.theme.Themes;
import com.saeed.editor.ui.util.FileChooserUtil;
import com.saeed.editor.ui.util.GlobalCompRef;

import lombok.extern.slf4j.Slf4j;

import static com.saeed.editor.model.SyntaxMap.SYNTAX_MAP;

@Slf4j
public class TabbedEditorPane {

    private static final String DEFAULT_TITLE = "Untitled";
    private final JTabbedPane tabbedPane;
    private final List<FileData> fileDataList = new ArrayList<>();

    public TabbedEditorPane() {
        this.tabbedPane = new JTabbedPane();

        EventBus eventBus = EventBus.getDefault();
        eventBus.subscribe(Event.GENERATE_NEW_TAB, (Object a) -> generateNewEditorTab());
        eventBus.subscribe(Event.CLOSE_CURRENT_TAB, (Object a) -> closeCurrentTab());
        eventBus.subscribe(Event.SAVE_FILE, this::saveTextToFile);
        eventBus.subscribe(Event.UNSAVED_TITLE, this::tabNameUnsaved);
        eventBus.subscribe(Event.OPEN_FILE, this::openFile);
        eventBus.subscribe(Event.THEME, this::updateTheme);
    }

    public void generateNewEditorTab(String title, RSyntaxTextArea textArea, FileData fileData) {
        defaultTextAreaSettings(textArea);
        textArea.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkFileSaved();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkFileSaved();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkFileSaved();
            }

            private void checkFileSaved() {
                FileData fileData = fileData();
                if (fileData.isSaved()) {
                    fileData.setSaved(false, true);
                }
            }
        });

        tabbedPane.addTab(title, editorPanel(textArea));
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);

        if (fileData == null) {
            fileData = new FileData();
            fileData.setTextArea(textArea);
            fileData.setSaved(false, true);
        }
        fileDataList.add(fileData);
    }

    public void generateNewEditorTab() {
        generateNewEditorTab(DEFAULT_TITLE, new RSyntaxTextArea(), null);
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    private void closeCurrentTab() {
        FileData fileData = fileData();
        if (!fileData.isSaved()) {
            int choice = JOptionPane.showConfirmDialog(GlobalCompRef.window, "Save before exiting?");
            if (choice == JOptionPane.OK_OPTION) {
                saveFile(fileData, fileData.getFile());
            } else if (choice == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }
        fileDataList.remove(fileData);
        if (tabbedPane.getTabCount() == 1) {
            System.exit(0);
        }
        tabbedPane.removeTabAt(tabbedPane.getSelectedIndex());
    }

    private void tabNameUnsaved(boolean saved) {
        String title = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
        if (!saved) {
            title = "*" + title;
        } else {
            if (title.substring(1).equals("*")) {
                title = title.substring(1);
            }
        }
        tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), title);
    }

    private void saveTextToFile(File file) {
        FileData fileData = fileData();
        if (fileData.getFile() != null) {
            saveFile(fileData, fileData.getFile());
        } else {
            File newFile = FileChooserUtil.display("Save File", JFileChooser.SAVE_DIALOG);
            saveFile(fileData, newFile);
        }
    }

    private void saveFile(FileData fileData, File file) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileData.getTextArea().write(fileWriter);
            fileData.setFile(file);
            fileData.setSaved(true);
            tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), file.getName());
        } catch (IOException e) {
            log.error("Failed to save file {}: {}", file.getName(), e.getMessage(), e);
        }
    }

    private JPanel editorPanel(RSyntaxTextArea textArea) {
        JPanel editorPanel = new JPanel();
        editorPanel.setLayout(new BorderLayout());
        editorPanel.add(new RTextScrollPane(textArea), BorderLayout.CENTER);
        return editorPanel;
    }

    private FileData fileData() {
        return fileDataList.get(tabbedPane.getSelectedIndex());
    }

    private void openFile(File file) {
        RSyntaxTextArea textArea = new RSyntaxTextArea();
        Optional<String> extension = findExtension(file.getName());
        extension.ifPresent(s -> textArea.setSyntaxEditingStyle(SYNTAX_MAP.get(s)));

        try (FileReader reader = new FileReader(file)) {
            textArea.read(reader, null);
            FileData fileData = new FileData();
            fileData.setSaved(true);
            fileData.setFile(file);
            fileData.setTextArea(textArea);
            generateNewEditorTab(file.getName(), textArea, fileData);
        } catch (IOException e) {
            log.error("Failed to read file: {}", file.getName(), e);
        }
    }

    private Optional<String> findExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        if (lastIndex == -1) {
            return Optional.empty();
        }
        return Optional.of(fileName.substring(lastIndex + 1));
    }

    private void defaultTextAreaSettings(RSyntaxTextArea textArea) {
        textArea.setTabSize(4);
        textArea.setTabsEmulated(true);
    }

    private void updateTheme(Themes theme) {
        if (theme == Themes.DARK) {
            fileDataList.forEach(fileData -> changeStyleViaThemeXml(fileData.getTextArea(), "dark.xml"));
        } else {
            fileDataList.forEach(fileData -> changeStyleViaThemeXml(fileData.getTextArea(), "default.xml"));
        }
    }

    private void changeStyleViaThemeXml(RSyntaxTextArea textArea, String themeFile) {
        try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream("themes/" + themeFile)) {
            Theme theme = Theme.load(inputStream);
            theme.apply(textArea);
        } catch (IOException e) {
            log.error("Failed to apply theme: {}", e.getMessage(), e);
        }
    }
}
