package agh.cs.lab8;

public class Grass implements IWorldMapElement {
    protected Vector2d vector2d;
    protected int animalEnergy;

    public Grass(Vector2d vector2d, int animalEnergy) {
        this.vector2d = vector2d;
        this.animalEnergy = animalEnergy;
    }


    @Override
    public String toString() {
        return "*";
    }


    @Override
    public Vector2d getPosition() {
        return vector2d;
    }

}
