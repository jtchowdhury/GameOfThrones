import java.awt.*;

public class Interfaces {
    public int cellNum = 10;
    public static int numPlayers;
    public double canSize = cellNum + (10.0 * 7.0/9.0);

    // Constructor
    public Interfaces() {
        cellNum = 10;
        double canSize = cellNum + (10.0 * 7.0/9.0);
    }

    // Intro page
    public void intro(){
        // Set the canvas, scale, background music, and background image
        StdDraw.setCanvasSize(1280, 720);
        StdDraw.setScale(0, cellNum);
        StdAudio.playInBackground("./sounds/the-epic-trailer.wav");
        StdDraw.picture(cellNum/2, cellNum/2, "./images/intropage.jpg");

        // Adding Play, Rules, and Quit buttons
        StdDraw.setPenColor(204,204,0);
        StdDraw.picture(cellNum/2 - 2, cellNum/2 - 1.7, "./images/button.png", 1.5, 1.5);
        StdDraw.picture(cellNum/2, cellNum/2 - 1.7, "./images/button.png", 1.5, 1.5);
        StdDraw.picture(cellNum/2 + 2, cellNum/2 - 1.7, "./images/button.png", 1.5, 1.5);

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setPenRadius(1.0);
        Font font = new Font("Book Antiqua", Font.BOLD, 40);
        StdDraw.setFont(font);

        StdDraw.textLeft(cellNum/2 - 2.35, cellNum/2 - 1.5, "Play");
        StdDraw.textLeft(cellNum/2 - 0.35, cellNum/2 - 1.5, "Rules");
        StdDraw.textLeft(cellNum/2 + 1.60, cellNum/2 - 1.5, "Quit");
        StdDraw.show();
        StdDraw.pause(100);

        // Wait for the user to click on Play, Rules, or Quit
        int playClicked = 0;
        boolean rulesClicked = false;
        while (true) {
            if (StdDraw.isMousePressed()) {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                // Play button clicked
                if (x > cellNum/2 - 2.75 && x < cellNum/2 -1.25 && y > cellNum/2 - 2.45 && y < cellNum/2 -0.95) {
                    StdAudio.playInBackground("./sounds/Unsheathing2.wav");
                    StdDraw.pause(1000);
                    playClicked = playButtonClicked(cellNum);
                    break;
                }
                // Rules button clicked
                else if (x > cellNum/2 - 0.75 && x < cellNum/2 + 0.75 && y > cellNum/2 - 2.45 && y < cellNum/2 -0.95) {
                    StdAudio.playInBackground("./sounds/paper-rustle.wav");
                    StdDraw.pause(1000);
                    rulesClicked = rules();
                    break;
                }
                // Quit button clicked
                else if (x > cellNum/2 + 1.25 && x < cellNum/2 + 2.75 && y > cellNum/2 - 2.45 && y < cellNum/2 -0.95) {
                    System.exit(0);
                }
            }
        }

        // Set up the board if the play button was clicked
        if (playClicked >= 2 && playClicked <= 7) {
            StdAudio.playInBackground("./sounds/Unsheathing.wav");
            StdDraw.pause(1000);
            board();
        }
        // Go back to the intro page if the back button was clicked from the rules page
        else if (playClicked == 10) {
            StdAudio.playInBackground("./sounds/Unsheathing.wav");
            StdDraw.pause(1000);
            intro();
        }
        // Quit the game if the quit button was clicked
        else if (playClicked == 20) {
            System.exit(0);
        }
        // Display the rules if the rules button was clicked
        else if (rulesClicked) {
            StdAudio.playInBackground("./sounds/Unsheathing.wav");
            StdDraw.pause(1000);
            intro();
        }
    }

    // Rules page
    public boolean rules(){
        // Set the Parchment Scroll
        StdDraw.picture(cellNum/2, cellNum/2, "./images/Parchment_Scroll.png", cellNum/1.8, cellNum*1.8, 90);

        // Pen settings
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(1.0);

        // Set the fonts
        Font font = new Font("Book Antiqua", Font.BOLD, 40);
        Font font1 = new Font("Book Antiqua", Font.BOLD, 22);
        Font font2 = new Font("Book Antiqua", Font.BOLD, 30);
        Font font3 = new Font("Book Antiqua", Font.PLAIN, 20);

        // Display the rules
        StdDraw.setFont(font);
        StdDraw.text(cellNum/2, cellNum-1.5, "Welcome to Game of Thrones");
        StdDraw.setFont(font1);
        StdDraw.text(cellNum/2, cellNum-2, "(Inspired from the game \"Chain Reaction\" and the popular fiction \"Game of Thrones\".)");
        StdDraw.setFont(font3);
        StdDraw.textLeft(1.5, cellNum-2.6, "Caution! “When you play a game of thrones you win or you die.” - George R.R. Martin. (Spoiler)");
        StdDraw.textLeft(1.5, cellNum-2.9, "It can be as unpredictable as the actual fiction if you know how to play it.");
        StdDraw.textLeft(1.5, cellNum-3.4, "The board is the representation of Westeros and each player represents a prominent house.");
        StdDraw.textLeft(1.5, cellNum-3.7, "Your goal is to establish territories and expand them (by clicking in the cells) because according ");
        StdDraw.textLeft(1.5, cellNum-4.0, "to the author, \"The lone wolf dies but the pack survives.\"");
        StdDraw.setFont(font2);
        StdDraw.text(cellNum/2, cellNum-4.5, "Rules:");
        StdDraw.setFont(font3);
        StdDraw.textLeft(1.5, cellNum-5.0, "* 2 to 7 players can play this game - each will represent a house and go by turns.");
        StdDraw.textLeft(1.5, cellNum-5.3, "* You can stack up to 3 flags in each cell except for the edge and corner cells,");
        StdDraw.textLeft(1.5, cellNum-5.6, "    where you can stack up to 2 and 1 flag(s) respectively.");
        StdDraw.textLeft(1.5, cellNum-5.9, "* You can’t put your flag in others’ cell.");
        StdDraw.textLeft(1.5, cellNum-6.2, "* After stacking 3 flags in a cell (2 and 1 for the edge and corner cells) when you click there again,");
        StdDraw.textLeft(1.5, cellNum-6.5, "    the cell explodes and take over the surrounding cells.");
        StdDraw.textLeft(1.5, cellNum-6.8, "That’s how you expand your territory. If the surrounding cells had others’ flags,");
        StdDraw.textLeft(1.5, cellNum-7.1, "    they’ll become your territory now.");
        StdDraw.textLeft(1.5, cellNum-7.4, "* If a certain house has no more territory remaining on the board, they’re dead.");
        StdDraw.textLeft(1.5, cellNum-7.7, "* The last player to survive the game wins the \"Iron Throne\" after all other houses are extinct.");
        StdDraw.setFont(font2);
        StdDraw.text(cellNum/2, cellNum-8.3, "Enjoy!");

        // Draw the Back button
        StdDraw.picture(cellNum/2, 0.45, "./images/button.png", 1.5, 1.5);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(cellNum/2, 0.65, "Back");

        StdDraw.show();

        // Wait for the user to click on Back
        while (true) {
            if (StdDraw.isMousePressed()) {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                if (x > cellNum/2-0.75 && x < cellNum/2+0.75 && y > -0.3 && y < 1.2) {
                    StdAudio.playInBackground("./sounds/paper-rustle.wav");
                    StdDraw.pause(1000);
                    return true;
                }
            }
        }
    }

    // Mouse clicked on play button
    public int playButtonClicked(int cellNum){
        // Remove the play and rules buttons and place the player selection buttons
        StdDraw.clear();
        // StdAudio.stopInBackground();
        StdAudio.playInBackground("./sounds/cinematic-impact-climax.wav");
        StdDraw.picture(cellNum/2, cellNum/2, "./images/intropage.jpg");

        // Draw the buttons for selecting the number of players. 2, 3, 4, 5, 6, 7
        StdDraw.setPenColor(204,204,0);
        StdDraw.picture(cellNum/2 - 3, cellNum/2 + 2.5 - 0.2, "./images/button.png", 1.8, 1.5);
        StdDraw.picture(cellNum/2 + 3, cellNum/2 + 2.5 - 0.2, "./images/button.png", 1.8, 1.5);
        StdDraw.picture(cellNum/2 - 3, cellNum/2 + 0.5 - 0.2, "./images/button.png", 1.8, 1.5);
        StdDraw.picture(cellNum/2 + 3, cellNum/2 + 0.5 - 0.2, "./images/button.png", 1.8, 1.5);
        StdDraw.picture(cellNum/2 - 3, cellNum/2 - 1.5 - 0.2, "./images/button.png", 1.8, 1.5);
        StdDraw.picture(cellNum/2 + 3, cellNum/2 - 1.5 - 0.2, "./images/button.png", 1.8, 1.5);
        StdDraw.picture(cellNum/2 - 3, cellNum/2 - 3.5 - 0.2, "./images/button.png", 1.8, 1.5);
        StdDraw.picture(cellNum/2 + 3, cellNum/2 - 3.5 - 0.2, "./images/button.png", 1.8, 1.5);

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setPenRadius(1.0);
        Font font = new Font("Book Antiqua", Font.BOLD, 40);
        StdDraw.setFont(font);

        StdDraw.text(cellNum/2 - 3, cellNum/2 + 2.5, "2 Players");
        StdDraw.text(cellNum/2 + 3, cellNum/2 + 2.5, "3 Players");
        StdDraw.text(cellNum/2 - 3, cellNum/2 + 0.5, "4 Players");
        StdDraw.text(cellNum/2 + 3, cellNum/2 + 0.5, "5 Players");
        StdDraw.text(cellNum/2 - 3, cellNum/2 - 1.5, "6 Players");
        StdDraw.text(cellNum/2 + 3, cellNum/2 - 1.5, "7 Players");
        StdDraw.text(cellNum/2 - 3, cellNum/2 - 3.5, "Back");
        StdDraw.text(cellNum/2 + 3, cellNum/2 - 3.5, "Quit");

        // Introduce Houses in the middle of the screen
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledRectangle(cellNum/2, cellNum-1-0.25, 2.0, 0.5);
        StdDraw.setPenColor(255,0,0); // Red
        StdDraw.filledRectangle(cellNum/2, cellNum-2-0.50, 1.5, 0.45);
        StdDraw.picture(cellNum/2, cellNum-2-0.50, "./images/Targaryen2.png", 0.4, 0.75);
        StdDraw.setPenColor(128,128,128); // Grey
        StdDraw.filledRectangle(cellNum/2, cellNum-3-0.50, 1.5, 0.45);
        StdDraw.picture(cellNum/2, cellNum-3-0.50, "./images/Stark2.png", 0.4, 0.75);
        StdDraw.setPenColor(218,165,32); // Golden
        StdDraw.filledRectangle(cellNum/2, cellNum-4-0.50, 1.5, 0.45);
        StdDraw.picture(cellNum/2, cellNum-4-0.50, "./images/Lannister2.png", 0.4, 0.75);
        StdDraw.setPenColor(255,255,0); // Yellow
        StdDraw.filledRectangle(cellNum/2, cellNum-5-0.50, 1.5, 0.45);
        StdDraw.picture(cellNum/2, cellNum-5-0.50, "./images/Baratheon2.png", 0.4, 0.75);
        StdDraw.setPenColor(76,153,0); // Green
        StdDraw.filledRectangle(cellNum/2, cellNum-6-0.50, 1.5, 0.45);
        StdDraw.picture(cellNum/2, cellNum-6-0.50, "./images/Tyrell2.png", 0.4, 0.75);
        StdDraw.setPenColor(0,102,204); // Blue
        StdDraw.filledRectangle(cellNum/2, cellNum-7-0.50, 1.5, 0.45);
        StdDraw.picture(cellNum/2, cellNum-7-0.50, "./images/Arryn2.png", 0.4, 0.75);
        StdDraw.setPenColor(255,128,0); // Orange
        StdDraw.filledRectangle(cellNum/2, cellNum-8-0.50, 1.5, 0.45);
        StdDraw.picture(cellNum/2, cellNum-8-0.50, "./images/Greyjoy2.png", 0.4, 0.75);

        StdDraw.text(cellNum/2, cellNum-1-0.25, "Houses of Westeros");
        Font font2 = new Font("Book Antiqua", Font.BOLD, 25);
        StdDraw.setFont(font2);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(cellNum/2, cellNum-2-0.50, "Player 1: House Targaryen");
        StdDraw.text(cellNum/2, cellNum-3-0.50, "Player 2: House Stark");
        StdDraw.text(cellNum/2, cellNum-4-0.50, "Player 3: House Lannister");
        StdDraw.text(cellNum/2, cellNum-5-0.50, "Player 4: House Baratheon");
        StdDraw.text(cellNum/2, cellNum-6-0.50, "Player 5: House Tyrell");
        StdDraw.text(cellNum/2, cellNum-7-0.50, "Player 6: House Arryn");
        StdDraw.text(cellNum/2, cellNum-8-0.50, "Player 7: House Greyjoy");

        // Wait for the user to click on the number of players
        StdDraw.show();
        StdDraw.pause(100);
        while (true) {
            if (StdDraw.isMousePressed()) {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                if (x > cellNum/2-3-0.9 && x < cellNum/2-3+0.9 && y > cellNum/2+2.3-0.75 && y < cellNum/2+2.3+0.75) {
                    numPlayers = 2;
                    return 2;
                }
                else if (x > cellNum/2+3-0.9 && x < cellNum/2+3+0.9 && y > cellNum/2+2.3-0.75 && y < cellNum/2+2.3+0.75) {
                    numPlayers = 3;
                    return 3;
                }
                else if (x > cellNum/2-3-0.9 && x < cellNum/2-3+0.9 && y > cellNum/2+0.3-0.75 && y < cellNum/2+0.3+0.75) {
                    numPlayers = 4;
                    return 4;
                }
                else if (x > cellNum/2+3-0.9 && x < cellNum/2+3+0.9 && y > cellNum/2+0.3-0.75 && y < cellNum/2+0.3+0.75) {
                    numPlayers = 5;
                    return 5;
                }
                else if (x > cellNum/2-3-0.9 && x < cellNum/2-3+0.9 && y > cellNum/2-1.7-0.75 && y < cellNum/2-1.7+0.75) {
                    numPlayers = 6;
                    return 6;
                }
                else if (x > cellNum/2+3-0.9 && x < cellNum/2+3+0.9 && y > cellNum/2-1.7-0.75 && y < cellNum/2-1.7+0.75) {
                    numPlayers = 7;
                    return 7;
                }
                else if (x > cellNum/2-3-0.9 && x < cellNum/2-3+0.9 && y > cellNum/2-3.7-0.75 && y < cellNum/2-3.7+0.75) {
                    return 10;
                }
                else if (x > cellNum/2+3-0.9 && x < cellNum/2+3+0.9 && y > cellNum/2-3.7-0.75 && y < cellNum/2-3.7+0.75) {
                    return 20;
                }
            }
        }
    }

    // Set up the board
    public void board () {
        // StdDraw.clear();
        StdAudio.stopInBackground();
        StdAudio.playInBackground("./sounds/paper-rustle.wav");
        StdDraw.setCanvasSize(1280, 720);
        StdDraw.setXscale(0, canSize);
        StdDraw.setYscale(0, cellNum);

        // Place the board
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledSquare(cellNum/2, cellNum/2, cellNum/2);

        StdDraw.show();
        StdDraw.pause(100);

        // Place the map of Westeros and the sigils
        drawMap(1);
    }

    // Draw map and sigils
    public void drawMap(Grid grid, int player) {
        if (player > numPlayers)
            player -= numPlayers;

        // Place the map of Westeros
        double picCenter = cellNum + ((canSize - cellNum) / 2);
        StdDraw.picture(picCenter, cellNum/2, "./images/map.jpg", picCenter-cellNum/2-1.1, cellNum);

        // Draw the sigils on the map of Westeros
        if (numPlayers > 1) {
            StdDraw.picture(14.5, 3.25, "./images/Targaryen2.png", 0.65, 0.75);
            if (grid.getPlayerSigilCount(1) == 0 && Project.round > numPlayers)
                StdDraw.picture(14.5, 3.25, "./images/Blood.png", 1.0, 1.0);
            StdDraw.picture(13.5, 7.5, "./images/Stark2.png", 0.65, 0.75);
            if (grid.getPlayerSigilCount(2) == 0 && Project.round > numPlayers)
                StdDraw.picture(13.5, 7.5, "./images/Blood.png", 1.0, 1.0);
        }
        if (numPlayers > 2) {
            StdDraw.picture(11.5, 3.5, "./images/Lannister2.png", 0.65, 0.75);
            if (grid.getPlayerSigilCount(3) == 0 && Project.round > numPlayers)
                StdDraw.picture(11.5, 3.5, "./images/Blood.png", 1.0, 1.0);
        }
        if (numPlayers > 3) {
            StdDraw.picture(15.5, 3.5, "./images/Baratheon2.png", 0.65, 0.75);
            if (grid.getPlayerSigilCount(4) == 0 && Project.round > numPlayers)
                StdDraw.picture(15.5, 3.5, "./images/Blood.png", 1.0, 1.0);
        }
        if (numPlayers > 4) {
            StdDraw.picture(12, 1.75, "./images/Tyrell2.png", 0.65, 0.75);
            if (grid.getPlayerSigilCount(5) == 0 && Project.round > numPlayers)
                StdDraw.picture(12, 1.75, "./images/Blood.png", 1.0, 1.0);
        }
        if (numPlayers > 5) {
            StdDraw.picture(15.25, 5, "./images/Arryn2.png", 0.65, 0.75);
            if (grid.getPlayerSigilCount(6) == 0 && Project.round > numPlayers)
                StdDraw.picture(15.25, 5, "./images/Blood.png", 1.0, 1.0);
        }
        if (numPlayers > 6) {
            StdDraw.picture(11.75, 4.75, "./images/Greyjoy2.png", 0.65, 0.75);
            if (grid.getPlayerSigilCount(7) == 0 && Project.round > numPlayers)
                StdDraw.picture(11.75, 4.75, "./images/Blood.png", 1.0, 1.0);
        }

        // Draw swords on the player whose turn it is
        if (player == 1)
            StdDraw.picture(14.5, 3.25, "./images/sword.png", 0.5, 1.5);
        else if (player == 2)
            StdDraw.picture(13.5, 7.5, "./images/sword.png", 0.5, 1.5);
        else if (player == 3)
            StdDraw.picture(11.5, 3.5, "./images/sword.png", 0.5, 1.5);
        else if (player == 4)
            StdDraw.picture(15.5, 3.5, "./images/sword.png", 0.5, 1.5);
        else if (player == 5)
            StdDraw.picture(12, 1.75, "./images/sword.png", 0.5, 1.5);
        else if (player == 6)
            StdDraw.picture(15.25, 5, "./images/sword.png", 0.5, 1.5);
        else if (player == 7)
            StdDraw.picture(11.75, 4.75, "./images/sword.png", 0.5, 1.5);

        StdDraw.show();
        StdDraw.pause(100);
    }

    public void drawMap(int player) {
        // Place the map of Westeros
        double picCenter = cellNum + ((canSize - cellNum) / 2);
        StdDraw.picture(picCenter, cellNum/2, "./images/map.jpg", picCenter-cellNum/2-1.1, cellNum);

        // Draw the sigils on the map of Westeros
        if (numPlayers > 1) {
            StdDraw.picture(14.5, 3.25, "./images/Targaryen2.png", 0.65, 0.75);
            StdDraw.picture(13.5, 7.5, "./images/Stark2.png", 0.65, 0.75);
        }
        if (numPlayers > 2) StdDraw.picture(11.5, 3.5, "./images/Lannister2.png", 0.65, 0.75);
        if (numPlayers > 3) StdDraw.picture(15.5, 3.5, "./images/Baratheon2.png", 0.65, 0.75);
        if (numPlayers > 4) StdDraw.picture(12, 1.75, "./images/Tyrell2.png", 0.65, 0.75);
        if (numPlayers > 5) StdDraw.picture(15.25, 5, "./images/Arryn2.png", 0.65, 0.75);
        if (numPlayers > 6) StdDraw.picture(11.75, 4.75, "./images/Greyjoy2.png", 0.65, 0.75);

        // Draw swords on the player whose turn it is
        if (player == 1) StdDraw.picture(14.5, 3.25, "./images/sword.png", 0.5, 1.5);
        else if (player == 2) StdDraw.picture(13.5, 7.5, "./images/sword.png", 0.5, 1.5);
        else if (player == 3) StdDraw.picture(11.5, 3.5, "./images/sword.png", 0.5, 1.5);
        else if (player == 4) StdDraw.picture(15.5, 3.5, "./images/sword.png", 0.5, 1.5);
        else if (player == 5) StdDraw.picture(12, 1.75, "./images/sword.png", 0.5, 1.5);
        else if (player == 6) StdDraw.picture(15.25, 5, "./images/sword.png", 0.5, 1.5);
        else if (player == 7) StdDraw.picture(11.75, 4.75, "./images/sword.png", 0.5, 1.5);

        StdDraw.show();
    }

    // Winner page
    public boolean winnerPage(Grid grid, int winner) {
        String house;
        if (winner == 1) house = "Targaryen";
        else if (winner == 2) house = "Stark";
        else if (winner == 3) house = "Lannister";
        else if (winner == 4) house = "Baratheon";
        else if (winner == 5) house = "Tyrell";
        else if (winner == 6) house = "Arryn";
        else house = "Greyjoy";

        StdDraw.clear();
        StdDraw.setCanvasSize(1280, 800);
        StdDraw.setXscale(0, 1280);
        StdDraw.setYscale(0, 800);
        StdDraw.picture(1280/2, 800/2, "./images/winnerpage.jpg");
        StdAudio.playInBackground("./sounds/ending.wav");

        grid.colorPicker(winner);
        StdDraw.filledRectangle(1280/2, 800/3, 1280/3, 800/20);
        StdDraw.setPenRadius(1.0);
        StdDraw.setPenColor(StdDraw.WHITE);
        Font font = new Font("Book Antiqua", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(1280/2, 800/3, "House " + house + " (Player " + winner + ") takes the Iron Throne!");
        StdDraw.show();

        // Draw the buttons for Play Again and Quit
        StdDraw.setPenColor(204,204,0);
        StdDraw.picture(1280/2 - 1280*2/10, 800/4.5 - 15, "./images/button.png", 1280*1.5/10, 1280*0.75/10);
        StdDraw.picture(1280/2 + 1280*2/10, 800/4.5 - 15, "./images/button.png", 1280*1.5/10, 1280*0.75/10);

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setPenRadius(1.0);
        StdDraw.text(1280/2 - 1280*2/10, 800/4.5, "Play Again");
        StdDraw.text(1280/2 + 1280*2/10, 800/4.5, "Quit");

        StdDraw.show();

        // Wait for the user to click on Play Again or Quit
        while (true) {
            if (StdDraw.isMousePressed()) {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                if (x > 1280/2 - 1280*2/10 - 1280*0.75/20 && x < 1280/2 - 1280*2/10 + 1280*0.75/20 && y > 800/4.5 - 15 - 1280*0.75/20 && y < 800/4.5 - 15 + 1280*0.75/20) {
                    return true;
                }
                else if (x > 1280/2 + 1280*2/10 - 1280*0.75/20 && x < 1280/2 + 1280*2/10 + 1280*0.75/20 && y > 800/4.5 - 15 - 1280*0.75/20 && y < 800/4.5 - 15 + 1280*0.75/20) {
                    return false;
                }
            }
        }
    }
}
