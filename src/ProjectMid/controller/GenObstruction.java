package ProjectMid.controller;

import ProjectMid.model.AnimatedSprite;
import ProjectMid.view.Platform;
import ProjectMid.model.Character;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.Random;


public class GenObstruction extends Pane {
    public static final int CHARACTER_WIDTH = 32;
    public static final int CHARACTER_HEIGHT = 64;
    private Image characterImg;
    private AnimatedSprite imageView;
    private int x;
    private int y;
    private KeyCode leftKey;
    private KeyCode rightKey;
    private KeyCode upKey;
    int xVelocity = 0;
    int yVelocity = 0;
    int xAcceleration = 1;
    int yAcceleration = 1;
    int xMaxVelocity = 7;
    int yMaxVelocity = 17;
    boolean isMoveLeft = false;
    boolean isMoveRight = false;
    boolean isFalling = true;
    Random GenObj;

    public void moveLeft() {
        isMoveLeft = false;
        isMoveRight = true;
    }

    public void moveRight() {
        isMoveLeft = true;
        isMoveRight = false;
    }

    public void stop() {
        isMoveLeft = false;
        isMoveRight = false;
    }
    public void moveX() {
        setTranslateX(500+x);
        if(isMoveLeft) {
            xVelocity = xVelocity>=xMaxVelocity? xMaxVelocity : xVelocity+xAcceleration;
            x = x - xVelocity;
        }
        if(isMoveRight) {
            xVelocity = xVelocity>=xMaxVelocity? xMaxVelocity : xVelocity+xAcceleration;
            x = x + xVelocity;
        }
    }

    public void moveY() {
        setTranslateY(y);
        if(isFalling) {
            yVelocity = yVelocity >= yMaxVelocity? yMaxVelocity : yVelocity+yAcceleration;
            y = y + yVelocity;
        }
    }

    public GenObstruction(int x, int y, int offsetX, int offsetY, KeyCode leftKey, KeyCode rightKey, KeyCode upKey,
                          String url , int weight, int height) {
        this.x = x;
        this.y = y;
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.characterImg = new Image(getClass().getResourceAsStream(url));
        this.imageView = new AnimatedSprite(characterImg,4,4,1,offsetX,offsetY
                ,weight,height);
        this.imageView.setFitWidth(CHARACTER_WIDTH);
        this.imageView.setFitHeight(CHARACTER_HEIGHT);
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.getChildren().addAll(this.imageView);
    }

    public void checkReachGameWall() {
        if(x <= -500) {
            GenObj = new Random();
            x = GenObj.nextInt(500)+300;

        }
    }

    public void checkReachHighest () {
        if(yVelocity <= 0) {
            isFalling = true;
            yVelocity = 0;
        }
    }

    public void checkReachFloor() {
        if(isFalling && y >= Platform.GROUND - CHARACTER_HEIGHT) {
            isFalling = false;
            yVelocity = 0;
        }
    }

    public void repaint() {
        moveX();
        moveY();
    }

    public KeyCode getLeftKey() {
        return leftKey;
    }

    public KeyCode getRightKey() {
        return rightKey;
    }

    public KeyCode getUpKey() {
        return upKey;
    }

    public AnimatedSprite getImageView() {
        return imageView;
    }
}

/*
    private final static String obstruction_URL = "ProjectMid/assets/StillMario.png";
    ImageView[] obj;
    Random GenObj;
 */