package ProjectMid.view;


import ProjectMid.controller.AllCustomHandler;
import ProjectMid.Item.listCharacter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import static ProjectMid.controller.AllCustomHandler.onDragDropped;
import static ProjectMid.controller.AllCustomHandler.onDragOver;

public class EquipPane extends ScrollPane {
    private listCharacter equippedlistCharacter;

    public EquipPane() { }

    public void drawPane( listCharacter equippedlistCharacter) {
        this.equippedlistCharacter = equippedlistCharacter;
        Pane equipmentInfo = getDetailsPane();
        this.setContent(equipmentInfo);
    }

    private Pane getDetailsPane() {
        Pane equipmentInfoPane = new VBox(10);
        equipmentInfoPane.setBorder(null);
        ((VBox) equipmentInfoPane).setAlignment(Pos.CENTER);
        equipmentInfoPane.setPadding(new Insets(50, 50, 50, 50));
        Label  armorLbl;

        StackPane armorImgGroup = new StackPane();

        ImageView bg2 = new ImageView();

        ImageView armorImg = new ImageView();

        bg2.setImage(new Image(getClass().getClassLoader().getResource("ProjectMid/assets/blank.png").toString()));

        armorImgGroup.getChildren().add(bg2);


        if (equippedlistCharacter != null) {
            armorLbl = new Label("listCharacter: \n" + equippedlistCharacter.getName());
            armorImg.setImage(new Image(getClass().getClassLoader().getResource(equippedlistCharacter.getImagepath()).toString()));
            armorImgGroup.getChildren().add(armorImg);
        } else {
            armorLbl = new Label("listCharacter:");
            armorImg.setImage(new Image(getClass().getClassLoader().getResource("ProjectMid/assets/blank.png").toString()));
        }
        armorImgGroup.setOnDragOver(e -> onDragOver(e, "listCharacter"));
        armorImgGroup.setOnDragDropped(e -> onDragDropped(e, armorLbl, armorImgGroup));

        Button unequip = new Button();
        unequip.setText("unequip");
        unequip.setOnAction(new AllCustomHandler.Unequip());
        equipmentInfoPane.getChildren().addAll(  armorLbl, armorImgGroup,unequip );
        return equipmentInfoPane;
    }


}
