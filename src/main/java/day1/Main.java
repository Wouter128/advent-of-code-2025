package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File myObj = new File("src/main/resources/day1.txt");
        ArrayList<String> myArray = new ArrayList<>();

        Dial dial = new Dial();

        String direction;
        int moveAmount;

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                myArray.add(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }

        for(String value : myArray) {
            direction = value.substring(0, 1);
            moveAmount = Integer.parseInt(value.substring(1));

            if (direction.equals("R")) {
                dial.moveRight(moveAmount);
            }

            if (direction.equals("L")) {
                dial.moveLeft(moveAmount);
            }
        }

        System.out.println(dial.getAmountOfZeros());
    }
}

