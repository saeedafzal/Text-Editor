package com.saeed.editor.model;

import java.io.File;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import com.saeed.editor.event.Event;
import com.saeed.editor.event.EventBus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileData {

    private File file;
    private boolean saved;
    private RSyntaxTextArea textArea;

    public void setSaved(boolean saved, boolean triggerEvent) {
        setSaved(saved);
        if (triggerEvent) {
            EventBus.getDefault().publish(Event.UNSAVED_TITLE, saved);
        }
    }
}
