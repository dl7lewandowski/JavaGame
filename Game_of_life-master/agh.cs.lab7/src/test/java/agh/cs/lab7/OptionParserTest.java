package agh.cs.lab5;
import static org.junit.Assert.assertArrayEquals;

import agh.cs.lab5.MoveDirection;
import org.junit.Test;

public class OptionParserTest {
    @Test
    public void moveDirectionTest(){
        assertArrayEquals("list: right, forward, left, backward", new MoveDirection[] {MoveDirection.RIGHT,
                MoveDirection.FORWARD,MoveDirection.LEFT,MoveDirection.BACKWARD},OptionsParser.parse( new String[] {"r","f","l","b"}));
    }


}
