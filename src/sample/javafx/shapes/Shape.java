package sample.javafx.shapes;

import sample.javafx.Board;

public interface Shape {

    enum ShapeType {CIRCLE, RECTANGLE, TRIANGLE, GROUP}

    void draw();

    void setActive(boolean active);

    void decrease();

    void increase();

    Shape copy();

    boolean consistPoint(int sceneX, int sceneY);

    void move(Board.Direction direction);
}
