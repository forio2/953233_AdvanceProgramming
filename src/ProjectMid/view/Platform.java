package ProjectMid.view;

import Chapter2.view.TopPane;
import ProjectMid.controller.GenObstruction;
import ProjectMid.model.Character;
import ProjectMid.model.Keys;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;


public class Platform extends Pane {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;
    public final static int GROUND = 300;
    private Image platformImg;
    private Character character;
    private GenObstruction obj;
    private static TopPane topPane;

    private Keys keys;
    public Platform() {
        keys = new Keys();
        topPane = new TopPane();
        platformImg = new Image(getClass().getResourceAsStream("/ProjectMid/assets/Background.png"));
        ImageView backgroundImg = new ImageView(platformImg);
        backgroundImg.setFitHeight(HEIGHT);
        backgroundImg.setFitWidth(WIDTH);
        character = new Character(30, 30,0,0, KeyCode.A,KeyCode.D,KeyCode.W, "/ProjectMid/assets/MarioSheet.png", 16, 32);
        obj = new GenObstruction(30, 30,0,0, KeyCode.A,KeyCode.D,KeyCode.W, "/ProjectMid/assets/wood.png", 64, 64);
        getChildren().addAll(backgroundImg, topPane, character, obj);
    }

    public Character getCharacter() { return character; }

    public GenObstruction getObstruction() { return obj; }

    public Keys getKeys() { return keys; }
}