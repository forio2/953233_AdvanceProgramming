package ProjectMid.controller;

import ProjectMid.model.Select;
import javafx.scene.control.TextInputDialog;

public class AllEventHandlers{

    //2.4.7 2.25
    public static void onAdd(){
        TextInputDialog dialog = new TextInputDialog();
        Select select = new Select();
        dialog.setHeaderText(null);
        dialog.setGraphic(select);
        dialog.show();
    }
}