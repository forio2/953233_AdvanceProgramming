package ProjectMid.controller;
import ProjectMid.model.Character;
import ProjectMid.view.Platform;
import javafx.scene.image.ImageView;

import java.util.Random;

//Imports are omitted
public class GameLoop implements Runnable {
    private Platform platform;
    private int frameRate;
    private float interval;
    private boolean running;

    public GameLoop(Platform platform) {
        this.platform = platform;
        //frameRate = 60;
        frameRate = 10;
        interval = 1000.0f / frameRate;
        running = true;
    }
    private void update(Character character) {
        if (platform.getKeys().isPressed(character.getLeftKey())) {
            character.setScaleX(-1);
            character.moveLeft();
        }

        if (platform.getKeys().isPressed(character.getRightKey())) {
            character.setScaleX(1);
            character.moveRight();
        }

        if (!platform.getKeys().isPressed(character.getLeftKey()) && !platform.getKeys().isPressed(character.getRightKey()) ) {
            character.stop();
        }

        if (platform.getKeys().isPressed(character.getLeftKey()) || platform.getKeys().isPressed(character.getRightKey())) {
            character.getImageView().tick();
        }

        if (platform.getKeys().isPressed(character.getUpKey())) {
            character.jump();
        }
    }

    private void updateObj(GenObstruction obj) {
        if (platform.getKeys().isPressed(obj.getLeftKey())) {
            obj.moveLeft();
        }

        if (platform.getKeys().isPressed(obj.getRightKey())) {
            obj.moveRight();
        }

        if (!platform.getKeys().isPressed(obj.getLeftKey()) && !platform.getKeys().isPressed(obj.getRightKey()) ) {
            obj.stop();
        }
    }

    @Override
    public void run() {
        while (running) {
            float time = System.currentTimeMillis();
            update(platform.getCharacter());
            updateObj(platform.getObstruction());
            time = System.currentTimeMillis() - time;
            if (time < interval) {
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep((long) (interval - (interval % time)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
