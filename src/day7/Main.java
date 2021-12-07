package day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    private static ArrayList<Integer> input = new ArrayList<>();

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("src/day7/input.txt"));
            while (scanner.hasNextLine()) {
                String[] input = scanner.nextLine().split(",");
                for (String i : input) {
                    Main.input.add(Integer.parseInt(i));
                }
                Collections.sort(Main.input);
                System.out.println(Main.input);
            }
            scanner.close();
            part2();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void part1() {
        int min = input.get(0);
        int max = input.get(input.size()-1);
        int cheapest = -1;

        for (int i = min; i <= max; i++) {
            int total = 0;
            for (int value : input) {
                total += Math.abs(i - value);
            }
            if (cheapest == -1) {
                cheapest = total;
            } else if (total < cheapest) {
                cheapest = total;
            }
            System.out.printf("Position %s: %s fuel%n", i, total);
        }
        System.out.printf("Cheapest position: %s fuel", cheapest);
    }

    public static void part2() {
        int min = input.get(0);
        int max = input.get(input.size()-1);
        int cheapest = -1;

        for (int i = min; i <= max; i++) {
            int total = 0;
            for (int value : input) {
                int difference = Math.abs(i - value);
                for (int j = 1; j <= difference; j++) {
                    total += j;
                }
            }
            if (cheapest == -1) {
                cheapest = total;
            } else if (total < cheapest) {
                cheapest = total;
            }
            System.out.printf("Position %s: %s fuel%n", i, total);
        }
        System.out.printf("Cheapest position: %s fuel", cheapest);
    }
}
