package day4;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int[][] DIRS8 = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
    };

    public static void main(String[] args) throws IOException {
        char[][] grid = readGrid("src/main/resources/day4.txt");
        int accessibleRolls;
        int totalAmountOfRemovableRolls = 0;

        // Check whether rolls can be removed. Remove rolls and repeat process until no more rolls can be removed.
        do {
            accessibleRolls = 0;
            for (int r = 0; r < grid.length; r++) {
                for (int c = 0; c < grid[0].length; c++) {
                    // neighbors is a list of coordinates. Use the coordinates in the grid to find out which symbol they point to.
                    List<Point> neighbors = neighbors(new Point(r, c), grid);

                    // If current position is a roll, find out how many 'roll' neighbors it has.
                    if (grid[r][c] == '@') {
                        int neighboringAmountOfRolls = 0;
                        for (Point pnt : neighbors) {
                            if (grid[pnt.x][pnt.y] == '@') {
                                neighboringAmountOfRolls++;
                            }
                        }

                        // If less than 4 'roll' neighbors, increase totals and remove roll from the grid.
                        if (neighboringAmountOfRolls < 4) {
                            accessibleRolls++;
                            totalAmountOfRemovableRolls++;
                            grid[r][c] = 'X';
                        }
                    }
                }
            }
        } while (accessibleRolls != 0);

        System.out.println(totalAmountOfRemovableRolls);
    }

    public static char[][] readGrid(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            List<String> lines = br.lines().toList();

            int rows = lines.size();
            int cols = lines.get(0).length();

            char[][] grid = new char[rows][cols];

            for (int r = 0; r < rows; r++) {
                grid[r] = lines.get(r).toCharArray();
            }

            return grid;
        }
    }

    public static List<Point> neighbors(Point p, char[][] grid) {
        List<Point> result = new ArrayList<>();

        for (int[] dir : DIRS8) {
            Point n = new Point((int) (p.getX() + dir[0]), (int) (p.getY() + dir[1]));
            if (inBounds(n, grid)) {
                result.add(n);
            }
        }

        return result;
    }

    public static boolean inBounds(Point p, char[][] grid) {
        return p.getX() >= 0 && p.getX() < grid.length &&
                p.getY() >= 0 && p.getY() < grid[0].length;
    }
}
