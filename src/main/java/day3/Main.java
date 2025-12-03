package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File myObj = new File("src/main/resources/day3.txt");
        List<String> bank = new ArrayList<>();
        int totalJoltage = 0;

        try {
            Scanner scanner = new Scanner(myObj);
            while (scanner.hasNextLine()) {
                bank.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }

        for (String currentBank : bank) {
            int maxJoltageCurrentBank = 0;
            for (int i = 0; i < currentBank.length() - 1; i++) {
                char a = currentBank.charAt(i);

                for (int j = i + 1; j < currentBank.length(); j++) {
                    char b = currentBank.charAt(j);

                    String number = String.valueOf(a) + b;

                    if (Integer.parseInt(number) > maxJoltageCurrentBank) {
                        maxJoltageCurrentBank = Integer.parseInt(number);
                    }
                }
            }

            totalJoltage += maxJoltageCurrentBank;
        }

        System.out.println("Total Joltage: " + totalJoltage);
    }
}
