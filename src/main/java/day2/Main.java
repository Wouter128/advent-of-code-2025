package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File myObj = new File("src/main/resources/day2.txt");
        String data = "";
        String[] idRanges;

        String first;
        String last;

        long sumInvalids = 0;

        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }

        idRanges = data.split(",");

        for (String idRange : idRanges) {
            first = idRange.split("-")[0];
            last = idRange.split("-")[1];

            for (long i = Long.parseLong(first); i <= Long.parseLong(last); i++) {
                String current = String.valueOf(i);

                if (current.substring(0, current.length() / 2).equals(current.substring(current.length() / 2))) {
                    System.out.println("Invalid id: " + current);
                    sumInvalids += Long.parseLong(current);
                }
            }
        }

        System.out.println("Sum of invalid id's: " + sumInvalids);
    }
}

