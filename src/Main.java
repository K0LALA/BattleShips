import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Battleship game!\n");

        System.out.println("Your first task is to choose where you put your five boats on the grid");
        System.out.println("You have one boat of each type:");
        System.out.println(ConsoleColors.colorText("yellow", " #####         @@@@          %%%          $$$           §§"));
        System.out.println(ConsoleColors.colorText("yellow", "Carrier     Battleship     Cruiser     Submarine     Destroyer\n"));
        System.out.println("You will have to place them in this order, and with two positions, starting and ending point separated by a space (e.g. A1 E1)\nYou can't place diagonally!\n");

        Grid grid = new Grid(10, 10);
        grid.print();

        Vector<Boat> boats = new Vector<>();

        // Ask for the three boats, may not do it in for loop
        for (Names boats_ : Names.values()) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter position for the " + ConsoleColors.colorText("yellow", boats_.toString()) + " (e.g. A1 E1): ");
            String position = "";
            String firstPosition = "";
            String secondPosition = "";
            try {
                position = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Get the two positions
            firstPosition = position.substring(0, position.indexOf(' '));
            secondPosition = position.substring(position.indexOf(' ') + 1);

            // Encode them
            float encodedFirstPosition = firstPosition.charAt(0) - 64 + (float) Integer.parseInt(firstPosition.substring(1)) / 100;
            float encodedSecondPosition = secondPosition.charAt(0) - 64 + (float) Integer.parseInt(secondPosition.substring(1)) / 100;

            // Add the instance to the Vector
            boats.add(new Boat(boats_.toString(), encodedFirstPosition, encodedSecondPosition));

            System.out.println();
            grid.calculateBoats(boats);
            grid.print();
        }


    }
}