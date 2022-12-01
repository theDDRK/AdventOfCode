package aoc21.day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SmokeBasin {

    private static int[][] input = new int[10][10];

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("src/aoc21.day9/exampleinput.txt"));
            int y = 0;
            int x = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (x = 0; x < line.length(); x++) {
                    input[x][y] = Integer.parseInt(String.valueOf(line.charAt(x)));
                }
                y++;
            }
            lowPoints(x, y);
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void lowPoints(int maxX, int maxY) {
        ArrayList<Integer> lowPoints = new ArrayList<>();
        ArrayList<Integer> basinSizes = new ArrayList<>();

        for (int y = 0; y < input.length; y++) {
            for (int x = 0; x < input.length; x++) {
                //top left
                if (x == 0 && y == 0) {
                    if (input[x][y] < input[x + 1][y] && input[x][y] < input[x][y + 1]) {
                        lowPoints.add(input[x][y]);
                        ArrayList<Integer> basin = basin(x, y, maxX, maxY);
                        basinSizes.add(basin.get(0) - basin.get(1) / 2);
                    }
                }

                //top right
                else if (x == maxX - 1 && y == 0) {
                    if (input[x][y] < input[x - 1][y] && input[x][y] < input[x][y + 1]) {
                        lowPoints.add(input[x][y]);
                        ArrayList<Integer> basin = basin(x, y, maxX, maxY);
                        basinSizes.add(basin.get(0) - basin.get(1) / 2);
                    }
                }

                //bottom left
                else if (x == 0 && y == maxY - 1) {
                    if (input[x][y] < input[x + 1][y] && input[x][y] < input[x][y - 1]) {
                        lowPoints.add(input[x][y]);
                        ArrayList<Integer> basin = basin(x, y, maxX, maxY);
                        basinSizes.add(basin.get(0) - basin.get(1) / 2);
                    }
                }

                //bottom right
                else if (x == maxX - 1 && y == maxY - 1) {
                    if (input[x][y] < input[x - 1][y] && input[x][y] < input[x][y - 1]) {
                        lowPoints.add(input[x][y]);
                        ArrayList<Integer> basin = basin(x, y, maxX, maxY);
                        basinSizes.add(basin.get(0) - basin.get(1) / 2);
                    }
                }

                //top
                else if (y == 0) {
                    if (input[x][y] < input[x - 1][y] && input[x][y] < input[x][y + 1] &&
                        input[x][y] < input[x + 1][y]) {
                        lowPoints.add(input[x][y]);
                        ArrayList<Integer> basin = basin(x, y, maxX, maxY);
                        basinSizes.add(basin.get(0) - basin.get(1) / 2);
                    }
                }

                //bottom
                else if (y == maxY - 1) {
                    if (input[x][y] < input[x - 1][y] && input[x][y] < input[x][y - 1] &&
                        input[x][y] < input[x + 1][y]) {
                        lowPoints.add(input[x][y]);
                        ArrayList<Integer> basin = basin(x, y, maxX, maxY);
                        basinSizes.add(basin.get(0) - basin.get(1) / 2);
                    }
                }

                //left
                else if (x == 0) {
                    if (input[x][y] < input[x][y - 1] && input[x][y] < input[x][y + 1] &&
                        input[x][y] < input[x + 1][y]) {
                        lowPoints.add(input[x][y]);
                        ArrayList<Integer> basin = basin(x, y, maxX, maxY);
                        basinSizes.add(basin.get(0) - basin.get(1) / 2);
                    }
                }

                //right
                else if (x == maxX - 1) {
                    if (input[x][y] < input[x][y - 1] && input[x][y] < input[x][y + 1] &&
                        input[x][y] < input[x - 1][y]) {
                        lowPoints.add(input[x][y]);
                        ArrayList<Integer> basin = basin(x, y, maxX, maxY);
                        basinSizes.add(basin.get(0) - basin.get(1) / 2);
                    }
                }

                //middle
                else {
                    if (input[x][y] < input[x][y - 1] && input[x][y] < input[x][y + 1] &&
                        input[x][y] < input[x - 1][y] &&
                        input[x][y] < input[x + 1][y]) {
                        lowPoints.add(input[x][y]);
                        ArrayList<Integer> basin = basin(x, y, maxX, maxY);
                        System.out.println(basin);
                        basinSizes.add(basin.get(0) - basin.get(0) / 2);
                    }
                }
            }
        }
        System.out.println(lowPoints);
        int total = 0;
        for (int point : lowPoints) {
            total += point + 1;
        }
        System.out.println(total);

        Collections.sort(basinSizes);
        Collections.reverse(basinSizes);
        System.out.println(basinSizes);
        int totalBasins = 1;
        for (int i = 0; i < 3; i++) {
            totalBasins *= basinSizes.get(i);
        }
        System.out.println(totalBasins);
    }

    public static ArrayList<Integer> basin(int x, int y, int maxX, int maxY) {
        ArrayList<Integer> basinSize = new ArrayList<>() {{
            add(1);
            add(0);
        }};

        //top left
        if (x == 0 && y == 0) {
            if (input[x + 1][y] != 9 && input[x + 1][y] > input[x][y]) {
                ArrayList<Integer> basin = basin(x + 1, y, maxX, maxY);
                basinSize.add(0, basinSize.get(0) + basin.get(0));
                basinSize.remove(1);
            }
            if (input[x][y + 1] != 9 && input[x][y + 1] > input[x][y]) {
                ArrayList<Integer> basin = basin(x, y + 1, maxX, maxY);
                basinSize.add(0, basinSize.get(0) + basin.get(0));
                basinSize.remove(1);
                basinSize.add(1, basinSize.get(1) + basin.get(1));
                basinSize.remove(2);
            }
        }

        //top right
        else if (x == maxX - 1 && y == 0) {
            if (input[x - 1][y] != 9 && input[x - 1][y] > input[x][y]) {
                ArrayList<Integer> basin = basin(x - 1, y, maxX, maxY);
                basinSize.add(0, basinSize.get(0) + basin.get(0));
                basinSize.remove(1);
                basinSize.add(1, basinSize.get(1) + basin.get(1));
                basinSize.remove(2);
            }
            if (input[x][y + 1] != 9 && input[x][y + 1] > input[x][y]) {
                ArrayList<Integer> basin = basin(x, y + 1, maxX, maxY);
                basinSize.add(0, basinSize.get(0) + basin.get(0));
                basinSize.remove(1);
                basinSize.add(1, basinSize.get(1) + basin.get(1));
                basinSize.remove(2);
            }
        }

        //bottom left
        else if (x == 0 && y == maxY - 1) {
            if (input[x + 1][y] != 9 && input[x + 1][y] > input[x][y]) {
                ArrayList<Integer> basin = basin(x + 1, y, maxX, maxY);
                basinSize.add(0, basinSize.get(0) + basin.get(0));
                basinSize.remove(1);
                basinSize.add(1, basinSize.get(1) + basin.get(1));
                basinSize.remove(2);
            }
            if (input[x][y - 1] != 9 && input[x][y - 1] > input[x][y]) {
                ArrayList<Integer> basin = basin(x, y - 1, maxX, maxY);
                basinSize.add(0, basinSize.get(0) + basin.get(0));
                basinSize.remove(1);
                basinSize.add(1, basinSize.get(1) + basin.get(1));
                basinSize.remove(2);
            }
        }

        //bottom right
        else if (x == maxX - 1 && y == maxY - 1) {
            if (input[x - 1][y] != 9 && input[x - 1][y] > input[x][y]) {
                ArrayList<Integer> basin = basin(x - 1, y, maxX, maxY);
                basinSize.add(0, basinSize.get(0) + basin.get(0));
                basinSize.remove(1);
                basinSize.add(1, basinSize.get(1) + basin.get(1));
                basinSize.remove(2);
            }
            if (input[x][y - 1] != 9 && input[x][y - 1] > input[x][y]) {
                ArrayList<Integer> basin = basin(x, y - 1, maxX, maxY);
                basinSize.add(0, basinSize.get(0) + basin.get(0));
                basinSize.remove(1);
                basinSize.add(1, basinSize.get(1) + basin.get(1));
                basinSize.remove(2);
            }
        }

        //top
        else if (y == 0) {
            if (input[x - 1][y] != 9 && input[x - 1][y] > input[x][y]) {
                ArrayList<Integer> basin = basin(x - 1, y, maxX, maxY);
                basinSize.add(0, basinSize.get(0) + basin.get(0));
                basinSize.remove(1);
                basinSize.add(1, basinSize.get(1) + basin.get(1));
                basinSize.remove(2);
            }
            if (input[x + 1][y] != 9 && input[x + 1][y] > input[x][y]) {
                ArrayList<Integer> basin = basin(x + 1, y, maxX, maxY);
                basinSize.add(0, basinSize.get(0) + basin.get(0));
                basinSize.remove(1);
                basinSize.add(1, basinSize.get(1) + basin.get(1));
                basinSize.remove(2);
            }
            if (input[x][y + 1] != 9 && input[x][y + 1] > input[x][y]) {
                ArrayList<Integer> basin = basin(x, y + 1, maxX, maxY);
                basinSize.add(0, basinSize.get(0) + basin.get(0));
                basinSize.remove(1);
                basinSize.add(1, basinSize.get(1) + basin.get(1));
                basinSize.remove(2);
            }
        }

        //bottom
        else if (y == maxY - 1) {
            if (input[x - 1][y] != 9 && input[x - 1][y] > input[x][y]) {
                ArrayList<Integer> basin = basin(x - 1, y, maxX, maxY);
                basinSize.add(0, basinSize.get(0) + basin.get(0));
                basinSize.remove(1);
                basinSize.add(1, basinSize.get(1) + basin.get(1));
                basinSize.remove(2);
            }
            if (input[x + 1][y] != 9 && input[x + 1][y] > input[x][y]) {
                ArrayList<Integer> basin = basin(x + 1, y, maxX, maxY);
                basinSize.add(0, basinSize.get(0) + basin.get(0));
                basinSize.remove(1);
                basinSize.add(1, basinSize.get(1) + basin.get(1));
                basinSize.remove(2);
            }
            if (input[x][y - 1] != 9 && input[x][y - 1] > input[x][y]) {
                ArrayList<Integer> basin = basin(x, y - 1, maxX, maxY);
                basinSize.add(0, basinSize.get(0) + basin.get(0));
                basinSize.remove(1);
                basinSize.add(1, basinSize.get(1) + basin.get(1));
                basinSize.remove(2);
            }
        }

        //left
        else if (x == 0) {
            if (input[x][y - 1] != 9 && input[x][y - 1] > input[x][y]) {
                ArrayList<Integer> basin = basin(x, y - 1, maxX, maxY);
                basinSize.add(0, basinSize.get(0) + basin.get(0));
                basinSize.remove(1);
                basinSize.add(1, basinSize.get(1) + basin.get(1));
                basinSize.remove(2);
            }
            if (input[x][y + 1] != 9 && input[x][y + 1] > input[x][y]) {
                ArrayList<Integer> basin = basin(x, y + 1, maxX, maxY);
                basinSize.add(0, basinSize.get(0) + basin.get(0));
                basinSize.remove(1);
                basinSize.add(1, basinSize.get(1) + basin.get(1));
                basinSize.remove(2);
            }
            if (input[x + 1][y] != 9 && input[x + 1][y] > input[x][y]) {
                ArrayList<Integer> basin = basin(x + 1, y, maxX, maxY);
                basinSize.add(0, basinSize.get(0) + basin.get(0));
                basinSize.remove(1);
                basinSize.add(1, basinSize.get(1) + basin.get(1));
                basinSize.remove(2);
            }
        }

        //right
        else if (x == maxX - 1) {
            if (input[x][y - 1] != 9 && input[x][y - 1] > input[x][y]) {
                ArrayList<Integer> basin = basin(x, y - 1, maxX, maxY);
                basinSize.add(0, basinSize.get(0) + basin.get(0));
                basinSize.remove(1);
                basinSize.add(1, basinSize.get(1) + basin.get(1));
                basinSize.remove(2);
            }
            if (input[x][y + 1] != 9 && input[x][y + 1] > input[x][y]) {
                ArrayList<Integer> basin = basin(x, y + 1, maxX, maxY);
                basinSize.add(0, basinSize.get(0) + basin.get(0));
                basinSize.remove(1);
                basinSize.add(1, basinSize.get(1) + basin.get(1));
                basinSize.remove(2);
            }
            if (input[x - 1][y] != 9 && input[x - 1][y] > input[x][y]) {
                ArrayList<Integer> basin = basin(x - 1, y, maxX, maxY);
                basinSize.add(0, basinSize.get(0) + basin.get(0));
                basinSize.remove(1);
                basinSize.add(1, basinSize.get(1) + basin.get(1));
                basinSize.remove(2);
            }
        }

        //middle
        else {
            basinSize.add(1, basinSize.get(1) + 1);
            basinSize.remove(2);
            if (input[x][y - 1] != 9 && input[x][y - 1] > input[x][y]) {
                ArrayList<Integer> basin = basin(x, y - 1, maxX, maxY);
                basinSize.add(0, basinSize.get(0) + basin.get(0));
                basinSize.remove(1);
            }
            if (input[x][y + 1] != 9 && input[x][y + 1] > input[x][y]) {
                ArrayList<Integer> basin = basin(x, y + 1, maxX, maxY);
                basinSize.add(0, basinSize.get(0) + basin.get(0));
                basinSize.remove(1);
//                basinSize.add(1, basinSize.get(1) + basin.get(1) + 1);
//                basinSize.remove(2);
            }
            if (input[x - 1][y] != 9 && input[x - 1][y] > input[x][y]) {
                ArrayList<Integer> basin = basin(x - 1, y, maxX, maxY);
                basinSize.add(0, basinSize.get(0) + basin.get(0));
                basinSize.remove(1);
//                basinSize.add(1, basinSize.get(1) + basin.get(1) + 1);
//                basinSize.remove(2);
            }
            if (input[x + 1][y] != 9 && input[x + 1][y] > input[x][y]) {
                ArrayList<Integer> basin = basin(x + 1, y, maxX, maxY);
                basinSize.add(0, basinSize.get(0) + basin.get(0));
                basinSize.remove(1);
//                basinSize.add(1, basinSize.get(1) + basin.get(1) + 1);
//                basinSize.remove(2);
            }
        }
        return basinSize;
    }

}
