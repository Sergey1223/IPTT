package com.mss.src.iptt.src.parsing;

import com.mss.src.iptt.src.model.ValueObject;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

public interface Parser {

    ValueObject getResult();

    boolean tryParse(File file);
}