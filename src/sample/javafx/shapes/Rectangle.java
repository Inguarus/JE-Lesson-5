package sample.javafx.shapes;

import sample.javafx.Board;
import sample.javafx.DisplayForm;

public class Rectangle extends AbstractShape {

    public Rectangle(DisplayForm form, Board board, int x, int y, int size) {
        super(form, board, x, y, size);
    }

    @Override
    public void draw() {
        form.drawRectangle(x, y, size, active);

    }

    @Override
    public void setActive(boolean b) {

    }

    @Override
    public void decrease() {

    }

    @Override
    public void increase() {

    }

}
