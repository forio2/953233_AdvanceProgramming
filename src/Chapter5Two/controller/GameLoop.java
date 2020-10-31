package Chapter5Two.controller;

import Chapter5Two.model.Direction;
import Chapter5Two.model.Food;
import Chapter5Two.model.Snake;
import Chapter5Two.view.Platform;
import ProjectMid.model.DrawingLoop;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.input.KeyCode;

import java.awt.*;

public class GameLoop implements Runnable {
    private Platform platform;
    private Snake snake;
    private Food food;
    private float interval = 1000.0f / 10;
    private boolean running;
    public GameLoop(Platform platform, Snake snake, Food food) {
        this.snake = snake;
        this.platform = platform;
        this.food = food;
        running = true;
    }
    private void update() {
        KeyCode cur_key = platform.getKey();
        Direction cur_direction = snake.getCurrentDirection();
        if (cur_key == KeyCode.UP && cur_direction != Direction.DOWN)
            snake.setCurrentDirection(Direction.UP);
        else if (cur_key == KeyCode.DOWN && cur_direction != Direction.UP)
            snake.setCurrentDirection(Direction.DOWN);
        else if (cur_key == KeyCode.LEFT && cur_direction != Direction.RIGHT)
            snake.setCurrentDirection(Direction.LEFT);
        else if (cur_key == KeyCode.RIGHT && cur_direction != Direction.LEFT)
            snake.setCurrentDirection(Direction.RIGHT);
        snake.update();
    }
    private void checkCollision() {
        if (snake.isCollidingWith(food)) {
            snake.grow();
            food.respawn();
        }
        if (snake.isDead()) {
            Platform platform = new Platform();
            Snake snake = new Snake(new Point2D(platform.WIDTH/2,platform.HEIGHT/2));
            Food food = new Food();
            GameLoop gameLoop = new GameLoop(platform,snake,food);
            Scene scene = new Scene(platform,platform.WIDTH*platform.TILE_SIZE,platform.HEIGHT
                    * platform.TILE_SIZE);
            scene.setOnKeyPressed(event-> platform.setKey(event.getCode()));
            scene.setOnKeyReleased(event -> platform.setKey(null));
            Launcher.getStage().setTitle("Snake");
            Launcher.getStage().setScene(scene);
            Launcher.getStage().setResizable(false);
            Launcher.getStage().show();
            (new Thread(gameLoop)).start();
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setContentText("No No No");
            dialog.setHeaderText(null);
            dialog.showAndWait();
            running = true;
        }
    }

    private void redraw() {
        platform.render(snake,food);
    }
    @Override
    public void run() {
        while (running) {
            update();
            checkCollision();
            redraw();
            try {
                Thread.sleep((long)interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Launcher.setGameover(true);
    }

    public Snake getSnake() {
        return snake;
    }

    public Platform getPlatform() {
        return platform;
    }
}