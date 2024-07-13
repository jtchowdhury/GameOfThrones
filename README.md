# Welcome to Game of Thrones
***(Inspired from the game "Chain Reaction" and the popular fiction "Game of Thrones")***

Caution! *"When you play a game of thrones, you win or you die."* - George R.R. Martin 

(Spoiler) It can be as unpredictable as the actual fiction if you know how to play it.
The board is the representation of Westeros and each player represents a prominent house.
Your goal is to establish territories and expand them (by clicking in the cells) because according
to the author, *"The lone wolf dies but the pack survives."*


## Rules
- 2 to 7 players can play this game -- each will represent a house and go by turns.
- You can stack up to 3 flags in each cell except for the edge and corner cells, where you
    can stack up to 2 and 1 flag(s) respectively.
- You can't put your flag in other players' cells.
- After stacking 3 flags in a cell (2 and 1 for the edge and corner cells respectively) when you click
    there again, the cell explodes and take over the surrounding cells.
That's how you expand your territory. If the surrounding cells had others' flas,
    they'll become your territory now.
- If a certain house has no more territory remaining on the board, they're eliminated.
- The last player to survive the game wins the "Iron Throne" after all the other houses go extinct.
  
## Enjoy!

# How to play
 1. Make sure you have Java installed on your system.
    For windows, download and install Java from [here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
    For Ubuntu, run the following command in the terminal:
    ```
    sudo apt-get install default-jre
    ```
    For Arch:
    ```
    sudo pacman -S jre-openjdk
    ```
    For Fedora:
    ```
    sudo dnf install java-11-openjdk
    ```
    For MacOS
    ```
    brew install openjdk@11
    ```

 2. Clone the repository by running the following command in the terminal:
    ```
    git clone https://github.com/Turja-Roy/CR-GOT.git
    ```

 3. To run the game, run the following command in the terminal:
    ```
    java bin/Project
    ```

- To compile from source code, run the following command in the terminal:
    ```
    javac -d bin Project.java
    ```
    In that case, make sure you have jdk installed besides jre.
