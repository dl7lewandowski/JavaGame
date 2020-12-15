package agh.cs.lab4;

import org.junit.Assert;
import org.junit.Test;

public class MapDirectionTest {
    @Test
    public void next(){
        Assert.assertEquals(MapDirection.SOUTH,MapDirection.EAST.next());
        Assert.assertEquals(MapDirection.WEST,MapDirection.SOUTH.next());
        Assert.assertEquals(MapDirection.EAST,MapDirection.NORTH.next());
        Assert.assertEquals(MapDirection.NORTH,MapDirection.WEST.next());
    }
    @Test
    public void previous(){
        Assert.assertEquals(MapDirection.SOUTH,MapDirection.EAST.next());
        Assert.assertEquals(MapDirection.WEST,MapDirection.SOUTH.next());
        Assert.assertEquals(MapDirection.NORTH,MapDirection.WEST.next());
        Assert.assertEquals(MapDirection.EAST,MapDirection.NORTH.next());

    }

}