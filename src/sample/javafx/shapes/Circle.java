package sample.javafx.shapes;

import sample.javafx.Board;
import sample.javafx.DisplayForm;

public class Circle extends AbstractShape {

    public Circle(DisplayForm form, Board board, int x, int y, int size) {
        super(form, board, x, y, size);
    }

    @Override
    public void draw() {
        form.drawCircle(x, y, size, active);
    }

    @Override
    public Shape copy() {
        return new Circle(form, board, x, y, size);
    }
}
