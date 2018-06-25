package sample.javafx.shapes;

import sample.javafx.Board;
import sample.javafx.DisplayForm;
import sample.javafx.Signification;

public abstract class AbstractShape implements Shape {


    protected DisplayForm form;
    protected Board board;
    protected final int WIDTH = 30;
    protected final int HEIGHT = 30;
    protected int size;
    protected double x;
    protected double y;
    protected boolean active;
    protected double speedX;
    protected double speedY;


    public AbstractShape(DisplayForm form, Board board, int size, double x, double y) {
        this.form = form;
        this.board = board;
        this.size = size;
        this.x = x;
        this.y = y;
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

    public int getSize() {
        return size;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public abstract void draw();
}
