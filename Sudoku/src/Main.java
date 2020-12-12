public class Main {


    public static void main(String[] args) {
        Sudoku s = new Sudoku(9);
        int count = 0;

        while (count++ < 1000) {
            System.out.println("\n\n****Sudoku " + count + "****");
            int[][] result = s.generateSudoku();

            for (int[] arr : result) {
                for (int value : arr)
                    System.out.print(value + " \t");
                System.out.println();
            }
        }
    }
}
