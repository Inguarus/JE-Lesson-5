package sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import sample.javafx.Board;
import sample.javafx.Config;
import sample.javafx.DisplayForm;
import sample.javafx.Signification;
import sample.javafx.save.Save;

import java.io.File;

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


                        break;
                    case RIGHT:

                        break;
                    case DOWN:

                        break;
                    case LEFT:

                        break;
                    case DIGIT1:

                        break;
                    case DIGIT2:

                        break;
                    case DIGIT3:

                        break;
                    case PAGE_DOWN:

                        break;
                    case PAGE_UP:

                        break;
                    case Q:
                        board.increase();
                        break;
                    case W:
                        board.decrease();
                        break;
                    case DELETE:

                        break;
                    case S:

                        break;
                    case L:

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


}
