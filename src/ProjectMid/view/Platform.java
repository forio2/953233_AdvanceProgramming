package ProjectMid.view;

import ProjectMid.model.Character;
import ProjectMid.model.Keys;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.ArrayList;


public class Platform extends Pane {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;
    public final static int GROUND = 300;
    private Image platformImg;
    private ArrayList<Character> characterList;
    private Keys keys;
    private static TopPane topPane;

    public Platform() {
        characterList = new ArrayList<>();
        keys = new Keys();
        topPane = new TopPane();
        platformImg = new Image(getClass().getResourceAsStream("/ProjectMid/assets/Background.png"));
        ImageView backgroundImg = new ImageView(platformImg);
        backgroundImg.setFitHeight(HEIGHT);
        backgroundImg.setFitWidth(WIDTH);
        //character = new Character(30, 30,KeyCode.A,KeyCode.D,KeyCode.W);
        characterList.add(new Character(30, 30,0,0, KeyCode.A,KeyCode.D,KeyCode.W, "/ProjectMid/assets/MarioSheet.png", 16, 32, 'A'));
        characterList.add(new Character(Platform.WIDTH, 30,0,0, KeyCode.A,KeyCode.D,KeyCode.W, "/ProjectMid/assets/wood.png", 64, 64, 'B'));
        getChildren().addAll(backgroundImg, topPane);
        getChildren().addAll(characterList);
    }
    public ArrayList<Character> getCharacterList() {
        return characterList;
    }

    public Keys getKeys() { return keys; }
}