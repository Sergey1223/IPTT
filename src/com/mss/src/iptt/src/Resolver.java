package com.mss.src.iptt.src;

import com.mss.src.iptt.src.parsing.JsonParser;
import com.mss.src.iptt.src.parsing.Parser;
import com.mss.src.iptt.src.processing.JsonProcessor;
import com.mss.src.iptt.src.processing.Processor;

public class Resolver {
    private Parser parser;
    private Processor processor;

    public Parser getParser() {
        return parser;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void resolve(FileFormat format) {
        switch (format) {
            case EMPTY:
                parser = null;
                processor = null;

                break;
            case JSON:
                parser = new JsonParser();
                processor = new JsonProcessor();

                break;
        }
    }
}
