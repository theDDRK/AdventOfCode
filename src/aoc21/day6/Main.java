package aoc21.day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static ArrayList<Lanternfish> lanternfishes = new ArrayList<>();
    private static HashMap<Long, Long> fish = new HashMap<>() {{
        put((long) 0, (long) 0);
        put((long) 1, (long) 0);
        put((long) 2, (long) 0);
        put((long) 3, (long) 0);
        put((long) 4, (long) 0);
        put((long) 5, (long) 0);
        put((long) 6, (long) 0);
        put((long) 7, (long) 0);
        put((long) 8, (long) 0);
    }};
    private static String[] input;


    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("src/aoc21.day6/input.txt"));
            while (scanner.hasNextLine()) {
                Main.input = scanner.nextLine().split(",");
                System.out.println(Arrays.toString(input));
            }
            scanner.close();
//            growth(256);
            fish(256);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void growth(int days) {
        for (String timer : input) {
            lanternfishes.add(new Lanternfish(Integer.parseInt(timer)));
        }
        System.out.print("Initial State: ");
        for (Lanternfish lanternfish : lanternfishes) {
            System.out.printf("%s,", lanternfish.getInternalTimer());
        }
        for (int i = 1; i <= days; i++) {
            int newFish = 0;
            for (Lanternfish lanternfish : lanternfishes) {
                switch (lanternfish.getInternalTimer()) {
                    case 0 -> {
                        lanternfish.resetInternalTimer();
                        newFish++;
                    }
                    case 1, 2, 3, 4, 5, 6, 7, 8, 9 -> lanternfish.decreaseInternalTimer();
                }
            }
            for (int j = 0; j < newFish; j++) {
                lanternfishes.add(new Lanternfish(8));
            }
            System.out.printf("%nAfter %s days: ", String.format("%02d", i));
            for (Lanternfish lanternfish : lanternfishes) {
                System.out.printf("%s,", lanternfish.getInternalTimer());
            }
        }
        System.out.printf("%nTotal: %s", lanternfishes.size());
    }

    public static void fish(int days) {
        for (String timer : input) {
            fish.put((long) Integer.parseInt(timer), fish.get((long) Integer.parseInt(timer)) + 1);
        }


        for (int i = 1; i <= days; i++) {
            HashMap<Long, Long> newFish = fish;
            long newFishCounter = 0;
            long resetFishCounter = 0;
            for (long internalTime : fish.keySet()) {
                switch ((int) internalTime) {
                    case 0 -> {
                        if (fish.get(internalTime) != 0) {
                            newFishCounter += fish.get(internalTime);
                            resetFishCounter += fish.get(internalTime);
                            newFish.put(internalTime, (long) 0);
                        }
                    }
                    case 1, 2, 3, 4, 5, 6, 7, 8 -> {
                        newFish.put(internalTime - 1, newFish.get(internalTime - 1) + fish.get(internalTime));
                        newFish.put(internalTime, (long) 0);
                    }
                }
            }
            newFish.put((long) 6, newFish.get((long) 6) + resetFishCounter);
            newFish.put((long) 8, fish.get((long) 8) + newFishCounter);
            fish = newFish;
            System.out.printf("%nAfter %s days: ", String.format("%03d", i));
            long total = 0;
            for (long amount : fish.keySet()) {
                total += fish.get(amount);
            }
            System.out.printf("%s", total);
        }
    }
}
