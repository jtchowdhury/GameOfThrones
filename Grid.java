import java.awt.Font;
import java.awt.*;

public class Grid {
    private Cell[][] grid;
    private int cellNum;
    private int numPlayers;
    private int[] playerSigilCount;
    private double[] sound;
    private Interfaces intface = new Interfaces();

    // Constructor
    public Grid (int cellNum, int numPlayers) {
        this.cellNum = cellNum;
        grid = new Cell[cellNum][cellNum];

        for (int i=0; i<cellNum; i++)
            for (int j=0; j<cellNum; j++)
                grid[i][j] = new Cell(0, 0);

        this.numPlayers = numPlayers;
        playerSigilCount = new int[numPlayers+1];
        for (int i=0; i<=numPlayers; i++)
            playerSigilCount[i] = 0;
    }

    // Getters
    public Cell[][] getGrid() {
        return grid;
    }
    public Cell getCell(int x, int y) {
        return grid[x][y];
    }
    public int getCellNum() {
        return cellNum;
    }
    public int getNumPlayers() {
        return numPlayers;
    }
    public int[] getPlayerSigilCounts() {
        return playerSigilCount;
    }
    public int getPlayerSigilCount(int player) {
        return playerSigilCount[player];
    }

    // Draw a single cell
    public void drawCell(int player, int x, int y) {
        // Draw the square
        colorPicker(0);
        StdDraw.filledSquare(x+0.5, y+0.5, 0.49);

        // Pick the player color and sigil
        colorPicker(grid[x][y].getPlayer());
        String sigil = sigilPicker(grid[x][y].getPlayer());

        // Draw the sigils
        if (grid[x][y].getSigilCount() == 1) {
            StdDraw.picture(x+0.50, y+0.50, sigil, 0.5, 0.5);
        }
        else if (grid[x][y].getSigilCount() == 2) {
            StdDraw.picture(x+0.30, y+0.50, sigil, 0.5, 0.5);
            StdDraw.picture(x+0.70, y+0.50, sigil, 0.5, 0.5);
        }
        else if (grid[x][y].getSigilCount() == 3) {
            StdDraw.picture(x+0.30, y+0.30, sigil, 0.5, 0.5);
            StdDraw.picture(x+0.50, y+0.60, sigil, 0.5, 0.5);
            StdDraw.picture(x+0.70, y+0.30, sigil, 0.5, 0.5);
        }

        // Draw the lines for the next player
        // If the next player has no sigils, draw the lines for the player after that
        int nextPlayer = player % numPlayers + 1;
        while (playerSigilCount[nextPlayer] == 0) {
            nextPlayer = nextPlayer % numPlayers + 1;
        }
        intface.drawMap(this, nextPlayer);
        lines(nextPlayer);
    }

    // Draw the lines of the grid
    public void lines(int player) {
        if (player > numPlayers)
            player -= numPlayers;

        colorPicker(player);
        for (int i=0; i<=cellNum; i++) {
            StdDraw.line(0, i, cellNum, i);
            StdDraw.line(i, 0, i, cellNum);
        }
    }

    // Color picker
    public void colorPicker(int player) {
        if (player == -1)
            StdDraw.setPenColor(StdDraw.WHITE);
        else if (player == 0)
            StdDraw.setPenColor(StdDraw.BLACK);
        else if (player == 1)
            StdDraw.setPenColor(255,0,0); // Red
        else if (player == 2)
            StdDraw.setPenColor(128,128,128); // Grey
        else if (player == 3)
            StdDraw.setPenColor(218,165,32); // Golden
        else if (player == 4)
            StdDraw.setPenColor(255,255,0); // Yellow
        else if (player == 5)
            StdDraw.setPenColor(76,153,0); // Green
        else if (player == 6)
            StdDraw.setPenColor(0,102,204); // Blue
        else
            StdDraw.setPenColor(255,128,0); // Orange
    }

    // Sigil picker
    public String sigilPicker(int player) {
        String sigil;
        if (player == 1)
            sigil = "./images/Targaryen.png";
        else if (player == 2)
            sigil = "./images/Stark.png";
        else if (player == 3)
            sigil = "./images/Lannister.png";
        else if (player == 4)
            sigil = "./images/Baratheon.png";
        else if (player == 5)
            sigil = "./images/Tyrell.png";
        else if (player == 6)
            sigil = "./images/Arryn.png";
        else
            sigil = "./images/Greyjoy.png";
        return sigil;
    }

    // Add sigil to a cell under normal circumstances
    public boolean addSigil (int player, int x, int y) {
        // If the cell contains no sigil
        if (grid[x][y].getSigilCount() == 0) {
            // Project.out.println("Contains no sigil");

            playerSigilCount[player]++; // Increase the player's sigil count
            grid[x][y].setPlayer(player); // Set the cell to the player
            grid[x][y].setSigilCount(1); // Set the sigil count to 1

            StdAudio.playInBackground("./sounds/Sword_Slash.wav"); // Play the sound
            drawCell(player, x, y); // Draw the cell

            return true; // Adding the sigil is successful
        }

        // If the cell contains the player of the current turn
        if (grid[x][y].getPlayer() == player) {
            // Project.out.println("Contains the player");

            playerSigilCount[player]++; // Increase the player's sigil count

            if (isExplodable(x, y)) { // Explode if the cell is explodable
                StdAudio.playInBackground("./sounds/Sword_Block_Combo.wav"); // Play the sound
                explode(player, x, y); // Explode the cell
            }
            else { // Increment the sigil count otherwise
                StdAudio.playInBackground("./sounds/Sword_Slash.wav"); // Play the sound
                grid[x][y].setSigilCount( grid[x][y].getSigilCount() + 1 ); // Increment the sigil count
            }

            drawCell(player, x, y); // Draw the cell
            return true; // Adding the sigil is successful
        }

        // If the cell contains the opponent
        StdAudio.playInBackground("./sounds/Sword_Slash_With_Metal_Shield.wav"); // Play the sound
        // Project.out.println("Contains the opponent");
        return false; // Adding the sigil is unsuccessful
    }

    // Add sigil to a cell while exploding
    public void addExplodingSigil (int player, int x, int y) {
        // Project.out.println("Exploding sigil added");
        // Project.out.println("Player: " + player + " x: " + x + " y: " + y);

        // If the cell contains no sigil
        if (grid[x][y].getSigilCount() == 0) {
            grid[x][y].setPlayer(player); // Set the cell to the player
            grid[x][y].setSigilCount(1); // Set the sigil count to 1

            // StdAudio.playInBackground("./sounds/Sword_Impact.wav"); // Play the sound
            drawCell(player, x, y); // Draw the cell
        }

        // If the cell contains the player of the current turn
        else if (grid[x][y].getPlayer() == player) {
            if (isExplodable(x, y)) { // Explode if the cell is explodable
                StdAudio.playInBackground("./sounds/Sword_Block_Combo.wav"); // Play the sound
                explode(player, x, y); // Explode the cell
            }
            else { // Increment the sigil count otherwise
                // StdAudio.playInBackground("./sounds/Sword_Impact.wav"); // Play the sound
                grid[x][y].setSigilCount( grid[x][y].getSigilCount() + 1 ); // Increment the sigil count
            }

            drawCell(player, x, y); // Draw the cell
        }

        // If the cell contains the opponent
        else {
            // Decrease the opponent's sigil count and increase the current player's sigil count
            playerSigilCount[ grid[x][y].getPlayer() ] -= grid[x][y].getSigilCount();
            playerSigilCount[player] += grid[x][y].getSigilCount();
            // Set the cell to the current player
            grid[x][y].setPlayer(player);

            if (isExplodable(x, y)) {// Explode if the cell is explodable
                StdAudio.stopInBackground(); // Stop the current sound
                StdAudio.playInBackground("./sounds/Sword_Slash_With_Designed_Impact.wav"); // Play the sound
                StdAudio.playInBackground("./sounds/Sword_Block_Combo.wav"); // Play the sound
                explode(player, x, y); // Explode the cell
            }
            else {// Increment the sigil count otherwise
                StdAudio.stopInBackground(); // Stop the current sound
                StdAudio.playInBackground("./sounds/Sword_Slash_With_Designed_Impact.wav"); // Play the sound
                grid[x][y].setSigilCount( grid[x][y].getSigilCount() + 1 ); // Increment the sigil count
            }

            drawCell(player, x, y); // Draw the cell
        }
    }

    // Check if the cell is explodable
    public boolean isExplodable(int x, int y) {
        // If the cell is in the corner and contains 1 sigil, it is explodable
        if ( (x == 0 && y == 0) ||
             (x == 0 && y == cellNum-1) ||
             (x == cellNum-1 && y == 0) ||
             (x == cellNum-1 && y == cellNum-1) ) {
            if (grid[x][y].getSigilCount() == 1)
                return true;
            else
                return false;
        }

        // If the cell is on the edge and contains 2 sigils, it is explodable
        else if ( (x == 0) ||
                  (x == cellNum-1) ||
                  (y == 0) ||
                  (y == cellNum-1) ) {
            if (grid[x][y].getSigilCount() == 2)
                return true;
            else
                return false;
        }

        // If the cell is in the middle and contains 3 sigils, it is explodable
        else {
            if (grid[x][y].getSigilCount() == 3)
                return true;
            else
                return false;
        }
    }

    // Explode event
    public void explode(int player, int x, int y) {
        // Explode the sigil and empty the cell
        grid[x][y] = new Cell(0, 0);
        drawCell(player, x, y);

        // Explode the surrounding sigils
        if (x > 0)
            addExplodingSigil(player, x-1, y);
        if (x < cellNum-1)
            addExplodingSigil(player, x+1, y);
        if (y > 0)
            addExplodingSigil(player, x, y-1);
        if (y < cellNum-1)
            addExplodingSigil(player, x, y+1);
    }

    // Check if the game is over
    public boolean isGameOver() {
        int counter = 0;
        for (int i=1 ; i<=numPlayers ; i++) {
            if (playerSigilCount[i] == 0) {
                counter++;
            }
        }

        // If only one player has sigils left, the game is over
        if (counter == numPlayers-1)
            return true;
        else
            return false;
    }

    // Get the winner
    public int getWinner() {
        // Find the player with the most sigils
        int winner = 0;
        int colony = 0;
        for (int i=1 ; i<=numPlayers ; i++) {
            if (playerSigilCount[i] > colony) {
                colony = playerSigilCount[i];
                winner = i;
            }
        }
        intface.drawMap(this, winner); // Draw the map
        lines(winner); // Draw the lines

        return winner;
    }

    // Get the cell number of mouse click
    public int getMouseX() {
        return (int) Math.floor(StdDraw.mouseX());
    }
    public int getMouseY() {
        return (int) Math.floor(StdDraw.mouseY());
    }

    // Convert the mouse coordinates to cell coordinates
    public int[] getClickedCell() {
        int x = getMouseX();
        int y = getMouseY();
        return new int[]{x, y};
    }
}
