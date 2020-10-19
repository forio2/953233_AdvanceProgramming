package ProjectMid.model;

import ProjectMid.controller.GenObstruction;
import ProjectMid.view.Platform;

public class DrawingLoop implements Runnable {
    private Platform platform;
    private int frameRate;
    private float interval;
    private boolean running;
    public DrawingLoop(Platform platform) {
        this.platform = platform;
        frameRate = 60;
        interval = 1000.0f / frameRate; // 1000 ms = 1 second
        running = true;
    }
    private void checkDrawCollisions(Character character) {
        character.checkReachHighest();
        character.checkReachFloor();
    }

    private void checkDrawCollisionsObj(GenObstruction obj) {
        obj.checkReachGameWall();
        obj.checkReachHighest();
        obj.checkReachFloor();
    }
    private void paint(Character character) {
        character.repaint();
    }

    private void paintObj(GenObstruction obj) {
        obj.repaint();
    }

    @Override
    public void run() {
        while (running) {
            float time = System.currentTimeMillis();
            checkDrawCollisions(platform.getCharacter());
            paint(platform.getCharacter());
            checkDrawCollisionsObj(platform.getObstruction());
            paintObj(platform.getObstruction());
            time = System.currentTimeMillis() - time;
            if (time < interval) {
                try {
                    Thread.sleep((long) (interval - time));
                } catch (InterruptedException e) {
                }
            } else {
                try {
                    Thread.sleep((long) (interval - (interval % time)));
                } catch (InterruptedException e) {
                }
            }
        }
    }
}