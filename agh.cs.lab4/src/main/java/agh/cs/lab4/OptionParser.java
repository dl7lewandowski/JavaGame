package agh.cs.lab4;

import java.util.ArrayList;

import java.util.List;

public class OptionParser {

    public static MoveDirection[] parse(String[] args) {
        List<MoveDirection> moves = new ArrayList<>();
        for (String argument : args) {
            MoveDirection move = parse(argument);
            if (move != null) {
                moves.add(move);
            }
        }
        return(moves.toArray(new MoveDirection[0]));
    }
    private static MoveDirection parse(String argument) {
        switch(argument) {
            case "f": case "forward" :
                return(MoveDirection.FORWARD);
            case "b": case "backward" :
                return(MoveDirection.BACKWARD);
            case "r": case "right" :
                return(MoveDirection.RIGHT);
            case "l": case "left" :
                return(MoveDirection.LEFT);
            default:
                return(null);
        }
    }
}
