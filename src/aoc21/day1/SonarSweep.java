package aoc21.day1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SonarSweep {

    private static void single() {
        String previous;
        String current;
        int increasedCounter = 0;
        boolean increased;

        try {
            File myObj = new File("src/aoc21.day1/input.txt");
            Scanner myReader = new Scanner(myObj);

            current = myReader.nextLine();
            previous = current;
            System.out.printf("%s (N/A - no previous measurement)%n", current);

            while (myReader.hasNextLine()) {
                current = myReader.nextLine();
                if (Integer.parseInt(current) > Integer.parseInt(previous)) {
                    increased = true;
                    increasedCounter++;
                } else {
                    increased = false;
                }
                System.out.printf("%s (%s)%n", current, increased ? "increased" : "decreased");
                previous = current;
            }
            System.out.printf("%s measurements that are larger than the previous measurement.", increasedCounter);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void slidingWindow() {
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();
        ArrayList<Integer> arr3 = new ArrayList<>();
        ArrayList<Integer> arr4 = new ArrayList<>();

        int current;
        int previous = 0;
        int pointer = 3;
        int increasedCounter = 0;
        boolean increased;

        try {
            File myObj = new File("src/aoc21.day1/input.txt");
            Scanner myReader = new Scanner(myObj);

            current = Integer.parseInt(myReader.nextLine());
            arr1.add(current);

            current = Integer.parseInt(myReader.nextLine());
            arr1.add(current);
            arr2.add(current);

            while (myReader.hasNextLine()) {
                int total = 0;
                current = Integer.parseInt(myReader.nextLine());
                switch (pointer) {
                    case 1 -> {
                        arr1.add(current);
                        arr3.add(current);
                        arr4.add(current);
                        pointer++;
                    }
                    case 2 -> {
                        arr1.add(current);
                        arr2.add(current);
                        arr4.add(current);
                        pointer++;
                    }
                    case 3 -> {
                        arr1.add(current);
                        arr2.add(current);
                        arr3.add(current);
                        pointer++;
                    }
                    case 4 -> {
                        arr2.add(current);
                        arr3.add(current);
                        arr4.add(current);
                        pointer = 1;
                    }
                }
                if (arr1.size() == 3) {
                    for (int i : arr1) {
                        total += i;
                    }
                    if (previous > 0) {
                        if (total > previous) {
                            increased = true;
                            increasedCounter++;
                        } else {
                            increased = false;
                        }
                        System.out.printf("%s (%s)%n", total, increased ? "increased" : "-");
                    } else {
                        System.out.printf("%s (N/A - no previous sum)%n", total);
                    }
                    arr1.clear();
                }
                if (arr2.size() == 3) {
                    for (int i : arr2) {
                        total += i;
                    }
                    if (total > previous) {
                        increased = true;
                        increasedCounter++;
                    } else {
                        increased = false;
                    }
                    System.out.printf("%s (%s)%n", total, increased ? "increased" : "-");
                    arr2.clear();
                }
                if (arr3.size() == 3) {
                    for (int i : arr3) {
                        total += i;
                    }
                    if (total > previous) {
                        increased = true;
                        increasedCounter++;
                    } else {
                        increased = false;
                    }
                    System.out.printf("%s (%s)%n", total, increased ? "increased" : "-");
                    arr3.clear();
                }
                if (arr4.size() == 3) {
                    for (int i : arr4) {
                        total += i;
                    }
                    if (total > previous) {
                        increased = true;
                        increasedCounter++;
                    } else {
                        increased = false;
                    }
                    System.out.printf("%s (%s)%n", total, increased ? "increased" : "-");
                    arr4.clear();
                }
                previous = total;
            }
            System.out.printf("There are %s sums that are larger than the previous sum.", increasedCounter);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        slidingWindow();
    }


}
