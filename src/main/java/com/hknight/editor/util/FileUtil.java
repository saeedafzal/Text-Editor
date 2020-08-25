package com.hknight.editor.util;

import java.util.Optional;

public class FileUtil {

    public static Optional<String> getFileExtension(String filename) {
        return Optional.ofNullable(filename)
            .filter(f -> f.contains("."))
            .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
}
