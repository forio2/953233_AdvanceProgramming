package model;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import view.Platform;

public class Character extends Pane {
    Logger logger = LoggerFactory.getLogger(Character.class.getName());
    public static final int CHARACTER_WIDTH = 70;
    public static final int CHARACTER_HEIGHT = 64;

    private Image characterImg;


    private AnimatedSprite imageView;

    private int x;
    private int y;
    private int offsetX;
    private int offsetY;

    private KeyCode leftKey;
    private KeyCode rightKey;
    private KeyCode upKey;
    private KeyCode ultiKey;

    int xVelocity = 0;
    int yVelocity = 0;
    int xAcceleration = 1;
    int yAcceleration = 1;
    int xMaxVelocity = 7;
    int yMaxVelocity = 20;
    boolean isMoveLeft = false;
    boolean isMoveRight = false;
    boolean falling = true;
    boolean canJump = false;
    boolean jumping = false;

    public Character(int x, int y, int offsetX, int offsetY, KeyCode leftKey, KeyCode rightKey, KeyCode upKey, KeyCode ultiKey) {

        this.x = x;
        this.y = y;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.setTranslateX(x);
        this.setTranslateY(y);
        this.characterImg = new Image(getClass().getResourceAsStream("/assets/sprite_sheet.png"));
        this.imageView = new AnimatedSprite(characterImg,4,7,offsetX,offsetY,66,64);
        this.imageView.setFitWidth(CHARACTER_WIDTH);
        this.imageView.setFitHeight(CHARACTER_HEIGHT);
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.upKey = upKey;
        this.ultiKey = ultiKey;
        this.getChildren().addAll(this.imageView);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getOffsetX() {
        return offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public void moveLeft() {
        isMoveLeft = true;
        isMoveRight = false;
    }
    public void moveRight() {
        isMoveRight = true;
        isMoveLeft = false;
    }

    public void stop() {
        isMoveLeft = false;
        isMoveRight = false;
        xVelocity = 0;
    }

    public void dive() {
        if (canJump) {
            yVelocity = 7;
            canJump = false;
            jumping = true;
            falling = false;
        }
    }

    public void jump() {
        if (canJump) {
            yVelocity = yMaxVelocity;
            canJump = false;
            jumping = true;
            falling = false;
        }
    }

    public void checkReachHighest() {
        if(jumping &&  yVelocity <= 0) {
            jumping = false;
            falling = true;
            yVelocity = 0;
        }
    }

    public void checkReachFloor() {
        if(falling && y >= Platform.GROUND - CHARACTER_HEIGHT) {
            falling = false;
            canJump = true;
            yVelocity = 0;
        }
    }

    public void checkReachGameWall() {
        if(x <= 0) {
            x = 0;
        } else if( x+getWidth() >= Platform.WIDTH) {
            x = Platform.WIDTH-CHARACTER_WIDTH;
        }
    }

    public void moveX() {
        setTranslateX(x);

        if(isMoveLeft) {
            xVelocity = xVelocity >= xMaxVelocity? xMaxVelocity : xVelocity+xAcceleration;
            x = x - xVelocity;
        }
        if(isMoveRight) {
            xVelocity = xVelocity >= xMaxVelocity? xMaxVelocity : xVelocity+xAcceleration;
            x = x + xVelocity;
        }
    }

    public void moveY() {
        setTranslateY(y);

        if(falling) {
            yVelocity = yVelocity >= yMaxVelocity? yMaxVelocity : yVelocity+yAcceleration;
            y = y + yVelocity;
        }
        else if(jumping) {
            yVelocity = yVelocity <= 0 ? 0 : yVelocity-yAcceleration;
            y = y - yVelocity;
        }
    }

    public void repaint() {
        moveX();
        moveY();
    }

    public void trace() {
        logger.info("x:{} y:{} vx:{} vy:{}",x,y,xVelocity,yVelocity);
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

    public KeyCode getUtilKey() {
        return ultiKey;
    }

    public AnimatedSprite getImageView() { return imageView; }
}
