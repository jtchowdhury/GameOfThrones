import java.util.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.io.PrintStream;

public class Project {
    public static int round;

    public static void main(String[] args) {
        // Initialize the round
        round = 1;

        Interfaces intface = new Interfaces();

        // Intro
        intface.intro();

        int cellNum = intface.cellNum;
        int numPlayers = intface.numPlayers;

        Grid grid = new Grid(cellNum, numPlayers);

        grid.lines(1);
        StdDraw.show();
        StdDraw.pause(100);

        // Print the grid
        printGrid(grid);

        int player = 1;
        boolean foundWinner = false;
        while (true) {
            if (StdDraw.isMousePressed()) {
                int x = grid.getMouseX();
                int y = grid.getMouseY();

                // Play a turn if clicked on the grid
                if (x >= 0 && x < cellNum && y >= 0 && y < cellNum) {
                    player = playTurn(intface, grid, player);
                    StdDraw.pause(100);

                    // Print the grid
                    printGrid(grid);

                    // Check if the game is over
                    if (round > numPlayers+1 && grid.isGameOver()) {
                        foundWinner = true;
                        break;
                    }
                }
            }
        }

        // Winner found
        boolean playAgain = false;
        if (foundWinner) {
            int winner = grid.getWinner();
            playAgain = intface.winnerPage(grid, winner);
        }

        if (playAgain) {
            StdAudio.stopInBackground();
            StdDraw.pause(1000);
            StdDraw.clear();
            StdDraw.show();

            // Reset the round and play again
            main(args);
        }
        else {
            // Quit the program
            System.exit(0);
        }
    }

    // Prints the grid for cross-checking
    public static void printGrid (Grid grid) {
        for (int i=1; i<=grid.getNumPlayers(); i++) {
//            out.println("Player " + i + " sigil count: " + grid.getPlayerSigilCount(i));
        }

        int cellNum = grid.getCellNum();
        for (int i=cellNum-1; i>=0; i--) {
            for (int j=0; j<cellNum; j++) {
//                out.print(grid.getCell(i,j).getPlayer() + " " + grid.getCell(i,j).getSigilCount() + " | ");
            }
//            out.println();
        }
    }

    // Play a turn
    public static int playTurn (Interfaces intface, Grid grid, int player) {
        // If the player has no sigils left, skip the turn
        if (round > grid.getNumPlayers()+1 && grid.getPlayerSigilCount(player) == 0) {
            int nextPlayer = player % grid.getNumPlayers() + 1;
            while (grid.getPlayerSigilCount(nextPlayer) == 0) {
                nextPlayer = nextPlayer % grid.getNumPlayers() + 1;
            }
            player = nextPlayer;
        }

        int x = grid.getMouseX();
        int y = grid.getMouseY();

        boolean flag = grid.addSigil(player, x, y);
        grid.drawCell(player, x, y);

        // Update the player if an sigil is successfully added
        if (flag) {
            player = player % grid.getNumPlayers() + 1;
            round++;
        }
        else {
            // Repaint the grid lines and redraw the map
            intface.drawMap(grid, player);
            grid.lines(player);
        }

        // If the player has no sigils and round is less than number of players, keep drawing lines
        if (round <= grid.getNumPlayers() && grid.getPlayerSigilCount(player) == 0) {
            intface.drawMap(grid, player);
            grid.lines(player);
        }

        return player;
    }
}
