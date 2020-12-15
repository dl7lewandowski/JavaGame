package agh.cs.lab4;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;


public class OptionParserTest {

    @Test
    public void moveDirectionTest(){
        assertArrayEquals("list: right, forward, left, backward", new MoveDirection[] {MoveDirection.RIGHT,
        MoveDirection.FORWARD,MoveDirection.LEFT,MoveDirection.BACKWARD},OptionParser.parse( new String[] {"r","f","l","b"}));
    }

    @Test
    public void moveDirectionWithWrongInputTest(){
        assertArrayEquals("list: right, forward, left, backward", new MoveDirection[] {MoveDirection.RIGHT,
                MoveDirection.FORWARD,MoveDirection.LEFT,MoveDirection.BACKWARD},OptionParser.parse( new String[] {"r","sfsf","f","fsfx","l","rwe","b"}));
    }
}
