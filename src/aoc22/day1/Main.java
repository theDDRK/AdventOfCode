package aoc22.day1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

//    private static final String filename = "src/aoc22/aoc21.day1/sample_data.txt";
    private static final String filename = "src/aoc22/aoc21.day1/data.txt";

    private static ArrayList<Integer> elves = new ArrayList<>();

    public static void main(String[] args) {
        try {
//            System.out.println(new File(filename).getAbsolutePath());
            FileInputStream fis = new FileInputStream(filename);
            Scanner sc = new Scanner(fis);
            int elfCalories = 0;
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                if(nextLine.equals("")){
                    elves.add(elfCalories);
//                    System.out.println("total:" + elves);
                    elfCalories = 0;

                } else {
                    elfCalories += Integer.parseInt(nextLine);
//                    System.out.println(elfCalories);
                }
            }
            elves.add(elfCalories);
            System.out.println("total:" + elves);
            sc.close();
            getElfWithMostCalories();
            getTopThreeElves();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getElfWithMostCalories() {
        int highest = 0;
        for (int elfCalories: elves) {
            if (elfCalories > highest) {
                highest = elfCalories;
            }
        }
        System.out.println(highest);
    }

    private static void getTopThreeElves() {
        int one = 0;
        int two = 0;
        int three = 0;
        for (int elfCalories: elves) {
            if (elfCalories > three) {
                if (elfCalories > two) {
                    if (elfCalories > one) {
                        one = elfCalories;
                    } else {
                        two = elfCalories;
                    }
                } else {
                    three = elfCalories;
                }
            }
        }
        System.out.printf("One: %s, Two: %s, Three: %s%nTotal: %s", one, two, three, one+two+three);
    }

}
