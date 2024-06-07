public class Cell {
    private int player;
    private int sigilCount;

    // Constructor
    Cell (int player, int sigilCount) {
        this.player = player;
        this.sigilCount = sigilCount;
    }

    // Getters
    public int getPlayer() {
        return player;
    }
    public int getSigilCount() {
        return sigilCount;
    }

    // Setters
    public void setPlayer(int player) {
        this.player = player;
    }
    public void setSigilCount(int sigilCount) {
        this.sigilCount = sigilCount;
    }
}
