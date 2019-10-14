package com.hknight.text.gui;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import com.google.common.io.Files;

import com.hknight.text.lang.SyntaxMap;

public class TextEditor extends RSyntaxTextArea {

    private final SyntaxMap syntaxMap = new SyntaxMap();

    @Override
    public void read(Reader in, Object desc) throws IOException {
        super.read(in, desc);

        String extension = Files.getFileExtension(desc.toString());
        super.setSyntaxEditingStyle(syntaxMap.getSyntaxMap().get(extension));
    }

    public void saveAsSetSyntax(Writer out, String desc) throws IOException {
        super.write(out);

        String extension = Files.getFileExtension(desc);
        super.setSyntaxEditingStyle(syntaxMap.getSyntaxMap().get(extension));
    }
}
