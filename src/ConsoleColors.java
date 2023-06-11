public class ConsoleColors {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    /**
     * Color a given text with ANSI
     * Colors:
     * - red
     * - green
     * - yellow
     * - blue
     * - purple
     * - cyan
     * - white
     * @param color choose the color of the text
     * @param text the text you want to color
     * @return the text colored
     */
    public static String colorText(String color, String text) {
        if (color == null) {
            return text;
        }

        switch (color) {
            case "red":
                color = ANSI_RED;
                break;
            case "green":
                color = ANSI_GREEN;
                break;
            case "yellow":
                color = ANSI_YELLOW;
                break;
            case "blue":
                color = ANSI_BLUE;
                break;
            case "purple":
                color = ANSI_PURPLE;
                break;
            case "cyan":
                color = ANSI_CYAN;
                break;
            case "white":
                color = ANSI_WHITE;
                break;
            default:
                color = ANSI_RESET;
                break;
        }

        return color + text + ANSI_RESET;
    }
}
