package com.hknight.text.model;

import java.util.HashMap;
import java.util.Map;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

public class SyntaxParser {

    private Map<String, String> syntax = new HashMap<>();

    public SyntaxParser() {
        populate();
    }

    private void populate() {
        syntax.put("java", SyntaxConstants.SYNTAX_STYLE_JAVA);
        syntax.put("js", SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
        syntax.put("css", SyntaxConstants.SYNTAX_STYLE_CSS);
        syntax.put("dart", SyntaxConstants.SYNTAX_STYLE_DART);
        syntax.put("html", SyntaxConstants.SYNTAX_STYLE_HTML);
        syntax.put("json", SyntaxConstants.SYNTAX_STYLE_JSON);
        syntax.put("less", SyntaxConstants.SYNTAX_STYLE_LESS);
        syntax.put("lua", SyntaxConstants.SYNTAX_STYLE_LUA);
        syntax.put("py", SyntaxConstants.SYNTAX_STYLE_PYTHON);
        syntax.put("rb", SyntaxConstants.SYNTAX_STYLE_RUBY);
        syntax.put("sql", SyntaxConstants.SYNTAX_STYLE_SQL);
        syntax.put("ts", SyntaxConstants.SYNTAX_STYLE_TYPESCRIPT);
        syntax.put("xml", SyntaxConstants.SYNTAX_STYLE_XML);
    }

    public String getSyntax(String extension) {
        return syntax.get(extension);
    }
}
