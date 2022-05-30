package com.mss.src.iptt.src;

public class Scanner {
    private static final String EXIT_COMMAND = "exit";

    private String result;

    public String getResult() {
        return result;
    }

    public boolean tryScan(String placeholder) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.println(placeholder);

        result = scanner.next();

        return !EXIT_COMMAND.equalsIgnoreCase(result);
    }
}
