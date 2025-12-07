package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> input = readFile("src/main/resources/day5.txt");
    }

    public static List<String> readFile(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            List<String> lines = br.lines().toList();

            return lines;
        }
    }
}
