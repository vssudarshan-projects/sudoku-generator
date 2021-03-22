public class Sudoku {

    private final int[] valueFlag;
    private final int size;
    private final int[][] sudoku;
    public static int bigCount;
    public static int totalCount;

    public Sudoku() {
        size = 9;
        valueFlag = new int[size];
        sudoku = new int[size][size];
    }

    //Naive Algorithm to generate sudoku leave 21 clues
    public int[][] generateSudoku() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sudoku[i][j] = selectValue(i, j); //enter value into sudoku array
                totalCount++;
                if (sudoku[i][j] == 0) { //if value is 0 redo the row

                    if (count++ >= 40) { //if row has been for set threshold times reset the sudoku array
                        count = 0; //reset the count
                        resetSudoku(); // reset the sudoku
                        bigCount++;
                        i = -1; //redo sudoku array
                        break;
                    }
                    j = -1; //redo the row
                    resetRow(i);
                }
                resetFlag(); //reset valueFlag array after a new value is entered into sudoku array
            }
        }
        return sudoku; //return the valid sudoku
    }

    //reset sudoku array to 0
    private void resetSudoku() {

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                sudoku[i][j] = 0;
    }

    //reset the row
    private void resetRow(int i) {

        for (int j = 0; j < size; j++)
            sudoku[i][j] = 0;
    }

    //reset valueFlag array to 0
    private void resetFlag() {

        for (int j = 0; j < size; j++)
            valueFlag[j] = 0;
    }


    //select value to insert into sudoku Array
    private int selectValue(int i, int j) {
        //check 3x3 square to see if value is already present
        if (i <= 2 && j <= 2)
            checkSquare(0, 2, 0, 2);
        else if (i <= 2 && j <= 5) {
            checkSquare(0, 2, 3, 5);
            checkRow(3, i);
        } else if (i <= 2 && j <= 8) {
            checkSquare(0, 2, 6, 8);
            checkRow(6, i);
        } else if (i <= 5 && j <= 2) {
            checkSquare(3, 5, 0, 2);
            checkCol(3, j);
        } else if (i <= 5 && j <= 5) {
            checkSquare(3, 5, 3, 5);
            checkRow(3, i);
            checkCol(3, j);
        } else if (i <= 5 && j <= 8) {
            checkSquare(3, 5, 6, 8);
            checkRow(6, i);
            checkCol(3, j);
        } else if (i <= 8 && j <= 2) {
            checkSquare(6, 8, 0, 2);
            checkCol(6, j);
        } else if (i <= 8 && j <= 5) {
            checkSquare(6, 8, 3, 5);
            checkRow(3, i);
            checkCol(6, j);
        } else if (i <= 8 && j <= 8) {
            checkSquare(6, 8, 6, 8);
            checkRow(6, i);
            checkCol(6, j);
        }

        int count = 0;
        int[] values = new int[9];

        //store unused values in values array
        for (int index = 0; index < size; index++) {
            if (valueFlag[index] == 0) {
                values[count] = index + 1;
                count++;
            }
        }
        //randomly select and return a value from values array
        return values[(int) (Math.random() * count)];
    }

    //check 3x3 square to see if value is already present
    private void checkSquare(int iStart, int iFin, int jStart, int jFin) {
        for (int i = iStart; i <= iFin; i++)
            for (int j = jStart; j <= jFin; j++) {
                if (sudoku[i][j] == 0)
                    continue;
                valueFlag[sudoku[i][j] - 1] = 1;
            }
    }

    private void checkRow(int jFin, int i) {
        //check col to see if value is already present
        for (int col = 0; col < jFin; col++)
            valueFlag[sudoku[i][col] - 1] = 1;

    }

    private void checkCol(int iFin, int j) {
        //check row to see if value is already present
        for (int row = 0; row < iFin; row++)
            valueFlag[sudoku[row][j] - 1] = 1;
    }

}
