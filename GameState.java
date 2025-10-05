// GameState.java
public class GameState {
    private boolean[][] horizontalLines;
    private boolean[][] verticalLines;
    private char[][] boxes;
    private int rows;
    private int cols;

    public GameState(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        horizontalLines = new boolean[rows + 1][cols];  // extra row for bottom lines
        verticalLines = new boolean[rows][cols + 1];    // extra col for right lines
        boxes = new char[rows][cols];                   // stores box owner initials
    }

    // Check if a line can be drawn
    public boolean isLineAvailable(int row, int col, boolean isHorizontal) {
        if (isHorizontal)
            return !horizontalLines[row][col];
        else
            return !verticalLines[row][col];
    }

    // Draws a line and checks if it completes any box(es)
    public boolean drawLine(int row, int col, boolean isHorizontal, char playerInitial) {
        if (!isLineAvailable(row, col, isHorizontal))
            return false; // Invalid move

        if (isHorizontal) horizontalLines[row][col] = true;
        else verticalLines[row][col] = true;

        boolean boxCompleted = checkAndMarkBoxes(row, col, isHorizontal, playerInitial);
        return boxCompleted;
    }

    // Removes a line (for undo functionality)
    public void removeLine(int row, int col, boolean isHorizontal) {
        if (isHorizontal) horizontalLines[row][col] = false;
        else verticalLines[row][col] = false;
    }

    // Helper: Check and mark completed boxes
    private boolean checkAndMarkBoxes(int row, int col, boolean isHorizontal, char playerInitial) {
        boolean completed = false;

        // A line can complete up to 2 boxes — one on each side
        if (isHorizontal) {
            // Check box above
            if (row > 0 && isBoxCompleted(row - 1, col)) {
                boxes[row - 1][col] = playerInitial;
                completed = true;
            }
            // Check box below
            if (row < rows && isBoxCompleted(row, col)) {
                boxes[row][col] = playerInitial;
                completed = true;
            }
        } else {
            // Check box to the left
            if (col > 0 && isBoxCompleted(row, col - 1)) {
                boxes[row][col - 1] = playerInitial;
                completed = true;
            }
            // Check box to the right
            if (col < cols && isBoxCompleted(row, col)) {
                boxes[row][col] = playerInitial;
                completed = true;
            }
        }

        return completed;
    }

    // Checks if all four sides of a box are filled
    private boolean isBoxCompleted(int row, int col) {
        return horizontalLines[row][col] && horizontalLines[row + 1][col] &&
               verticalLines[row][col] && verticalLines[row][col + 1];
    }

    // Getters (for Board rendering)
    public boolean isHorizontalLineDrawn(int row, int col) {
        return horizontalLines[row][col];
    }

    public boolean isVerticalLineDrawn(int row, int col) {
        return verticalLines[row][col];
    }

    public char getBoxOwner(int row, int col) {
        return boxes[row][col] == '\0' ? ' ' : boxes[row][col];
    }

    // Check if the game is over (all boxes filled)
    public boolean isGameOver() {
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                if (boxes[r][c] == '\0')
                    return false;
        return true;
    }
}
