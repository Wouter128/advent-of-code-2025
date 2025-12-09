package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int DIGITS = 12;

    public static void main(String[] args) {
        File myObj = new File("src/main/resources/day3.txt");
        List<String> bank = new ArrayList<>();
        long totalJoltage = 0;

        try {
            Scanner scanner = new Scanner(myObj);
            while (scanner.hasNextLine()) {
                bank.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }

        for (String currentBank : bank) {
//            PART ONE
//            long maxJoltageCurrentBank = getMaxJoltageCurrentBankPartOne(currentBank);

//            PART TWO
            long maxJoltageCurrentBank = getMaxJoltageCurrentBankPartTwo(currentBank);

            totalJoltage += maxJoltageCurrentBank;
        }

        System.out.println("Total Joltage: " + totalJoltage);
    }

    /************
     * PART ONE *
     ************/

//    private static int getMaxJoltageCurrentBankPartOne(String currentBank) {
//        int maxJoltageCurrentBank = 0;
//        for (int i = 0; i < currentBank.length() - 1; i++) {
//            char a = currentBank.charAt(i);
//
//            for (int j = i + 1; j < currentBank.length(); j++) {
//                char b = currentBank.charAt(j);
//
//                String number = String.valueOf(a) + b;
//
//                if (Integer.parseInt(number) > maxJoltageCurrentBank) {
//                    maxJoltageCurrentBank = Integer.parseInt(number);
//                }
//            }
//        }
//        return maxJoltageCurrentBank;
//    }

    /************
     * PART TWO *
     ************/

    public static long getMaxJoltageCurrentBankPartTwo(String currentBank) {
        String maxJoltageCurrentBank = "";
        HashMap<String, Long> map = new HashMap<>();
        map.put("Position", 0L);
        map.put("Largest", 0L);

        for (int i = 0; i < DIGITS; i++) {
            map = findLargestNumberInSequence(currentBank, DIGITS - i - 1, map.get("Position"));
            maxJoltageCurrentBank = maxJoltageCurrentBank.concat(String.valueOf(map.get("Largest")));
        }

        return Long.parseLong(maxJoltageCurrentBank);
    }

    public static HashMap<String, Long> findLargestNumberInSequence(String currentBank, int excludedCharacters, long position) {
        HashMap<String, Long> map = new HashMap<>();
        String availableSequence = currentBank.substring((int) position, currentBank.length() - excludedCharacters);
        long largestNumber = 0;

        for (int i = 0; i < availableSequence.length(); i++) {
            int x = Character.getNumericValue(availableSequence.charAt(i));
            if (x > largestNumber) {
                largestNumber = x;
                // Set position (+1) of highest number to start the next sequence right after that point.
                // Because the loop starts counting from 0 again, we also need to add the previous position.
                map.put("Position", (long) (i + 1) + position);
            }
        }

        map.put("Largest", largestNumber);

        return map;
    }
}
