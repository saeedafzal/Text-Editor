package com.hknight.editor.util;

import java.util.HashMap;
import java.util.Map;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

public class SyntaxMap {

    private static final Map<String, String> SYNTAX_MAP = new HashMap<>();

    static {
        SYNTAX_MAP.put("plain", SyntaxConstants.SYNTAX_STYLE_NONE);
        SYNTAX_MAP.put("java", SyntaxConstants.SYNTAX_STYLE_JAVA);
        SYNTAX_MAP.put("xml", SyntaxConstants.SYNTAX_STYLE_XML);
    }

    public static String getSyntaxFromExt(String ext) {
        return SYNTAX_MAP.get(ext);
    }
}
