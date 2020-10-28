package ProjectMid.view;

import ProjectMid.controller.AllEventHandlers;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.time.LocalDateTime;

//2.4.4
public class TopPane extends FlowPane {
    private Button add;

    private Label update;
    public TopPane() {
        this.setPadding(new Insets(10)); this.setHgap(10);
        this.setPrefSize(640,20);
        add = new Button("Add");
        add.setOnAction(event -> AllEventHandlers.onAdd());
        this.getChildren().add(add);
    }
}