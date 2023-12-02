package mogbos.brix.utils.level;

public class Block {
    int row;
    int column;
    int stamina;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getX() {
        return 10 + ((column-1) * 75);
    }

    public int getY() {
        return 720 - 495 + ((10-row) * 45);
    }

    @Override
    public String toString() {
        return "Block [Row: " + row + ", Column: " + column + ", Stamina: " + stamina + "]";
    }
}
