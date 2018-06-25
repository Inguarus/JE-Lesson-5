package sample.javafx.shapes;

public interface Shape {

    public static enum ShapeType {CIRCLE, RECTANGLE, TRIANGLE, GROUP}

    void draw();

    void setActive(boolean b);

    void decrease();

    void increase();
}
