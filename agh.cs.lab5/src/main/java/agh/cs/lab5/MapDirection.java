package agh.cs.lab5;

import agh.cs.lab5.Vector2d;

public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    @Override
    public String toString() {
        switch(this) {
            case NORTH:
                return("Połnoc");
            case SOUTH:
                return("Południe");
            case EAST:
                return("Wschód");
            default:
                return("Zachód");
        }
    }

    public MapDirection next() {
        switch(this) {
            case NORTH:
                return(EAST);
            case EAST:
                return(SOUTH);
            case SOUTH:
                return(WEST);
            default:
                return(NORTH);
        }
    }

    public MapDirection previous() {
        switch(this) {
            case EAST:
                return(NORTH);
            case SOUTH:
                return(EAST);
            case WEST:
                return(SOUTH);
            default:
                return(WEST);
        }
    }

    public Vector2d toUnitVector() {
        switch(this) {
            case EAST:
                return(new Vector2d(1,0));
            case SOUTH:
                return(new Vector2d(0,-1));
            case WEST:
                return(new Vector2d(-1,0));
            default:
                return(new Vector2d(0,1));
        }
    }
}
