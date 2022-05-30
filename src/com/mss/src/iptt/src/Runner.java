package com.mss.src.iptt.src;

import com.mss.src.iptt.src.model.ValueObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public final class Runner {


    public static void run() {
        Scanner scanner = new Scanner();
        FileOpener fileOpener = new FileOpener();
        Resolver resolver = new Resolver();

        while (true) {
            if (!scanner.tryScan(
                    "Enter path to the file (type \"" +
                            FileOpener.EMPTY_PATH_STRING +
                            "\" if file in work directory) or type \"exit\" for exit.")) {
                break;
            }
            String path = scanner.getResult();

            if (!scanner.tryScan("Enter file name (with extension) or type \"exit\" for exit.")) {
                break;
            }
            String fileName = scanner.getResult();

            if (!fileOpener.tryOpen(path, fileName)) {
                System.out.println("File not found. Check path and file name.");

                continue;
            }

            resolver.resolve(fileOpener.getExtension());

            if (resolver.getParser() == null || resolver.getProcessor() == null) {
                System.out.printf("File extension '%s' does not supported.\n", fileOpener.getExtension());

                continue;
            }

            if (!resolver.getParser().tryParse(fileOpener.getFile())) {
                System.out.println("Failed to parse file.");

                continue;
            }

            ValueObject result = resolver.getParser().getResult();

            resolver.getProcessor().process(result);
        }
    }
}
