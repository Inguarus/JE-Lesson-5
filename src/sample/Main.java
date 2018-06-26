package sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import sample.javafx.Board;
import sample.javafx.Config;
import sample.javafx.DisplayForm;
import sample.javafx.Signification;
import sample.javafx.save.Save;
import sample.javafx.shapes.Shape;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    private Board board;


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Java FX");
        final Canvas canvas = new Canvas(Config.WIDTH, Config.HEIGHT);
        final BorderPane group = new BorderPane(canvas);
        group.setCenter(canvas);
        final Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.show();

        createBoard(canvas, primaryStage);
        registerOnKeyPressListener(primaryStage.getScene());
        registerOnMousePressListener(primaryStage.getScene());

    }


    public static void main(String[] args) {
        launch(args);
    }

    private void createBoard(Canvas canvas, final Stage primaryStage) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        DisplayForm form = new DisplayForm(gc);
        board = new Board(form, new Board.ShapeSelected() {
            @Override
            public void onShapeSelected(int index) {
                primaryStage.setTitle(String.valueOf(index));
            }
        });
        File f = new File(Signification.SAVE_FILE);
        if (f.exists()) {
            loadScene();
        }
    }


    public void registerOnKeyPressListener(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        board.move(Board.Direction.UP);
                        break;
                    case RIGHT:
                        board.move(Board.Direction.RIGHT);
                        break;
                    case DOWN:
                        board.move(Board.Direction.DOWN);
                        break;
                    case LEFT:
                        board.move(Board.Direction.LEFT);
                        break;
                    case DIGIT1:
                        board.add(Shape.ShapeType.CIRCLE);
                        break;
                    case DIGIT2:
                        board.add(Shape.ShapeType.RECTANGLE);
                        break;
                    case DIGIT3:
                        board.add(Shape.ShapeType.TRIANGLE);
                        break;
                    case PAGE_DOWN:
                        board.previous();
                        break;
                    case PAGE_UP:
                        board.next();
                        break;
                    case Q:
                        board.decrease();
                        break;
                    case W:
                        board.increase();
                        break;
                    case DELETE:
                        board.remove();
                        break;
                    case S:
                        saveScene();
                        break;
                    case L:
                        loadScene();
                        break;
                }
            }
        });
    }

    private void loadScene() {
        try {
            File f = new File(Signification.SAVE_FILE);
            String jsonString = FileUtils.readFileToString(f, "windows-1251");

            Gson gson = new GsonBuilder().create();
            Save save = gson.fromJson(jsonString, Save.class);
            board.loadSave(save);
        } catch (Exception e) {
            System.out.println("Can't load from file");
        }
    }

    private void saveScene() {
        Gson gson = new GsonBuilder().create();
        String jsonString = gson.toJson(board.makeSave());
        File f = new File(Signification.SAVE_FILE);
        try {
            FileUtils.writeStringToFile(f, jsonString, "windows-1251", false);
        } catch (IOException e) {
            System.out.println("Can't save to file");
        }
    }

    public void registerOnMousePressListener(Scene scene) {
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isControlDown()) {
                    board.merge((int) event.getSceneX(), (int) event.getSceneY(), true);
                }
                if (event.isShiftDown()) {
                    board.merge((int) event.getSceneX(), (int) event.getSceneY(), false);
                }
            }
        });
    }


}
