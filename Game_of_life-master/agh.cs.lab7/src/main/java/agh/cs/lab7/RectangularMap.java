package agh.cs.lab5;

import agh.cs.lab5.Vector2d;

public class RectangularMap extends AbstractWorldMap {

    public RectangularMap(int width, int height) {
        super(width, height);
    }

    @Override
    public String toString() {
        MapVisualizer mv = new MapVisualizer(this);
        return (mv.draw(new Vector2d(0, 0), new Vector2d(this.width - 1, this.height - 1)));
    }
}
