package sample.javafx;

import sample.javafx.save.Save;
import sample.javafx.save.ShapeSave;
import sample.javafx.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public interface ShapeSelected {
        void onShapeSelected(int index);
    }


    private int activeShapeIndex;
    private DisplayForm form;

    private List<Shape> shapes = new ArrayList<>();
    private ShapeSelected shapeSelected;


    public Board(DisplayForm form, ShapeSelected shapeSelected) {
        this.form = form;
        add(Shape.ShapeType.CIRCLE);

    }

    public void add(Shape.ShapeType shapeType) {
        shapes.add(ShapeFactory.createShape(shapeType, form, this, 0, 0));
        activeShapeIndex = shapes.size() - 1;
        shapeSelected.onShapeSelected(activeShapeIndex);
        drawAll();
    }


    public void drawAll() {
        form.cleanScreen();
        for (int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            if (i == activeShapeIndex) {
                shape.setActive(true);
            } else {
                shape.setActive(false);
            }
            shape.draw();
        }
    }

    private Shape getActiveShape() {
        return shapes.get(activeShapeIndex);
    }

    public void decrease() {
        getActiveShape().decrease();
        drawAll();
    }

    public void increase() {
        getActiveShape().increase();
        drawAll();
    }

    public void loadSave(Save save) {
        shapes.clear();
        List<ShapeSave> saveList = save.getList();
        activeShapeIndex = save.getActiveShapeIndex();
        for (ShapeSave shapeSave : saveList) {
            Shape shape = ShapeSave.createShape(shapeSave, this, form);
            shapes.add(shape);
        }
        drawAll();
    }
}
