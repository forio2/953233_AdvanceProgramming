package ProjectMid.controller;

import Chapter1.controller.Launcher;
import Chapter1.model.Item.Armor;
import Chapter1.model.Item.Weapon;
import ProjectMid.Item.BasedCharacter;
import ProjectMid.Item.listCharacter;
import ProjectMid.model.Select;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;

import java.util.ArrayList;


public class AllCustomHandler {
    public static String bg = "/ProjectMid/assets/Background.png";
    static boolean dragCompleted;

    public static class Unequip implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            BasedCharacter equip = Select.getMainCharacter();
            if (Select.getEquippedArmor() != null) {
                Select.setEquippedArmor(null);
                Select.setAllEquipments(GenItemList.setUpIList());
            }
            Select.refreshPane();
        }
    }
    public static void onDragDetected(MouseEvent event, BasedCharacter equipment, ImageView imgView) {
        Dragboard db = imgView.startDragAndDrop(TransferMode.ANY);
        db.setDragView(imgView.getImage());
        ClipboardContent content = new ClipboardContent();
        content.put(equipment.DATA_FORMAT, equipment);
        db.setContent(content);
        event.consume();
    }

    public static void onDragOver(DragEvent event, String type) {
        Dragboard dragboard = event.getDragboard();
        BasedCharacter retrievedEquipment = (BasedCharacter) dragboard.getContent(BasedCharacter.DATA_FORMAT);
        if (dragboard.hasContent(BasedCharacter.DATA_FORMAT) && retrievedEquipment.getClass().getSimpleName().equals(type))
            event.acceptTransferModes(TransferMode.MOVE);
    }

    public static void onDragDropped(DragEvent event, Label lbl, StackPane imgGroup) {
        dragCompleted = false;
        Dragboard dragboard = event.getDragboard();
        ArrayList<BasedCharacter> allEquipments = Select.getBasedCharacter();
        if (dragboard.hasContent(BasedCharacter.DATA_FORMAT)) {
            BasedCharacter retrievedEquipment = (BasedCharacter) dragboard.getContent(BasedCharacter.DATA_FORMAT);
            //ArrayList<BasedCharacter> character = Launcher.getMainCharacter();

            if (retrievedEquipment.getClass().getSimpleName().equals("listCharacter")) {
                if (Select.getEquippedArmor() != null) {
                    allEquipments.add(Select.getEquippedArmor());
                }
                Select.setEquippedArmor((listCharacter) retrievedEquipment);

            }
            Select.setAllEquipments(allEquipments);
            Select.refreshPane();
            ImageView imgView = new ImageView();
            if (imgGroup.getChildren().size() != 1) {
                imgGroup.getChildren().remove(1);
                Select.refreshPane();
            }
            lbl.setText(retrievedEquipment.getClass().getSimpleName() + ":\n" + retrievedEquipment.getName());
            imgView.setImage(new Image(AllCustomHandler.class.getClassLoader().getResource(retrievedEquipment.getImagepath()).toString()));
            imgGroup.getChildren().add(imgView);
            dragCompleted = true;
        }
        event.setDropCompleted(dragCompleted);
    }

    public String getBackground(){
        return bg;
    }

    public static void onEquipDone(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        ArrayList<BasedCharacter> allEquipments = Select.getBasedCharacter();
        BasedCharacter retrievedEquipment = (BasedCharacter) dragboard.getContent(
                BasedCharacter.DATA_FORMAT);
        int pos = -1;
        for (int i = 0; i < allEquipments.size(); i++) {
            if (allEquipments.get(i).getName().equals(retrievedEquipment.getName())) {
                pos = i;
            }
        }
        if (pos != -1) {
            allEquipments.remove(pos);
        }
        Select.setAllEquipments(allEquipments);
        Select.refreshPane();
    }
}
