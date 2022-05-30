package com.mss.src.iptt.src;

import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOpener {
    public static final String EMPTY_PATH_STRING = "\\";

    private File file;

    public File getFile() {
        return file;
    }

    public boolean tryOpen(String pathString, String fileName) {
        Path path;

        if (EMPTY_PATH_STRING.equals(pathString)) {
             path = Paths.get("").toAbsolutePath();
        }
        else {
             path =  Paths.get(pathString).toAbsolutePath();
        }

        file = new File(path.toString() + File.separatorChar + fileName);

        return file.exists();
    }

    public FileFormat getExtension() {
        String name = file.getName();

        int index = name.indexOf('.');
        if (index > 0) {
            return Enum.valueOf(FileFormat.class, name.substring(index + 1).toUpperCase());
        }

        return FileFormat.EMPTY;
    }
}
