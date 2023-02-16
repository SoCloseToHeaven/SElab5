package util;

public enum TerminalColors {
    GREEN("\u001b[32m"),
    RED("\u001b[31m"),
    BLUE("\u001b[34m"),
    CYAN("\u001B[36m"),
    RESET("\u001b[0m");

    private final String ansiColor;

    TerminalColors(String ansiColor) {
        this.ansiColor = ansiColor;
    }

    public static String setColor(String line, TerminalColors color) {
        return color.toString() + line + TerminalColors.RESET;
    }

    @Override
    public String toString() {
        return this.ansiColor;
    }
}
