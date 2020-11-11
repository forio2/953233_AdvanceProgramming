package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import model.Character;
import model.Keys;

import java.util.ArrayList;

public class Platform extends Pane {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;
    public static final int GROUND = 300;

    private Image platformImg;
    private ArrayList<Character> characterList = new ArrayList();

    private Keys keys;

    public Platform() {
        keys = new Keys();
        platformImg = new Image(getClass().getResourceAsStream("/assets/Background.png"));
        ImageView backgroundImg = new ImageView(platformImg);
        backgroundImg.setFitHeight(HEIGHT);
        backgroundImg.setFitWidth(WIDTH);
        characterList.add(new Character(30, 30,0,270, KeyCode.A,KeyCode.D,KeyCode.W, KeyCode.J));
        characterList.add(new Character(Platform.WIDTH-60, 30,0,270, KeyCode.LEFT,KeyCode.RIGHT,KeyCode.UP, KeyCode.ENTER)); //397
        getChildren().add(backgroundImg);
        getChildren().addAll(characterList);
    }

    public ArrayList<Character> getCharacterList() {
        return characterList;
    }

    public Keys getKeys() {
        return keys;
    }
}

