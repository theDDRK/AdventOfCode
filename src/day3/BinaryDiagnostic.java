package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class BinaryDiagnostic {

    public static ArrayList<String> report = new ArrayList<>();

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("src/day3/input.txt"));
            while (scanner.hasNextLine()) {
                report.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String gammaRate = gammaRate();
        String epsilonRate = epsilonRate(gammaRate);
        powerConsumption(Integer.parseInt(gammaRate, 2), Integer.parseInt(epsilonRate, 2));
        String oxygenGeneratorRating = oxygenGeneratorRating();
        String CO2ScrubberRating = CO2ScrubberRating();
        lifeSupportRating(Integer.parseInt(oxygenGeneratorRating, 2), Integer.parseInt(CO2ScrubberRating, 2));
    }

    public static String gammaRate() {
        StringBuilder gammaRate = new StringBuilder();
        for (int i = 0; i < report.get(0).length(); i++) {
            int zeros = 0;
            int ones = 0;

            for (String number : report) {

                char bit = number.charAt(i);

                if (bit == '1') {
                    ones++;
                } else {
                    zeros++;
                }
            }
            if (ones > zeros) {
                gammaRate.append("1");
            } else {
                gammaRate.append("0");
            }
        }
        System.out.printf("Gamma rate: %s or %s%n", gammaRate, Integer.parseInt(gammaRate.toString(), 2));
        return gammaRate.toString();
    }

    public static String epsilonRate(String gammaRate) {
        StringBuilder epsilonRate = new StringBuilder();

        for (int i = 0; i < gammaRate.length(); i++) {
            if (gammaRate.charAt(i) == '1') {
                epsilonRate.append('0');
            } else {
                epsilonRate.append('1');
            }
        }
        System.out.printf("Epsilon rate: %s or %s%n", epsilonRate, Integer.parseInt(epsilonRate.toString(), 2));
        return epsilonRate.toString();
    }

    public static void powerConsumption(int gammaRate, int epsilonRate) {
        System.out.printf("Power consumption: %s%n", gammaRate * epsilonRate);
    }

    public static String oxygenGeneratorRating() {
        ArrayList<String> values = new ArrayList<>(report);

        StringBuilder oxygenGeneratorRating = new StringBuilder();
        for (int i = 0; i < report.get(0).length(); i++) {
            int zeros = 0;
            int ones = 0;

            for (String number : values) {

                char bit = number.charAt(i);

                if (bit == '1') {
                    ones++;
                } else {
                    zeros++;
                }
            }

            if (ones > zeros || ones == zeros) {
                oxygenGeneratorRating.append("1");
            } else {
                oxygenGeneratorRating.append("0");
            }

            for (String value : report) {
                if (value.charAt(i) != oxygenGeneratorRating.charAt(i)) {
                    if (report.contains(value) && values.size() > 1) {
                        values.remove(value);
                    }
                }
            }
        }
        System.out.printf("Oxygen generator rating: %s or %s%n", values.get(0), Integer.parseInt(values.get(0), 2));
        return values.get(0);
    }

    private static String CO2ScrubberRating() {
        ArrayList<String> values = new ArrayList<>(report);

        StringBuilder CO2ScrubberRating = new StringBuilder();
        for (int i = 0; i < report.get(0).length(); i++) {
            int zeros = 0;
            int ones = 0;

            for (String number : values) {

                char bit = number.charAt(i);

                if (bit == '1') {
                    ones++;
                } else {
                    zeros++;
                }
            }

            if (ones > zeros || ones == zeros) {
                CO2ScrubberRating.append("0");
            } else {
                CO2ScrubberRating.append("1");
            }

            for (String value : report) {
                if (value.charAt(i) != CO2ScrubberRating.charAt(i)) {
                    if (report.contains(value) && values.size() > 1) {
                        values.remove(value);
                    }
                }
            }
        }
        System.out.printf("CO2 scrubber rating: %s or %s%n", values.get(0), Integer.parseInt(values.get(0), 2));
        return values.get(0);
    }

    public static void lifeSupportRating(int oxygenGeneratorRating, int CO2ScrubberRating) {
        System.out.printf("Life support rating: %s%n", oxygenGeneratorRating * CO2ScrubberRating);
    }
}
