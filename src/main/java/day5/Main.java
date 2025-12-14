package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> input = readFile("src/main/resources/day5.txt");
        List<String> idList;
        List<String> ranges;

        int freshIngredientAmount = 0;

        ranges = input.subList(0, input.indexOf(""));
        idList = input.subList(input.indexOf("") + 1, input.size());

        // Part one: check whether an id from the list is present in any of the ranges.
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

        System.out.println("Part one: " + freshIngredientAmount);
        System.out.println("#####");

        // Part two: find out how many id's are fresh (all ranges).
        List<Range> sortedRanges = ranges.stream()
                .map(r -> {
                    String[] parts = r.split("-");
                    return new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1]));
                })
                .sorted(Comparator.comparingLong(Range::start))
                .toList();

        List<Range> mergedRanges = new ArrayList<>();
        Range current = sortedRanges.get(0);

        for (int i = 1; i < sortedRanges.size(); i++) {
            Range next = sortedRanges.get(i);

            if (current.end() >= next.start()) {
                current = new Range(current.start(), next.end());
            } else {
                mergedRanges.add(current);
                current = next;
            }
        }

        mergedRanges.add(current);

        long totalAmountFreshIngredients = 0;

        for (Range range : mergedRanges) {
            long sum = range.end() - range.start() + 1;
            totalAmountFreshIngredients += sum;
        }

        System.out.println("Part two: " +  totalAmountFreshIngredients);
    }

    public static List<String> readFile(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return br.lines().toList();
        }
    }
}
