package aoc21.day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class SyntaxScoring {

    private static ArrayList<String> input = new ArrayList<>();

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("src/aoc21.day10/input.txt"));
            while (scanner.hasNextLine()) {
                input.add(scanner.nextLine());
            }
            scanner.close();
        } catch (
            FileNotFoundException e) {
            e.printStackTrace();
        }
        syntaxScoring();
    }

    private static void syntaxScoring() {
        int totalSyntaxErrorScore = 0;
        ArrayList<String> incompleteLines = new ArrayList<>(input);
        ArrayList<Long> completionScores = new ArrayList<>();

        for (String line : input) {
            String[] characters = line.split("");
            ArrayList<String> pastChar = new ArrayList<>();
            int lineSyntaxErrorScore = 0;

            for (String character : characters) {
                switch (character) {
                    case "(" -> pastChar.add("(");
                    case "[" -> pastChar.add("[");
                    case "{" -> pastChar.add("{");
                    case "<" -> pastChar.add("<");

                    case ")" -> {
                        if (!Objects.equals(pastChar.get(pastChar.size() - 1), "(")) {
                            System.out.printf("Expected %s, found %s%n", pastChar.get(pastChar.size() - 1), character);
                            lineSyntaxErrorScore += getSyntaxErrorScore(character);
                        }
                        pastChar.remove(pastChar.size() - 1);
                    }
                    case "]" -> {
                        if (!Objects.equals(pastChar.get(pastChar.size() - 1), "[")) {
                            System.out.printf("Expected %s, found %s%n", pastChar.get(pastChar.size() - 1), character);
                            lineSyntaxErrorScore += getSyntaxErrorScore(character);
                        }
                        pastChar.remove(pastChar.size() - 1);
                    }
                    case "}" -> {
                        if (!Objects.equals(pastChar.get(pastChar.size() - 1), "{")) {
                            System.out.printf("Expected %s, found %s%n", pastChar.get(pastChar.size() - 1), character);
                            lineSyntaxErrorScore += getSyntaxErrorScore(character);
                        }
                        pastChar.remove(pastChar.size() - 1);
                    }
                    case ">" -> {
                        if (!Objects.equals(pastChar.get(pastChar.size() - 1), "<")) {
                            System.out.printf("Expected %s, found %s%n", pastChar.get(pastChar.size() - 1), character);
                            lineSyntaxErrorScore += getSyntaxErrorScore(character);
                        }
                        pastChar.remove(pastChar.size() - 1);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + character);
                }
            }
            if (lineSyntaxErrorScore > 0) {
                incompleteLines.remove(line);
            }
            totalSyntaxErrorScore += lineSyntaxErrorScore;
        }
        System.out.printf("Total syntax error score: %s%n", totalSyntaxErrorScore);
        System.out.println(incompleteLines);

        for (String line : incompleteLines) {
            String[] characters = line.split("");
            ArrayList<String> pastChar = new ArrayList<>();

            for (String character : characters) {
                switch (character) {
                    case "(" -> pastChar.add("(");
                    case "[" -> pastChar.add("[");
                    case "{" -> pastChar.add("{");
                    case "<" -> pastChar.add("<");

                    case ")", "]", "}", ">" -> pastChar.remove(pastChar.size() - 1);
                    default -> throw new IllegalStateException("Unexpected value: " + character);
                }
            }
            Collections.reverse(pastChar);
            System.out.printf("%s - ", pastChar);
            long totalScore = 0;
            for (String character : pastChar) {
                totalScore *= 5;
                totalScore += getCompletionScore(character);
            }
            System.out.printf("%s%n", totalScore);
            completionScores.add(totalScore);
        }
        Collections.sort(completionScores);
        System.out.printf("Middle score: %s%n", getMedian(completionScores));
    }

    private static int getSyntaxErrorScore(String character) {
        switch (character) {
            case ")" -> {
                return 3;
            }
            case "]" -> {
                return 57;
            }
            case "}" -> {
                return 1197;
            }
            case ">" -> {
                return 25137;
            }
            default -> throw new IllegalStateException("Unexpected value: " + character);
        }
    }

    private static int getCompletionScore(String character) {
        switch (character) {
            case "(" -> {
                return 1;
            }
            case "[" -> {
                return 2;
            }
            case "{" -> {
                return 3;
            }
            case "<" -> {
                return 4;
            }
            default -> throw new IllegalStateException("Unexpected value: " + character);
        }
    }

    private static long getMedian(ArrayList<Long> values) {
        if (values.size() % 2 == 1)
            return values.get((values.size() + 1) / 2 - 1);
        else {
            long lower = values.get(values.size() / 2 - 1);
            long upper = values.get(values.size() / 2);
            return (long) ((lower + upper) / 2.0);
        }
    }

}
