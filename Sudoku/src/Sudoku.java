public class Sudoku {

    private final int[] valueFlag;
    private final int size;
    private final int[][] sudoku;

    public Sudoku(int n) {
        size = n;
        valueFlag = new int[size];
        sudoku = new int[size][size];
    }

    //Naive Algorithm to generate sudoku leave 21 clues
    public int[][] generateSudoku() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sudoku[i][j] = selectValue(i, j); //enter value into sudoku array

                if (sudoku[i][j] == 0) { //if value is 0 redo the row

                    if (count++ >= 30) { //if row has been reset 30 times reset the sudoku array
                        count = 0; //reset the count
                        resetSudoku(); // reset the sudoku
                        i = -1; //redo sudoku array
                        break;
                    }
                    j = -1; //redo the row
                }
                resetValueFlag(); //reset valueFlag array after a new value is entered into sudoku array
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

    //reset valueFlag array to 0
    private void resetValueFlag() {
        for (int i = 0; i < size; i++)
            valueFlag[i] = 0;
    }


    //select value to insert into sudoku Array
    private int selectValue(int i, int j) {

        //check row to see if value is already present and set flag as 1 for the used value
        for (int row = 0; row < i; row++)
            valueFlag[sudoku[row][j] - 1] = 1;

        //check col to see if value is already present and set flag as 1 for the used value
        for (int col = 0; col < j; col++)
            valueFlag[sudoku[i][col] - 1] = 1;

        //check 3x3 square to see if value is already present
        if (i <= 2 && j <= 2)
            checkSquare(0, 2, 0, 2);
        else if (i <= 2 && j <= 5)
            checkSquare(0, 2, 3, 5);
        else if (i <= 2 && j <= 8)
            checkSquare(0, 2, 6, 8);
        else if (i <= 5 && j <= 2)
            checkSquare(3, 5, 0, 2);
        else if (i <= 5 && j <= 5)
            checkSquare(3, 5, 3, 5);
        else if (i <= 5 && j <= 8)
            checkSquare(3, 5, 6, 8);
        else if (i <= 8 && j <= 2)
            checkSquare(6, 8, 0, 2);
        else if (i <= 8 && j <= 5)
            checkSquare(6, 8, 3, 5);
        else if (i <= 8 && j <= 8)
            checkSquare(6, 8, 6, 8);

        int count = 1;
        int[] values = new int[1];

        //store unused values in values array
        for (int index = 0; index < size; index++) {
            if (valueFlag[index] == 0) {
                int[] temp = values;
                values = new int[count];
                System.arraycopy(temp, 0, values, 0, temp.length);
                values[count - 1] = index + 1;
                count++;
            }
        }
        //randomly select and return a value from values array
        return values[(int) (Math.random() * (count - 1))];
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


}
