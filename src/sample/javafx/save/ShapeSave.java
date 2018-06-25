package sample.javafx.save;

import sample.javafx.Board;
import sample.javafx.DisplayForm;
import sample.javafx.ShapeFactory;
import sample.javafx.shapes.Group;
import sample.javafx.shapes.Shape;

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
}
