package agh.cs.lab8;

public interface IWorldChangePosition {
    void positionChanged(Vector2d startVector2d, Vector2d newVector2d, Animal a);
    void dead(Animal a);
}
