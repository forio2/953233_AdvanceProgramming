package Chapter1.controller;

import Chapter1.model.Character.BasedCharacter;
import Chapter1.model.Item.Armor;
import Chapter1.model.Item.BasedEquipment;
import Chapter1.model.Item.Weapon;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class AllCustomHandler {
    public static class GenHeroHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Launcher.setMainCharacter(GenCharacter.setUpCharacter());
            //1.4.7.4
            BasedCharacter equip = Launcher.getMainCharacter();
            if (Launcher.getEquippedWeapon() != null){
                equip.equipWeapon((Weapon) Launcher.getEquippedWeapon());
            }
            if (Launcher.getEquippedArmor() != null){
                equip.equipArmor((Armor) Launcher.getEquippedArmor());
            }
            Launcher.refreshPane();
        }
    }

    public static class UnEquip implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            //1.4.7.5
            BasedCharacter equip = Launcher.getMainCharacter();
            if (Launcher.getEquippedWeapon() != null){
                Launcher.setEquippedWeapon(null);
                Launcher.setAllEquipments(GenItemList.setUpItemList());
                equip.unequipWeapon();
            }
            if (Launcher.getEquippedArmor() != null){
                Launcher.setEquippedArmor(null);
                Launcher.setAllEquipments(GenItemList.setUpItemList());
                equip.unequipArmor();
            }
            Launcher.refreshPane();
        }
    }

    //1.4.5
    public static void onDragDetected(MouseEvent event, BasedEquipment equipment, ImageView imgView) {
        Dragboard db = imgView.startDragAndDrop(TransferMode.ANY);
        db.setDragView(imgView.getImage());
        ClipboardContent content = new ClipboardContent();
        content.put(equipment.DATA_FORMAT, equipment);
        db.setContent(content);
        event.consume();
    }
    public static void onDragOver(DragEvent event, String type) {
        Dragboard dragboard = event.getDragboard();
        BasedEquipment retrievedEquipment = (BasedEquipment) dragboard.getContent(BasedEquipment.DATA_FORMAT);
        if (dragboard.hasContent(BasedEquipment.DATA_FORMAT) && retrievedEquipment.getClass().getSimpleName().equals(type))
            event.acceptTransferModes(TransferMode.MOVE);
    }
    /*1.4.5
    public static void onDragDropped(DragEvent event, Label lbl, StackPane imgGroup) {
        boolean dragCompleted = false;
        Dragboard dragboard = event.getDragboard();
        if (dragboard.hasContent(BasedEquipment.DATA_FORMAT)) {
            BasedEquipment retrievedEquipment = (BasedEquipment) dragboard.getContent(BasedEquipment.DATA_FORMAT);
            if (retrievedEquipment.getClass().getSimpleName().equals("Weapon")) {
                Launcher.setEquippedWeapon((Weapon) retrievedEquipment);
            } else {
                Launcher.setEquippedArmor((Armor) retrievedEquipment);
            }
            ImageView imgView = new ImageView();
            if (imgGroup.getChildren().size() != 1) {
                imgGroup.getChildren().remove(1);
                Launcher.refreshPane();
            }
            lbl.setText(retrievedEquipment.getClass().getSimpleName() + ":\n" + retrievedEquipment.getName());
            imgView.setImage(new Image(AllCustomHandler.class.getClassLoader().getResource(retrievedEquipment.getImagepath()).toString()));
            imgGroup.getChildren().add(imgView);
            dragCompleted = true;
        }
        event.setDropCompleted(dragCompleted);
    }
    */
    //1.4.6
    public static void onDragDropped(DragEvent event, Label lbl, StackPane imgGroup) {
        boolean dragCompleted = false;
        Dragboard dragboard = event.getDragboard();
        ArrayList<BasedEquipment> allEquipments = Launcher.getAllEquipments();
        if (dragboard.hasContent(BasedEquipment.DATA_FORMAT)) {
            BasedEquipment retrievedEquipment = (BasedEquipment) dragboard.getContent(BasedEquipment.DATA_FORMAT);
            BasedCharacter character = Launcher.getMainCharacter();

            if (retrievedEquipment.getClass().getSimpleName().equals("Weapon")) {
                if (Launcher.getEquippedWeapon() != null) {
                    allEquipments.add(Launcher.getEquippedWeapon());
                } //
                Launcher.setEquippedWeapon((Weapon) retrievedEquipment);
                character.equipWeapon((Weapon) retrievedEquipment);
            } else { //
                if (Launcher.getEquippedArmor() != null) {
                    allEquipments.add(Launcher.getEquippedArmor());
                }
                Launcher.setEquippedArmor((Armor) retrievedEquipment);
                character.equipArmor((Armor) retrievedEquipment);
            }
            Launcher.setMainCharacter(character);
            Launcher.setAllEquipments(allEquipments);
            Launcher.refreshPane();
            ImageView imgView = new ImageView();
            if (imgGroup.getChildren().size() != 1) {
                imgGroup.getChildren().remove(1);
                Launcher.refreshPane();
            }
            lbl.setText(retrievedEquipment.getClass().getSimpleName() + ":\n" + retrievedEquipment.getName());
            imgView.setImage(new Image(AllCustomHandler.class.getClassLoader().getResource(retrievedEquipment.getImagepath()).toString()));
            imgGroup.getChildren().add(imgView);
            dragCompleted = true;
        }
        event.setDropCompleted(dragCompleted);
    }
    // remove the equipment entity from the allEquipments
    public static void onEquipDone(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        ArrayList<BasedEquipment> allEquipments = Launcher.getAllEquipments();
        BasedEquipment retrievedEquipment = (BasedEquipment) dragboard.getContent(
                BasedEquipment.DATA_FORMAT);
        int pos = -1;
        for (int i = 0; i < allEquipments.size(); i++) {
            if (allEquipments.get(i).getName().equals(retrievedEquipment.getName())) {
                pos = i;
            }
        }
        if (pos != -1) {
            allEquipments.remove(pos);
        }
        Launcher.setAllEquipments(allEquipments);
        Launcher.refreshPane();
    }
}
