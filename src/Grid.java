import java.util.Vector;
import java.util.Collections;

public class Grid {

    private int width;
    private int height;

    private final Vector<String> lines = new Vector<>();
    private static final char[] boatsCharacters = { '#', '@', '%', '$', '§' };

    /**
     * Constructor for the Grid class
     * @param width of the grid
     * @param height of the grid
     */
    public Grid(int width, int height) {
        this.width = width;
        this.height = height;


        if (this.width <= 0 || this.height <= 0) {
            System.out.println(ConsoleColors.colorText("red", "Width and height must be positive, 10 be default"));
            this.width = 10;
            this.height = 10;
        }

        for (int i = 0; i < this.height; i++) {
            lines.add(waterFill());
        }
    }

    /**
     * Fills a whole line of the grid with blue waves characters
     * @return a line of the grid with only water
     */
    private String waterFill() {
        return String.valueOf(ConsoleColors.colorText("blue", " ~ ")).repeat(Math.max(0, this.width));
    }

    /**
     * Prints the grid in the console
     */
    public void print() {
        System.out.println("    " + " A  B  C  D  E  F  G  H  I  J  K  L  M  N  O  P  Q  R  S  T  U  V  W  X  Y  Z".substring(0, this.width * 3));
        for (int i = 0; i < lines.size(); i++)
            System.out.println(((i + 1) / 10 > 0 ? " " : "  ") + String.valueOf(i + 1) + " " + lines.get(i));
        System.out.println();
    }

    /**
     * Calculate where are the boats in the grid and write it to lines of the grid
     * @param boats Boat <code>Vector</code> containing the 5 boats
     */
    public void calculateBoats(Vector<Boat> boats) {
        // For each boat
        for (int i = 0; i < boats.size(); i++) {
            // Get coordinates of the boat
            Vector<Float> boatPositions = boats.get(i).getPositions();

            // If the boat is horizontal
            if(boats.get(i).isHorizontal()) {
                // Get the line where the boat is (Y)
                int lineIndex = (int) Math.round(boatPositions.get(0) % 1 * 100);
                // Remove the corresponding line


                // Get the columns where the boat is (X)
                Vector<Integer> columns = new Vector<>();
                for (Float boatPosition : boatPositions)
                    columns.add((int) Math.floor(boatPosition));

                // Build the line with the boat
                StringBuilder line = new StringBuilder(lines.get(lineIndex - 1));
                lines.remove(lineIndex - 1);
                // For all the columns of the grid
                for (int j = 0; j < this.width; j++) {
                    // If the boat is on that column
                    if(columns.contains(j + 1));
                        // Add the boat character to the String
                        //line.append(ConsoleColors.colorText("yellow", " " + boatsCharacters[i] + " "));
                        //
                        // Replace the j * 3 character by the boat character (colored)
                        // Blue color may fuck the thing up
                }

                // Add the String to the lines Vector at the lineIndex - 1
                lines.add(lineIndex - 1, line.toString());
            }
            else {
                // Get the lines where the boat is located
                Vector<Integer> linesIndex = new Vector<>();
                for (Float boatPosition : boatPositions)
                    linesIndex.add((int) Math.round(boatPosition % 1 * 100));
                Collections.sort(linesIndex);

                int column = (int) Math.floor(boatPositions.get(0));

                for(int lineIndex : linesIndex) {
                    StringBuilder line = new StringBuilder(lines.get(lineIndex - 1));
                    lines.remove(lineIndex - 1);
                    for (int k = 0; k < this.width; k++) {
                        if(k == column - 1);
                            //line.append(ConsoleColors.colorText("yellow", " " + boatsCharacters[i] + " "));
                            //
                            // Replace the k * 3 character by the boat character (colored)
                            // Blue color may fuck the thing up
                    }
                    lines.add(lineIndex - 1, line.toString());
                    System.out.println(linesIndex);
                }
            }
        }
    }
}
