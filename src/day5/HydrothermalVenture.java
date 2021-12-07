package day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class HydrothermalVenture {
    private static int[][] coords = new int[999][999];
    private static ArrayList<String> lines = new ArrayList<>();

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("src/day5/input.txt"));
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            scanner.close();
            setCoords();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void setCoords() {
        for (String line : lines) {
            String[] split = line.split("\\s+->\\s+");
            int x1 = Integer.parseInt(split[0].split(",")[0]);
            int y1 = Integer.parseInt(split[0].split(",")[1]);
            int x2 = Integer.parseInt(split[1].split(",")[0]);
            int y2 = Integer.parseInt(split[1].split(",")[1]);

            if (x1 == x2) {
                // horizontal
                int counter = Math.min(y1, y2);
                while (counter <= Math.max(y1, y2)) {
                    int y = counter;
                    coords[x1][y] += 1;
                    counter++;
                }
            } else if (y1 == y2) {
                // vertical
                int counter = Math.min(x1, x2);
                while (counter <= Math.max(x1, x2)) {
                    int x = counter;
                    coords[x][y1] += 1;
                    counter++;
                }
            } else {
                // diagonal
                int counterX = Math.min(x1, x2);
                int counterY = x1 < x2 ? y1 : y2;
                boolean increasingY = (x1 < x2 && y1 < y2) || (x1 > x2 && y1 > y2);

                while (counterX <= Math.max(x1, x2)) {
                    int x = counterX;
                    int y = counterY;
                    coords[x][y] += 1;
                    counterX++;
                    if (increasingY) {
                        counterY++;
                    } else {
                        counterY--;
                    }
                }
            }
        }
        int total = 0;
        for (int x = 0; x < coords.length; x++) {
            for (int y = 0; y < coords.length; y++) {
                if (coords[x][y] >= 2) {
                    total++;
                }
            }
        }
        System.out.println(total);
    }
}
