package sample.javafx;

import sample.javafx.shapes.*;

public class ShapeFactory {

    public static Shape createShape(Shape.ShapeType type, DisplayForm form, Board board, int x, int y) {
        return createShape(type, form, board, x, y, Signification.DEFAULT_SIZE);
    }

    public static Shape createShape(Shape.ShapeType type, DisplayForm form, Board board, int x, int y, int size) {
        switch (type) {
            case CIRCLE:
                return new Circle(form, board, x, y, size);
            case RECTANGLE:
                return new Rectangle(form, board, x, y, size);
            case TRIANGLE:
                return new Triangle(form, board, x, y, size);
            case GROUP:
                return new Group();
            default:
                return null;
        }
    }
}
