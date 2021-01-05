package agh.cs.lab8;

import java.util.Objects;

public class Vector2d {

    private final int x;
    private final int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2d vector2d = (Vector2d) o;
        return x == vector2d.x &&
                y == vector2d.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


   public Vector2d add(Vector2d other){
        int x = getX() + other.getX();
        int y = getY() + other.getY();
        return new Vector2d(x,y);
    }

   public Vector2d subtract(Vector2d other){
        int x = getX() - other.getX();
        int y = getY() - other.getY();
        return new Vector2d(x,y);
    }

   public Vector2d opposite(){
        int x = getX()*(-1);
        int y = getY()*(-1);
        return new Vector2d(x,y);
    }
}
