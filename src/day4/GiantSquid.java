package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GiantSquid {

    public static ArrayList<String> input = new ArrayList<>();
    public static ArrayList<String> numbers = new ArrayList<>();
    public static ArrayList<BingoCard> bingoCards = new ArrayList<>();

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("src/day4/input.txt"));
            while (scanner.hasNextLine()) {
                input.add(scanner.nextLine());
            }
            scanner.close();
            handleData();
            winFirst();
            winLast();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void handleData() {
        String numbers = input.get(0);
        String[] numbersArray = numbers.split(",");
        GiantSquid.numbers.addAll(Arrays.asList(numbersArray));
        input.remove(0);
        while (input.size() >= 6) {
            String row1 = "";
            String row2 = "";
            String row3 = "";
            String row4 = "";
            String row5 = "";

            for (int i = 0; i < 6; i++) {
                switch (i) {
                    case 1 -> row1 = input.get(0);
                    case 2 -> row2 = input.get(0);
                    case 3 -> row3 = input.get(0);
                    case 4 -> row4 = input.get(0);
                    case 5 -> row5 = input.get(0);
                    default -> System.out.print("");
                }
                input.remove(0);
            }
            GiantSquid.bingoCards.add(new BingoCard(row1, row2, row3, row4, row5));
        }
    }

    public static HashMap<BingoCard, String> game() throws Exception {
        for (String number : numbers) {
            for (BingoCard bingoCard : bingoCards) {
                bingoCard.numberDrawn(number);
                if (bingoCard.hasWon()) {
                    HashMap<BingoCard, String> winner = new HashMap<>();
                    winner.put(bingoCard, number);
                    return winner;
                }
            }
        }
        throw new Exception("No winner");
    }

    public static void winFirst() {
        try {
            HashMap<BingoCard, String> winner = game();
            for (BingoCard bingoCard : winner.keySet()) {
                int unmarkedNumbers = bingoCard.getUnmarkedNumbers();
                int justCalledNumber = Integer.parseInt(winner.get(bingoCard));
                System.out.printf("First card to win: Final score = %s * %s = %s%n", unmarkedNumbers, justCalledNumber,
                    unmarkedNumbers * justCalledNumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void winLast() {
        ArrayList<BingoCard> bingoCardsCopy = new ArrayList<>(bingoCards);
        boolean oneLeft = false;

        for (String number : numbers) {
            for (BingoCard bingoCard : bingoCards) {
                if (!bingoCardsCopy.contains(bingoCard)) {
                    continue;
                }
                bingoCard.numberDrawn(number);
                if (bingoCard.hasWon()) {
                    if (!oneLeft) {
                        bingoCardsCopy.remove(bingoCard);
                    }
                    if (bingoCardsCopy.size() == 1) {
                        bingoCardsCopy.get(0).numberDrawn(number);
                        if (!oneLeft) {
                            oneLeft = true;
                            break;
                        } else {
                            int unmarkedNumbers = bingoCardsCopy.get(0).getUnmarkedNumbers();
                            System.out.printf("Last Card to win: Final score = %s * %s = %s%n", unmarkedNumbers, number,
                                unmarkedNumbers * Integer.parseInt(number));
                            return;
                        }
                    }
                }
            }
        }
    }
}
