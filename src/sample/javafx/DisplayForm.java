package sample.javafx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DisplayForm {

    private GraphicsContext gc;

    public DisplayForm(GraphicsContext gc) {
        this.gc = gc;
    }

    public void cleanScreen() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }

    public void drawCircle(double x, double y, int size, boolean full) {
        gc.setFill(Color.RED);
        gc.setStroke(Color.RED);
        gc.setLineWidth(3);
        if (full) {
            gc.fillOval(x, y, size, size);
        } else {
            gc.strokeOval(x, y, size, size);
        }
    }

    public void drawRectangle(double x, double y, int size, boolean full) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.GREEN);
        gc.setLineWidth(3);
        if (full) {
            gc.fillRect(x, y, size, size);
        } else {
            gc.strokeRect(x, y, size, size);
        }
    }

    public void drawTriangle(double x, double y, int size, boolean full) {
        gc.setFill(Color.BROWN);
        gc.setStroke(Color.BROWN);
        gc.setLineWidth(3);
        if (full) {
            gc.fillPolygon(new double[]{x, x + size / 2.0, x + size}, new double[]{y + size, y, y + size}, 3);
        } else {
            gc.strokePolygon(new double[]{x, x + size / 2.0, x + size}, new double[]{y + size, y, y + size}, 3);
        }
    }

}
