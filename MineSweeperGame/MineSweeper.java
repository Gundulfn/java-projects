import java.util.Random;

public class MineSweeper {
    private int rowCount, colCount;
    private int[][] mineFieldArray;
    private final int MINE_COUNT;
    private int clearedFieldPartCount;
    private final double MINE_COUNT_RATE = 0.4;
    private final int MINE_NUM = -1;
    private final int SELECTED_FIELD_PART_MULTIPLIER = 10;

    public void placeNearbyColumnValues(int rowIndex, int colIndex) {
        if (colIndex > 0 && mineFieldArray[rowIndex][colIndex - 1] != MINE_NUM) {
            mineFieldArray[rowIndex][colIndex - 1]++;
        }

        if (colIndex < colCount - 1 && mineFieldArray[rowIndex][colIndex + 1] != MINE_NUM) {
            mineFieldArray[rowIndex][colIndex + 1]++;
        }
    }

    public int getRandomInt(Random rand, int exclusiveLimit) {
        return rand.nextInt(0, exclusiveLimit);
    }

    public void printMineField(boolean isPrintingMines) {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (mineFieldArray[i][j] >= SELECTED_FIELD_PART_MULTIPLIER) {
                    System.out.print(mineFieldArray[i][j] / SELECTED_FIELD_PART_MULTIPLIER + "\t");
                } else {
                    if (isPrintingMines && mineFieldArray[i][j] == MINE_NUM)
                        System.out.print("*\t");
                    else
                        System.out.print("-\t");
                }
            }

            System.out.println();
        }
    }

    public boolean isSelectionInMineField(int x, int y) {
        return x >= 0 && x < rowCount && y >= 0 && y < colCount;
    }

    public boolean isThereHiddenEmptyFieldPart(){
        return clearedFieldPartCount < (rowCount * colCount) - MINE_COUNT;
    }

    public GameState selectFieldPart(int x, int y) {
        if (mineFieldArray[x][y] == MINE_NUM) {
            return GameState.LOSE_GAME;
        } else {
            mineFieldArray[x][y] *= SELECTED_FIELD_PART_MULTIPLIER;
            clearedFieldPartCount++;

            if (!isThereHiddenEmptyFieldPart()) {
                return GameState.WIN_GAME;
            } else {
                return GameState.CONTINUE_GAME;
            }
        }
    }

    public MineSweeper(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;

        mineFieldArray = new int[rowCount][colCount];
        MINE_COUNT = (int) ((rowCount * colCount) * MINE_COUNT_RATE);

        Random rand = new Random();

        // Placing mines
        int randomMineRowIndex = 0, randomMineColIndex = 0;
        for (int i = 0; i < MINE_COUNT; i++) {

            randomMineRowIndex = getRandomInt(rand, rowCount);
            randomMineColIndex = getRandomInt(rand, colCount);

            while (mineFieldArray[randomMineRowIndex][randomMineColIndex] == MINE_NUM) {
                randomMineRowIndex = getRandomInt(rand, rowCount);
                randomMineColIndex = getRandomInt(rand, colCount);
            }

            mineFieldArray[randomMineRowIndex][randomMineColIndex] = -1;
            placeNearbyColumnValues(randomMineRowIndex, randomMineColIndex);

            if (randomMineRowIndex > 0) {
                if (mineFieldArray[randomMineRowIndex - 1][randomMineColIndex] != MINE_NUM)
                    mineFieldArray[randomMineRowIndex - 1][randomMineColIndex]++;

                placeNearbyColumnValues(randomMineRowIndex - 1, randomMineColIndex);
            }

            if (randomMineRowIndex < rowCount - 1) {
                if (mineFieldArray[randomMineRowIndex + 1][randomMineColIndex] != -1)
                    mineFieldArray[randomMineRowIndex + 1][randomMineColIndex]++;


                placeNearbyColumnValues(randomMineRowIndex + 1, randomMineColIndex);
            }
        }

        printMineField(true);
    }
}