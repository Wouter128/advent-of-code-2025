package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String first;
        String last;

        long sumInvalidsPartOne = 0;
        long sumInvalidsPartTwo;

        String[] idRanges = readAndProcessInput("src/main/resources/day2.txt");
        List<Long> invalidIds = new ArrayList<>();

        for (String idRange : idRanges) {
            first = idRange.split("-")[0];
            last = idRange.split("-")[1];

            for (long i = Long.parseLong(first); i <= Long.parseLong(last); i++) {
                String current = String.valueOf(i);

                sumInvalidsPartOne += checkForInvalidPartOne(current);

                invalidIds.add(checkForInvalidPartTwo(current));
            }
        }

        System.out.println("PART 1: Sum of invalid id's: " + sumInvalidsPartOne);
        sumInvalidsPartTwo = invalidIds.stream().distinct().mapToLong(Long::longValue).sum();
        System.out.println("PART 2: Sum of invalid id's: " + sumInvalidsPartTwo);
    }

    public static String[] readAndProcessInput(String fileName) throws IOException {
        try (BufferedReader bf = new BufferedReader(new FileReader(fileName))) {
            return bf.readLine().split(",");
        }
    }

    public static long checkForInvalidPartOne(String current) {
        if (current.substring(0, current.length() / 2).equals(current.substring(current.length() / 2))) {
            return Long.parseLong(current);
        } else {
            return 0;
        }
    }

    public static long checkForInvalidPartTwo(String current) {
        List<Integer> dividers = new ArrayList<>();
        for (int i = 2; i <= current.length(); i++) {
            if (current.length() % i == 0) {
                dividers.add(i);
            }
        }

        for (int divider : dividers) {
            if (current.substring(0, current.length() / divider).repeat(divider).equals(current)) {
                return Long.parseLong(current);
            }
        }

        return 0L;
    }
}

