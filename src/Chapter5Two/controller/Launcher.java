package Chapter5Two.controller;

import Chapter5Two.model.Food;
import Chapter5Two.model.Snake;
import Chapter5Two.view.Platform;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Launcher extends Application {
    private static Stage stage;
    private static boolean gameover;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        gameover = false;
        stage = primaryStage;
        Platform platform = new Platform();
        Snake snake = new Snake(new Point2D(platform.WIDTH/2,platform.HEIGHT/2));
        Food food = new Food();
        GameLoop gameLoop = new GameLoop(platform,snake,food);
        Scene scene = new Scene(platform,platform.WIDTH*platform.TILE_SIZE,platform.HEIGHT
                * platform.TILE_SIZE);
        scene.setOnKeyPressed(event-> platform.setKey(event.getCode()));
        scene.setOnKeyReleased(event -> platform.setKey(null));
        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        (new Thread(gameLoop)).start();
        if(gameover){
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setContentText("No No No");
            dialog.setHeaderText(null);
            dialog.showAndWait();
        }
        System.out.println(gameover);
    }

    public static Stage getStage(){
        return  stage;
    }

    public static void setGameover(boolean over){
        gameover = over;
    }
}

