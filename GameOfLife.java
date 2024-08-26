public class GameOfLife {
    private static final int SIZE = 25;
    private int[][] board;

    public GameOfLife() {
        board = new int[SIZE][SIZE];
    }

    // Initialize the board with the Glider pattern
    public void initializeGlider() {
        board[1][2] = 1;
        board[2][3] = 1;
        board[3][1] = 1;
        board[3][2] = 1;
        board[3][3] = 1;
    }

    // Calculate the next generation in place
    public void nextGeneration() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                int liveNeighbors = countLiveNeighbours(i, j);

                if (board[i][j] == 1) {
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        board[i][j] = -1; // cell dies
                    }
                } else {
                    if (liveNeighbors == 3) {
                        board[i][j] = 2; // cell becomes alive
                    }
                }
            }
        }

        // Update board to the next state
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 0;
                } else if (board[i][j] == 2) {
                    board[i][j] = 1;
                }
            }
        }
    }

    // Count live neighbors around a given cell
    private int countLiveNeighbours(int row, int col) {
        int count = 0;
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int x = row + dx[i];
            int y = col + dy[i];

            if (x >= 0 && x < SIZE && y >= 0 && y < SIZE && (board[x][y] & 1) == 1) {
                count++;
            }
        }

        return count;
    }

    // Print the current state of the board
    public void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print((board[i][j] & 1) == 1 ? "O" : ".");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        GameOfLife game = new GameOfLife();
        game.initializeGlider();

        System.out.println("Initial State:");
        game.printBoard();

        for (int i = 0; i < 5; i++) {  // Run for 5 generations
            game.nextGeneration();
            System.out.println("Generation " + (i + 1) + ":");
            game.printBoard();
        }
    }
}
