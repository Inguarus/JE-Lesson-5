package sample.javafx.shapes;

import sample.javafx.Board;
import sample.javafx.DisplayForm;

public class Triangle extends AbstractShape {

    public Triangle(DisplayForm form, Board board, int x, int y, int size) {
        super(form, board, x, y, size);
    }

    @Override
    public void draw() {
        form.drawTriangle(x, y, size, active);
    }

    @Override
    public Shape copy() {
        return new Triangle(form, board, x, y, size);
    }
}
