package com.hknight.text.lang;

import java.util.HashMap;
import java.util.Map;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

public class SyntaxMap {

    private Map<String, String> syntaxMap = new HashMap<>();

    public SyntaxMap() {
        populate();
    }

    private void populate() {
        syntaxMap.put("js", SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
        syntaxMap.put("java", SyntaxConstants.SYNTAX_STYLE_JAVA);
        syntaxMap.put("ts", SyntaxConstants.SYNTAX_STYLE_TYPESCRIPT);
        syntaxMap.put("xml", SyntaxConstants.SYNTAX_STYLE_XML);
    }

    public Map<String, String> getSyntaxMap() {
        return syntaxMap;
    }
}
