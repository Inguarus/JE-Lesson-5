package sample.javafx.save;

import sample.javafx.Board;
import sample.javafx.DisplayForm;
import sample.javafx.ShapeFactory;
import sample.javafx.shapes.*;

import java.util.ArrayList;
import java.util.List;

public class ShapeSave {

    private Shape.ShapeType saveType;

    private List<ShapeSave> list;

    private int size;
    private int x;
    private int y;

    public ShapeSave() {
    }

    public Shape.ShapeType getSaveType() {
        return saveType;
    }

    public List<ShapeSave> getList() {
        return list;
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

    public static Shape createShape(ShapeSave shapeSave, Board board, DisplayForm form) {
        Shape result = ShapeFactory.createShape(shapeSave.getSaveType(), form, board, shapeSave.getX(), shapeSave.getY(), shapeSave.getSize());
        if (shapeSave.getSaveType() == Shape.ShapeType.GROUP) {
            Group group = (Group) result;
            List<Shape> groupList = new ArrayList<Shape>();
            for (ShapeSave tmpShapeSave : shapeSave.getList()) {
                groupList.add(createShape(tmpShapeSave, board, form));
            }
            group.setList(groupList);
        }
        return result;
    }

    public static ShapeSave createShapeSave(Shape shape) {
        ShapeSave result = new ShapeSave();
        if (shape instanceof AbstractShape) {
            AbstractShape abstractShape = (AbstractShape) shape;
            result.x = abstractShape.getX();
            result.y = abstractShape.getY();
            result.size = abstractShape.getSize();
        }
        if (shape instanceof Circle) {
            result.saveType = Shape.ShapeType.CIRCLE;
        }
        if (shape instanceof Triangle) {
            result.saveType = Shape.ShapeType.TRIANGLE;
        }
        if (shape instanceof Rectangle) {
            result.saveType = Shape.ShapeType.RECTANGLE;
        }
        if (shape instanceof Group) {
            Group group = (Group) shape;
            result.list = new ArrayList<ShapeSave>();
            for (Shape tmpShape : group.getList()) {
                result.list.add(createShapeSave(tmpShape));
            }
            result.saveType = Shape.ShapeType.GROUP;
        }
        return result;
    }
}
