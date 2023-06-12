import java.util.Vector;

public class Grid {

    private int width;
    private int height;

    private final String[][] ocean;
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

        this.ocean = new String[width][height];

        waterFill();
    }

    /**
     * Fills the ocean array with waves
     */
    private void waterFill() {
        for (int i = 0; i < this.width; i++)
            for (int j = 0; j < this.height; j++)
                this.ocean[i][j] = "~";
    }

    /**
     * Prints the grid in the console
     */
    public void print() {
        System.out.println("   " + " A  B  C  D  E  F  G  H  I  J  K  L  M  N  O  P  Q  R  S  T  U  V  W  X  Y  Z".substring(0, this.width * 3));
        for (int i = 0; i < this.height; i++) {
            System.out.print(((i + 1) / 10 > 0 ? "" : " ") + (i + 1) + " ");
            for (int j = 0; j < this.width; j++) {
                System.out.print(" " + (ocean[j][i].equals("~") ? ConsoleColors.ANSI_RESET + ConsoleColors.ANSI_BLUE : ConsoleColors.ANSI_RESET + ConsoleColors.ANSI_YELLOW) + ocean[j][i] + " ");
            }
            System.out.println(ConsoleColors.ANSI_RESET);
        }
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

            // For each coordinate
            for (Float boatPosition : boatPositions) {
                // Modify the corresponding case
                this.ocean[(int) Math.floor(boatPosition) - 1][Math.round(boatPosition % 1 * 100) - 1] = String.valueOf(boatsCharacters[i]);
            }
        }
    }
}
