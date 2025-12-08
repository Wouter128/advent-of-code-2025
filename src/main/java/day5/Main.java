package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> input = readFile("src/main/resources/day5.txt");
        List<String> idList;
        List<String> ranges;

        int freshIngredientAmount = 0;

        ranges = input.subList(0, input.indexOf(""));
        idList = input.subList(input.indexOf("") + 1, input.size());

        for (String id : idList) {
            long numericId = Long.parseLong(id);

            for (String range : ranges) {
                long first = Long.parseLong(range.split("-")[0]);
                long last = Long.parseLong(range.split("-")[1]);

                if (numericId >= first && numericId <= last) {
                    freshIngredientAmount++;
                    break;
                }
            }
        }

        System.out.println(freshIngredientAmount);
    }

    public static List<String> readFile(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.lines().toList();
        }
    }
}
