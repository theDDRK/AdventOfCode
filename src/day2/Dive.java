package day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dive {

    public static ArrayList<String> course = new ArrayList<>();

    public static void main(String[] args) {
        int depth = 0;
        int position = 0;
        int aim = 0;

        try {
            Scanner scanner = new Scanner(new File("src/day2/input.txt"));
            while (scanner.hasNextLine()) {
                course.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        diveOne(position, depth);
        diveTwo(position, depth, aim);
    }

    public static void diveOne(int position, int depth) {
        for (String instruction : course) {
            String[] arr = instruction.split(" ");
            switch (arr[0]) {
                case "forward" -> position += Integer.parseInt(arr[1]);
                case "up" -> depth -= Integer.parseInt(arr[1]);
                case "down" -> depth += Integer.parseInt(arr[1]);
            }
        }
        System.out.printf("Part1:%nPosition: %s, Depth: %s%nMultiplying gives: %s%n%n", position, depth,
            position * depth);
    }

    public static void diveTwo(int position, int depth, int aim) {
        for (String instruction : course) {
            String[] arr = instruction.split(" ");
            switch (arr[0]) {
                case "forward" -> {
                    position += Integer.parseInt(arr[1]);
                    depth += aim * Integer.parseInt(arr[1]);
                }
                case "up" -> aim -= Integer.parseInt(arr[1]);
                case "down" -> aim += Integer.parseInt(arr[1]);
            }
        }
        System.out.printf("Part2:%nPosition: %s, Depth: %s, Aim: %s%nMultiplying gives: %s%n", position, depth, aim,
            position * depth);
    }
}
