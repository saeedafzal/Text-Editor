package com.hknight.text.model.lang;

import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.DefaultCompletionProvider;

public class JavascriptProvider extends DefaultCompletionProvider {

    public JavascriptProvider() {
        keywords();
    }

    private void keywords() {
        addCompletion(new BasicCompletion(this, "break"));
        addCompletion(new BasicCompletion(this, "case"));
        addCompletion(new BasicCompletion(this, "catch"));
        addCompletion(new BasicCompletion(this, "class"));
        addCompletion(new BasicCompletion(this, "const"));
        addCompletion(new BasicCompletion(this, "continue"));
        addCompletion(new BasicCompletion(this, "debugger"));
        addCompletion(new BasicCompletion(this, "default"));
        addCompletion(new BasicCompletion(this, "delete"));
        addCompletion(new BasicCompletion(this, "do"));
        addCompletion(new BasicCompletion(this, "else"));
        addCompletion(new BasicCompletion(this, "export"));
        addCompletion(new BasicCompletion(this, "extends"));
        addCompletion(new BasicCompletion(this, "finally"));
        addCompletion(new BasicCompletion(this, "for"));
        addCompletion(new BasicCompletion(this, "function"));
        addCompletion(new BasicCompletion(this, "if"));
        addCompletion(new BasicCompletion(this, "import"));
        addCompletion(new BasicCompletion(this, "in"));
        addCompletion(new BasicCompletion(this, "instanceof"));
        addCompletion(new BasicCompletion(this, "new"));
        addCompletion(new BasicCompletion(this, "return"));
        addCompletion(new BasicCompletion(this, "super"));
        addCompletion(new BasicCompletion(this, "switch"));
        addCompletion(new BasicCompletion(this, "this"));
        addCompletion(new BasicCompletion(this, "throw"));
        addCompletion(new BasicCompletion(this, "try"));
        addCompletion(new BasicCompletion(this, "typeof"));
        addCompletion(new BasicCompletion(this, "var"));
        addCompletion(new BasicCompletion(this, "void"));
        addCompletion(new BasicCompletion(this, "while"));
        addCompletion(new BasicCompletion(this, "with"));
        addCompletion(new BasicCompletion(this, "yield"));

        addCompletion(new BasicCompletion(this, "enum"));
        addCompletion(new BasicCompletion(this, "implements"));
        addCompletion(new BasicCompletion(this, "interface"));
        addCompletion(new BasicCompletion(this, "let"));
        addCompletion(new BasicCompletion(this, "package"));
        addCompletion(new BasicCompletion(this, "private"));
        addCompletion(new BasicCompletion(this, "protected"));
        addCompletion(new BasicCompletion(this, "public"));
        addCompletion(new BasicCompletion(this, "static"));

        addCompletion(new BasicCompletion(this, "abstract"));
        addCompletion(new BasicCompletion(this, "boolean"));
        addCompletion(new BasicCompletion(this, "byte"));
        addCompletion(new BasicCompletion(this, "char"));
        addCompletion(new BasicCompletion(this, "double"));
        addCompletion(new BasicCompletion(this, "final"));
        addCompletion(new BasicCompletion(this, "float"));
        addCompletion(new BasicCompletion(this, "goto"));
        addCompletion(new BasicCompletion(this, "int"));
        addCompletion(new BasicCompletion(this, "long"));
        addCompletion(new BasicCompletion(this, "native"));
        addCompletion(new BasicCompletion(this, "short"));
        addCompletion(new BasicCompletion(this, "synchronized"));
        addCompletion(new BasicCompletion(this, "throws"));
        addCompletion(new BasicCompletion(this, "transient"));
        addCompletion(new BasicCompletion(this, "volatile"));

        addCompletion(new BasicCompletion(this, "null"));
        addCompletion(new BasicCompletion(this, "undefined"));
        addCompletion(new BasicCompletion(this, "NaN"));
        addCompletion(new BasicCompletion(this, "true"));
        addCompletion(new BasicCompletion(this, "false"));
    }
}
