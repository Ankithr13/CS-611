// Player.java
public class Player {
    private String name;
    private char symbol; // e.g., 'A' or 'C' for Computer
    private int score;
    private int movesPlayed;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
        this.score = 0;
        this.movesPlayed = 0;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }

    public int getScore() {
        return score;
    }

    public int getMovesPlayed() {
        return movesPlayed;
    }

    public void incrementScore() {
        score++;
    }

    public void incrementMoves() {
        movesPlayed++;
    }

    public void reset() {
        score = 0;
        movesPlayed = 0;
    }
}
