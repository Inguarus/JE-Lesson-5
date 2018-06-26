package sample.javafx.shapes;

import sample.javafx.Board;
import sample.javafx.DisplayForm;
import sample.javafx.Signification;

public abstract class AbstractShape implements Shape {


    protected DisplayForm form;
    protected Board board;
    protected int size;
    protected int x;
    protected int y;
    protected boolean active;


    public AbstractShape(DisplayForm form, Board board, int size, int x, int y) {
        this.form = form;
        this.board = board;
        this.size = size;
        this.x = x;
        this.y = y;
    }

    @Override
    public void move(Board.Direction direction) {
        switch (direction) {
            case UP:
                y -= Signification.SPEED;
                break;
            case RIGHT:
                x += Signification.SPEED;
                break;
            case DOWN:
                y += Signification.SPEED;
                break;
            case LEFT:
                x -= Signification.SPEED;
                break;
        }
    }

    @Override
    public void decrease() {
        size -= Signification.SIZE_INC;
        if (size < Signification.MIN_SIZE) {
            size = Signification.MIN_SIZE;
        }
    }

    @Override
    public void increase() {
        size += Signification.SIZE_INC;
        if (size > Signification.MAX_SIZE) {
            size = Signification.MAX_SIZE;
        }
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean consistPoint(int sceneX, int sceneY) {
        return between(sceneX, x, x + size) && between(sceneY, y, y + size);
    }

    private static boolean between(int test, int a, int b) {
        return test >= a && test <= b;
    }

    public int getSize() {
        return size;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract void draw();
}
