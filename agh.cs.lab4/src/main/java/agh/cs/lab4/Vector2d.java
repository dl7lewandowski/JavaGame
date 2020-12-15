package agh.cs.lab4;

import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x +"," + y + ")";
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

    boolean precedes(Vector2d other){
        if((this.x <= other.x) && (this.y <= other.y))
            return true;
        return false;
    }
    boolean follows(Vector2d other){
        if ((this.x >= other.x)&& (this.y >= other.y) ) return true;
        return false;
    }
    Vector2d upperRight(Vector2d other){
        int x = Math.max(this.x,other.x);
        int y = Math.max(this.y, other.y);
        return new Vector2d(x,y);
    }
    Vector2d lowerLeft(Vector2d other){
        int x = Math.min(this.x,other.x);
        int y = Math.min(this.y,other.y);
        return new Vector2d(x,y);
    }
    Vector2d add(Vector2d other){
        int x = getX() + other.getX();
        int y = getY() + other.getY();
        return new Vector2d(x,y);
    }
    Vector2d subtract(Vector2d other){
        int x = getX() - other.getX();
        int y = getY() - other.getY();
        return new Vector2d(x,y);
    }
    Vector2d opposite(){
        int x = getX()*(-1);
        int y = getY()*(-1);
        return new Vector2d(x,y);
    }
}
