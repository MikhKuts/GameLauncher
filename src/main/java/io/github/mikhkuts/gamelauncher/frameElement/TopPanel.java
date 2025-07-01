package io.github.mikhkuts.gamelauncher.frameElement;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TopPanel {
    public Button closeButton = new Button("X");
    private final Button minimalButton = new Button("–");
    public AnchorPane topPanel = new AnchorPane();

    public TopPanel(boolean canMinimized){
        if (canMinimized) topPanel.getChildren().addAll(closeButton, minimalButton);
        else topPanel.getChildren().addAll(closeButton);
        initComponents();
    }

    private void initComponents() {

        closeButton.getStyleClass().addAll("top-panel", "close-button");
        closeButton.setOnAction(e -> {
            ((Stage) closeButton.getScene().getWindow()).close();
        });


        minimalButton.getStyleClass().add("top-panel");
        minimalButton.setOnAction(e -> {
            ((Stage) minimalButton.getScene().getWindow()).setIconified(true);
        });

        AnchorPane.setTopAnchor(closeButton, 0.0);
        AnchorPane.setRightAnchor(closeButton, 0.0);
        AnchorPane.setTopAnchor(minimalButton, 0.0);
        AnchorPane.setRightAnchor(minimalButton, 35.0);

        topPanel.setOnMousePressed(pressEvent -> {
            topPanel.setOnMouseDragged(dragEvent -> {
                ((Stage) (topPanel.getScene().getWindow())).setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                ((Stage) (topPanel.getScene().getWindow())).setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
    }

}
