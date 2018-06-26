package sample.javafx;

import sample.javafx.save.Save;
import sample.javafx.save.ShapeSave;
import sample.javafx.shapes.Group;
import sample.javafx.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public enum Direction {UP, RIGHT, DOWN, LEFT}

    public interface ShapeSelected {
        void onShapeSelected(int index);
    }


    private DisplayForm form;
    private ShapeSelected shapeSelected;
    private List<Shape> shapes = new ArrayList<>();
    private int activeShapeIndex;


    public Board(DisplayForm form, ShapeSelected selected) {
        this.form = form;
        this.shapeSelected = selected;
        add(Shape.ShapeType.CIRCLE);

    }

    public void move(Direction direction) {
        getActiveShape().move(direction);
        drawAll();
    }


    public void add(Shape.ShapeType shapeType) {
        shapes.add(ShapeFactory.createShape(shapeType, form, this, 5, 5));
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

    public void next() {
        activeShapeIndex++;
        if (activeShapeIndex >= shapes.size()) {
            activeShapeIndex = 0;
        }
        shapeSelected.onShapeSelected(activeShapeIndex);
        drawAll();
    }

    public void previous() {
        activeShapeIndex--;
        if (activeShapeIndex < 0) {
            activeShapeIndex = shapes.size() - 1;
        }
        shapeSelected.onShapeSelected(activeShapeIndex);
        drawAll();
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

    public void remove() {
        if (shapes.size() == 1) {
            return;
        }
        shapes.remove(activeShapeIndex);
        previous();
        drawAll();
    }

    public void merge(int sceneX, int sceneY, boolean merge) {
        for (int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            if (shape.consistPoint(sceneX, sceneY)) {
                Shape result = new Group(shape.copy(), getActiveShape().copy());

                if (merge) {
                    Shape forRemove1 = shapes.get(i);
                    Shape forRemove2 = shapes.get(activeShapeIndex);
                    shapes.remove(forRemove1);
                    shapes.remove(forRemove2);
                }
                shapes.add(result);
                activeShapeIndex = shapes.size() - 1;
                shapeSelected.onShapeSelected(activeShapeIndex);
                drawAll();
                break;
            }
        }
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

    public Save makeSave() {
        List<ShapeSave> saveList = new ArrayList<ShapeSave>();
        for (Shape shape : shapes) {
            ShapeSave save = ShapeSave.createShapeSave(shape);
            saveList.add(save);
        }
        return new Save(saveList, activeShapeIndex);
    }
}
