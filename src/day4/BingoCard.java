package day4;

import java.util.*;

public class BingoCard {

    ArrayList<LinkedHashMap<String, Boolean>> rows = new ArrayList<>();
    LinkedHashMap<String, Boolean> row1 = new LinkedHashMap<>();
    LinkedHashMap<String, Boolean> row2 = new LinkedHashMap<>();
    LinkedHashMap<String, Boolean> row3 = new LinkedHashMap<>();
    LinkedHashMap<String, Boolean> row4 = new LinkedHashMap<>();
    LinkedHashMap<String, Boolean> row5 = new LinkedHashMap<>();


    public BingoCard(String row1, String row2, String row3, String row4, String row5) {
        String[] arr;

        arr = row1.split("\\s+");
        for (String item : arr) {
            if (!item.equals("")) {
                this.row1.put(item, false);
            }
        }
        rows.add(this.row1);

        arr = row2.split("\\s+");
        for (String item : arr) {
            if (!item.equals("")) {
                this.row2.put(item, false);
            }
        }
        rows.add(this.row2);

        arr = row3.split("\\s+");
        for (String item : arr) {
            if (!item.equals("")) {
                this.row3.put(item, false);
            }
        }
        rows.add(this.row3);

        arr = row4.split("\\s+");
        for (String item : arr) {
            if (!item.equals("")) {
                this.row4.put(item, false);
            }
        }
        rows.add(this.row4);

        arr = row5.split("\\s+");
        for (String item : arr) {
            if (!item.equals("")) {
                this.row5.put(item, false);
            }
        }
        rows.add(this.row5);
    }

    private boolean hasHorizontal() {
        for (LinkedHashMap<String, Boolean> row : rows) {
            int trueCounter = 0;
            for (String number : row.keySet()) {
                if (row.get(number)) {
                    trueCounter++;
                }
            }
            if (trueCounter == 5) {
                return true;
            }
        }
        return false;
    }

    private boolean hasVertical() {
        for (int i = 0; i < 5; i++) {
            int trueCounter = 0;
            for (LinkedHashMap<String, Boolean> row : rows) {
                boolean value = (boolean) row.values().toArray()[i];
                if (value) {
                    trueCounter++;
                }
            }
            if (trueCounter == 5) {
                return true;
            }
        }
        return false;
    }

    public boolean hasWon() {
        return hasHorizontal() || hasVertical();
    }

    public int getUnmarkedNumbers() {
        int unmarkedNumbers = 0;
        for (LinkedHashMap<String, Boolean> row : rows) {
            for (String key : row.keySet()) {
                if (!row.get(key)) {
                    unmarkedNumbers += Integer.parseInt(key);
                }
            }
        }
        return unmarkedNumbers;
    }

    public void numberDrawn(String number) {
        for (LinkedHashMap<String, Boolean> row : rows) {
            for (String key : row.keySet()) {
                if (Objects.equals(key, number)) {
                    row.put(key, true);
                }
            }
        }
    }
}
