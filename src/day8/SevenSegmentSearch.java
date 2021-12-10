package day8;

import day7.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class SevenSegmentSearch {

    public static ArrayList<String> input = new ArrayList<>();

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("src/day8/exampleinput.txt"));
            while (scanner.hasNextLine()) {
                String[] input = scanner.nextLine().split(",");
                SevenSegmentSearch.input.addAll(Arrays.asList(input));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        part1();
    }

    private static void part1() {
        for (String line : input) {
            String[] split = line.split(" /| ");
            ArrayList<String> signalPattern = new ArrayList<>();
            ArrayList<String> output = new ArrayList<>();

            boolean firstPart = true;
            for (String pattern : split) {
                if (Objects.equals(pattern, "|")) {
                    firstPart = false;
                    continue;
                }
                if (firstPart) {
                    signalPattern.add(pattern);
                } else {
                    output.add(pattern);
                }
            }
//            System.out.println(signalPattern);
//            System.out.println(output);

            HashMap<Integer, String> map = new HashMap<>();
            for (String pattern : signalPattern) {
                // 0=6, 1=2, 2=5, 3=5, 4=4, 5=5, 6=6, 7=3, 8=7, 9=6
                // 1=2, 4=4, 7=3, 8=7 are unique
                char[] chars = pattern.toCharArray();
                Arrays.sort(chars);
                switch (pattern.length()) {
                    case 2 ->
                        // number 1
                        map.put(1, new String(chars));
                    case 4 ->
                        // number 4
                        map.put(4, new String(chars));
                    case 3 ->
                        // number 7
                        map.put(7, new String(chars));
                    case 7 ->
                        // number 8
                        map.put(8, new String(chars));
                }
            }
            HashMap<String, String> mapping = new HashMap<>();

            mapping.put("cf", map.get(1));

            String one = map.get(1);
            String two = map.get(7);
//            System.out.printf("%s %s", one, two);
            for (int i = 0; i < one.length(); i++) {
                for (int j = 0; j < two.length(); j++) {
                    if (one.charAt(i) == two.charAt(j)) {
                        two = two.replace(two.charAt(j) + "", "");
                    }
                }
                if (two.length() == 1) {
                    mapping.put("a", two);
                }
            }

            String four = map.get(4);
//            System.out.printf("%s %s", one, four);
            for (int i = 0; i < one.length(); i++) {
                for (int j = 0; j < four.length(); j++) {
                    if (one.charAt(i) == four.charAt(j)) {
                        four = four.replace(four.charAt(j) + "", "");
                    }
                }
                if (four.length() == 2) {
                    mapping.put("bd", four);
                }
            }

            String eight = map.get(8);
            for (String s : mapping.keySet()) {
                for (int i = 0; i < s.length(); i++) {
                    eight = eight.replace(mapping.get(s).charAt(i) + "", "");
                }
            }
            mapping.put("eg", eight);




            System.out.println(mapping);

            // 0 ->

//            StringBuilder result = new StringBuilder();
//            for (String pattern : output) {
//                char[] chars = pattern.toCharArray();
//                Arrays.sort(chars);
//                for (String string : map.keySet()) {
//                    if (new String(chars).equals(string)) {
//                        result.append(map.get(string));
//                    } else {
//                        result.append(".");
//                    }
//                }
//            }
//            System.out.println(result);
        }
    }
}
