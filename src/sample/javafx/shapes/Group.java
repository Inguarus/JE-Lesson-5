package sample.javafx.shapes;

import java.util.ArrayList;
import java.util.List;

public class Group implements Shape {

    public Group() {
    }

    private List<Shape> shapes = new ArrayList<>();

    private Group(List<Shape> shapes) {
        setList(shapes);
    }

    private void addShape(Shape shape) {
        if (shape instanceof Group) {
            Group group = (Group) shape;
            shapes.addAll(group.getList());
        } else {
            shapes.add(shape);
        }
    }


    @Override
    public void draw() {
        for (Shape shape : shapes) {
            shape.draw();
        }
    }

    @Override
    public void setActive(boolean active) {
        for (Shape shape : shapes) {
            shape.setActive(active);
        }
    }

    @Override
    public void decrease() {
        for (Shape shape : shapes) {
            shape.decrease();
        }
    }

    @Override
    public void increase() {
        for (Shape shape : shapes) {
            shape.increase();
        }
    }

    public void setList(List<Shape> shapes) {
        this.shapes.clear();
        for (Shape shape : shapes) {
            addShape(shape);
        }
    }

    public List<Shape> getList() {
        return shapes;
    }
}
