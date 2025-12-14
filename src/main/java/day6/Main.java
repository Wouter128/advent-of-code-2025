package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String[][] input = readFile("src/main/resources/day6.txt");
        long result = 0;

        for (int i = 0; i < input[0].length; i++) {
            if (input[4][i].equals("+")) {
                result += Long.parseLong(input[0][i])
                        + Long.parseLong(input[1][i])
                        + Long.parseLong(input[2][i])
                        + Long.parseLong(input[3][i]);
            }

            if (input[4][i].equals("*")) {
                result += Long.parseLong(input[0][i])
                        * Long.parseLong(input[1][i])
                        * Long.parseLong(input[2][i])
                        * Long.parseLong(input[3][i]);
            }
        }

        System.out.println("Day 6 part one: " + result);
    }

    public static String[][] readFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            List<String> input = br.lines().toList();
            String[][] result = new String[input.size()][input.get(0).length()];
            int lineNumber = 0;

            for (String line : input) {
                result[lineNumber] = line.split("\\s+");
                lineNumber++;
            }

            return result;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
