import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        final int[] sizes = { 5, 4, 3, 3, 2 };

        System.out.println("Welcome to the Battleship game!\n");

        System.out.println("Your first task is to choose where you put your five boats on the grid");
        System.out.println("You have one boat of each type:");
        System.out.println(ConsoleColors.colorText("yellow", " #####         @@@@          %%%          $$$           §§"));
        System.out.println(ConsoleColors.colorText("yellow", "Carrier     Battleship     Cruiser     Submarine     Destroyer\n"));
        System.out.println("You will have to place them in this order, and with two positions, starting and ending point separated by a space (e.g. A1 E1)\nYou can't place diagonally!\n");

        Grid grid = new Grid(10, 10);
        grid.print();

        Vector<Boat> boats = new Vector<>();
        Vector<Float> boatPositions = new Vector<>();

        // Ask for the three boats, may not do it in for loop
        for (int i = 0; i < Names.values().length; i++) {
            boolean duplicate;
            Boat boat;
            Vector<Float> positions;
            do {
                duplicate = false;
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Enter position for the " + ConsoleColors.colorText("yellow", Names.values()[i].toString()) + " (e.g. A1 E1): ");
                String positionString = "";
                String firstPosition;
                String secondPosition;
                try {
                    positionString = bufferedReader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Get the two positions
                firstPosition = positionString.substring(0, positionString.indexOf(' '));
                secondPosition = positionString.substring(positionString.indexOf(' ') + 1);

                // Encode them
                float encodedFirstPosition = firstPosition.charAt(0) - 64 + (float) Integer.parseInt(firstPosition.substring(1)) / 100;
                float encodedSecondPosition = secondPosition.charAt(0) - 64 + (float) Integer.parseInt(secondPosition.substring(1)) / 100;

                // Add the instance to the Vector
                boat = new Boat(Names.values()[i].toString(), encodedFirstPosition, encodedSecondPosition);
                positions = boat.getPositions();

                for (Float position : positions) {
                    if (boatPositions.contains(position)) {
                        duplicate = true;
                        break;
                    }
                }
            } while (duplicate || !(positions.size() == sizes[i]));

            boats.add(boat);
            boatPositions.addAll(positions);

            System.out.println();
            grid.calculateBoats(boats);
            grid.print();
        }


    }
}