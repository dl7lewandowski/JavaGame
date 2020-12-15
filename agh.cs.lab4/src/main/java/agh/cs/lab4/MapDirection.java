package agh.cs.lab4;

public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    @Override
    public String toString() {
        switch (this){
            case NORTH: return "Północ";
            case SOUTH: return "Połódnie";
            case WEST: return "Zachód";
            case EAST: return "Wschód";
        }
        return null;
    }
    MapDirection next(){
        switch (this){
            case NORTH: return EAST;
            case EAST: return SOUTH;
            case SOUTH: return WEST;
            case WEST: return NORTH;
        }
        return null;
    }
    MapDirection previous(){
        switch (this){
            case NORTH: return WEST;
            case WEST: return SOUTH;
            case SOUTH: return EAST;
            case EAST: return NORTH;
        }
        return null;
    }
    Vector2d toUnitVector(){
        switch (this){
            case NORTH: return new Vector2d(0,1);
            case SOUTH: return new Vector2d(0,-1);
            case EAST: return new Vector2d(1, 0);
            case WEST: return new Vector2d(-1,0);
        }
        return null;
    }
}
