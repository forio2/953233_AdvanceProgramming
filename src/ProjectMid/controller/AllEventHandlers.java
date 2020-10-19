package ProjectMid.controller;

import ProjectMid.controller.Launcher;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class AllEventHandlers {
    public static void onRefresh() {
        try {
            Chapter2.controller.Launcher.refreshPane();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //2.4.7 2.25
    public static void onAdd() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Currency");
        dialog.setContentText("Currency code:");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        Optional<String> code = dialog.showAndWait();

        String retrievedJson = null;
        String url_str = String.format(
                "https://free.currconv.com/api/v7/currencies?apiKey=9bf3b840f309694ced0d");
        try {
            retrievedJson = IOUtils.toString(new URL(url_str),
                    Charset.defaultCharset());
        } catch (MalformedURLException e) {
            System.out.println("Encountered a Malformed Url exception");
        } catch (IOException e) {
            System.out.println("Encounter an IO exception");
        }

        JSONObject jsonOBJ = new JSONObject(retrievedJson);
        jsonOBJ = jsonOBJ.getJSONObject("results");
        Alert dialog2 = new Alert(Alert.AlertType.INFORMATION);
        dialog2.setTitle("notify");
        dialog2.setContentText("No No No");
        dialog2.setHeaderText(null);
        dialog2.showAndWait();
    }
}
