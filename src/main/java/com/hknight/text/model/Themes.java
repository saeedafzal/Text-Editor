package com.hknight.text.model;

import java.util.ArrayList;
import java.util.List;

public class Themes {

    private List<String> themes = new ArrayList<>();

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
    }

    public List<String> getThemes() {
        return themes;
    }
}
