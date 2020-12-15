package agh.cs.lab4;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class Vector2dTest {
    private Vector2d v0 = new Vector2d(1,0);
    private Vector2d v1 = new Vector2d(2,3);
    private Vector2d v2 = new Vector2d(3,4);
    private Vector2d v3 = new Vector2d(2,-1);
    private Vector2d v4 = new Vector2d(2,-1);


    @Test
    public void equalsTests() {
        assertFalse("not equal", v0.equals(v1));
        assertFalse("not equal", v1.equals(v2));
        assertTrue("equal", v3.equals(v4));
    }

    @Test
    public void toStringTests() {
        assertEquals("", "(1,0)", v0.toString());
        assertEquals("", "(2,3)", v1.toString());
        assertEquals("", "(2,-1)", v3.toString());
        assertEquals("", "(2,-1)", v4.toString());
    }

    @Test
    public void precedesTests() {
        assertTrue("", v0.precedes(v1));
        assertTrue("", v3.precedes(v2));
        assertFalse("", v2.precedes(v1));
        assertFalse("", v1.precedes(v0));
    }

    @Test
    public void followsTests() {
        assertTrue("", v2.follows(v0));
        assertTrue("", v1.follows(v4));
        assertTrue("", v1.follows(v3));
        assertFalse("", v0.follows(v1));
    }

    @Test
    public void upperRightTests() {
        assertEquals("", new Vector2d(2,3), v0.upperRight(v1));
        assertEquals("", new Vector2d(3,4), v1.upperRight(v2));
        assertEquals("", new Vector2d(3,4), v3.upperRight(v2));
        assertEquals("", new Vector2d(3,4), v3.upperRight(v2));
    }

    @Test
    public void lowerLeftTests() {
        assertEquals("", new Vector2d(1,0), v0.lowerLeft(v1));
        assertEquals("", new Vector2d(2,3), v1.lowerLeft(v2));
        assertEquals("", new Vector2d(2,-1), v3.lowerLeft(v2));
        assertEquals("", new Vector2d(2,-1), v3.lowerLeft(v2));
    }

    @Test
    public void addTests() {
        assertEquals("", new Vector2d(3,3), v0.add(v1));
        assertEquals("", new Vector2d(5,7), v1.add(v2));
        assertEquals("", new Vector2d(5,3), v3.add(v2));
        assertEquals("", new Vector2d(5,3), v2.add(v3));
    }

    @Test
    public void subtractTests() {
        assertEquals("", new Vector2d(-1,-3), v0.subtract(v1));
        assertEquals("", new Vector2d(-1,-1), v1.subtract(v2));
        assertEquals("", new Vector2d(-1,-5), v3.subtract(v2));
        assertEquals("", new Vector2d(1,-1), v4.subtract(v0));
    }

    @Test
    public void oppositeTests() {
        assertEquals("", new Vector2d(-1,0), v0.opposite());
        assertEquals("", new Vector2d(-2,-3), v1.opposite());
        assertEquals("", new Vector2d(-2,1), v3.opposite());
        assertEquals("", new Vector2d(-2,1), v4.opposite());
    }
}