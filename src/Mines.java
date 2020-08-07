import java.util.Random;

import static jdk.nashorn.internal.objects.NativeMath.random;

public class Mines {

    int gridSize;
    int numMines;
    int[][] mines;
    boolean[][] revealed;

    Mines(int size) {
        if (size == 0) {
            gridSize = 9;
            numMines = 10;
        } else if (size == 1) {
            gridSize = 16;
            numMines = 40;
        } else {
            gridSize = 24;
            numMines = 99;
        }
    }

    void setup() {
        mines = new int[gridSize][gridSize];
        revealed = new boolean[gridSize][gridSize];
        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                mines[x][y] = 0;
                revealed[x][y] = false;
            }
        }
    }

    void placeMines() {
        int i = 0;
        while (i < numMines) {
            Random random = new Random();
            int x = random.nextInt(gridSize);
            int y = random.nextInt(gridSize);
            if (mines[x][y] == 1) continue;
            mines[x][y] = 1;
            i++;
        }
    }

    private int calcNear(int x, int y) {
        if (outBounds(x, y)) return 0;
        int i = 0;
        for (int offsetX = -1; offsetX <= 1; offsetX++) {
            for (int offsetY = -1; offsetY <= 1; offsetY++) {
                if (outBounds(offsetX + x, offsetY + y)) continue;
                i += mines[offsetX + x][offsetY + y];
            }
        }
        return i;
    }

    private boolean outBounds(int x, int y) {
        return x < 0 || y < 0 || x >= gridSize || y >= gridSize;
    }

    void reveal(int x, int y) {
        if (outBounds(x, y)) return;
        if (revealed[x][y]) return;
        revealed[x][y] = true;
        if (calcNear(x, y) != 0) return;
        reveal(x - 1, y - 1);
        reveal(x - 1, y + 1);
        reveal(x + 1, y - 1);
        reveal(x + 1, y + 1);
        reveal(x - 1, y);
        reveal(x + 1, y);
        reveal(x, y - 1);
        reveal(x, y + 1);
    }

    void printMines() {
        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                if (revealed[x][y]) {
                    if (mines[x][y] == 1) {
                        System.out.print("*");
                        continue;
                    }
                    if (calcNear(x, y) == 0) {
                        System.out.print(" ");
                    } else {
                        System.out.print(calcNear(x, y));
                    }
                } else {
                    System.out.print("-");
                    continue;
                }


            }
            System.out.println();
        }
    }

    void finalPrintMines() {
        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                if (mines[x][y] == 1) {
                    System.out.print("*");
                    continue;
                }
                if (calcNear(x, y) == 0) {
                    System.out.print(" ");
                } else {
                    System.out.print(calcNear(x, y));
                }


            }
            System.out.println();
        }
    }

    boolean hasUnRevealed() {
        for (int x = 0; x < gridSize; x++) {
            for (int y = 0; y < gridSize; y++) {
                if (!revealed[x][y]) {
                    return true;

                }
            }
        }
        return false;
    }
}
