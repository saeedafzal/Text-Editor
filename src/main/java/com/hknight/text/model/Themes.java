package com.hknight.text.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.LookAndFeel;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;

public class Themes {

    private List<String> themes = new ArrayList<>();
    private Map<String, LookAndFeel> appearance = new HashMap<>();

    public Themes() {
        populate();
    }

    private void populate() {
        themes.add("dark.xml");
        themes.add("default.xml");
        themes.add("default-alt.xml");
        themes.add("eclipse.xml");
        themes.add("idea.xml");
        themes.add("monokai.xml");
        themes.add("vs.xml");

        appearance.put("Light", new FlatIntelliJLaf());
        appearance.put("Darcula", new FlatDarculaLaf());
    }

    public List<String> getThemes() {
        return themes;
    }

    public Map<String, LookAndFeel> getAppearance() {
        return appearance;
    }
}
