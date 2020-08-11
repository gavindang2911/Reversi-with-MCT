// A class for Move
package ca.reversi;

public class Move {
    // Variables discovered
    private int x;
    private int y;
    public int win,lose,draw;

    public Move(int x, int y){
        this.x = x;
        this.y = y;
        win=0;
        lose=0;
        draw=0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString(){
        return "["+x+", "+y+"]";
    }

    @Override
    public boolean equals(Object o){
        return o.hashCode()==this.hashCode();
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(x+""+y);
    }
}
