// Board.java
public class Board {
    private int rows;
    private int cols;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    // Optional: Display grid (for console visualization)
    public void displayBoard(GameState state) {
        for (int r = 0; r < rows; r++) {
            // Print horizontal lines and dots
            for (int c = 0; c < cols; c++) {
                System.out.print("•");
                if (state.isHorizontalLineDrawn(r, c)) System.out.print("──");
                else System.out.print("  ");
            }
            System.out.println("•");

            // Print vertical lines and boxes (except last row)
            if (r < rows - 1) {
                for (int c = 0; c < cols; c++) {
                    if (state.isVerticalLineDrawn(r, c)) System.out.print("│ ");
                    else System.out.print("  ");
                    System.out.print(state.getBoxOwner(r, c));
                }
                if (state.isVerticalLineDrawn(r, cols)) System.out.print("│");
                System.out.println();
            }
        }
    }
}
