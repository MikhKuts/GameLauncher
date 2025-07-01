package io.github.mikhkuts.gamelauncher.frames;
import java.io.File;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

import io.github.mikhkuts.gamelauncher.forGL.*;
import io.github.mikhkuts.gamelauncher.frameElement.TopPanel;
import io.github.mikhkuts.gamelauncher.main.Main;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SettingWindow {
	private final Game game;
	private final Label gameNameLabel = new Label("Game name");
	private final TextField nameTextField = new TextField();
	private final Label gamePathLabel = new Label("Game path");
	private final TextField pathTextField = new TextField();
	private final Button pathButton = new Button("Change");
	private final Label gameIconLabel = new Label("Game icon");
	private final TextField iconTextField = new TextField();
	private final Button iconButton = new Button("Change");
	private final Button okButton = new Button("OK");
	private final Button cancelButton = new Button("Cancel");
	private final Button deleteButton = new Button("Delete");
	private final AnchorPane topPanel = new TopPanel(false).topPanel;
	
	public SettingWindow(Game game) {
		this.game = game;
		initWindow();
	}
	
	private void initWindow() {
		AnchorPane root = init();
		Scene scene = new Scene(root);
		String stylesheet = "file:./res/style.css";
		scene.getStylesheets().add(stylesheet);
		Stage settingWindow = new Stage();
		settingWindow.setScene(scene);
		settingWindow.setTitle("GameLauncher");
		settingWindow.initStyle(StageStyle.TRANSPARENT);
		settingWindow.initModality(Modality.APPLICATION_MODAL);
		settingWindow.setWidth(500);
		settingWindow.setHeight(300);
		settingWindow.setResizable(false);
		settingWindow.centerOnScreen();
		settingWindow.show();
		settingWindow.setOnCloseRequest(e -> {
			if ((!Objects.equals(game.Name, nameTextField.getText())) &&
				(!game.gamePath.toString().equals(pathTextField.getText())) &&
				(!game.Icon.toString().equals(iconTextField.getText()))
				){
				ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
				ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
				Alert alert = new Alert(Alert.AlertType.WARNING,
						"",
						yesButton,
						noButton);
	        	alert.setTitle("Confirmation Dialog");
	        	alert.setHeaderText("You have unsaved changes. Save them?");
	        	Optional<ButtonType> result = alert.showAndWait();
	        	if (result.isPresent() && result.get() == yesButton) {
	        	    configManage.editListOfGames(game.id, new Game(
						nameTextField.getText(),
						Path.of(pathTextField.getText()),
						Path.of(iconTextField.getText())
						));
	        	    Main.updateListOfGames();
					((Stage) (okButton.getScene().getWindow())).close();
				}
			}
		});
	}
	
	private AnchorPane init(){
		AnchorPane.setRightAnchor(topPanel, 1.0);
		AnchorPane.setTopAnchor(topPanel, 1.0);

		nameTextField.getStyleClass().add("field");
		pathTextField.getStyleClass().add("field");
		iconTextField.getStyleClass().add("field");

        gameNameLabel.setLayoutX(40.0);
        gameNameLabel.setLayoutY(40.0);
        gameNameLabel.setFont(new Font(18.0));

        nameTextField.setText(game.Name);
        nameTextField.setLayoutX(163.0);
        nameTextField.setLayoutY(40.0);
        nameTextField.setPrefHeight(26.0);
        nameTextField.setPrefWidth(308.0);
        
        gamePathLabel.setLayoutX(40.0);
        gamePathLabel.setLayoutY(122.0);
        gamePathLabel.setFont(new Font(18.0));
        
        pathTextField.setText(game.gamePath.toString());
        pathTextField.setLayoutX(163.0);
        pathTextField.setLayoutY(122.0);
        pathTextField.setPrefHeight(26.0);
        pathTextField.setPrefWidth(240.0);
        
        pathButton.setLayoutX(414.0);
        pathButton.setLayoutY(123.0);
        pathButton.setMnemonicParsing(false);
        pathButton.setPrefHeight(26.0);
		pathButton.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Выбор файла");
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Executable files","*.exe"));
			File file = fileChooser.showOpenDialog(null);
			if (file != null) {
				game.gamePath = Path.of(file.toURI());
				pathTextField.setText(game.gamePath.toString());
			}
		});
		pathButton.getStyleClass().add("bbutton");
        
        gameIconLabel.setLayoutX(40.0);
        gameIconLabel.setLayoutY(200.0);
        gameIconLabel.setFont(new Font(18.0));
        
        iconTextField.setText(game.Icon.toString());
        iconTextField.setLayoutX(163.0);
        iconTextField.setLayoutY(200.0);
        iconTextField.setPrefHeight(26.0);
        iconTextField.setPrefWidth(240.0);
        
        iconButton.setLayoutX(414.0);
        iconButton.setLayoutY(200.0);
        iconButton.setMnemonicParsing(false);
        iconButton.setPrefHeight(26.0);
		iconButton.getStyleClass().add("bbutton");
        
        okButton.setLayoutX(301.0);
        okButton.setLayoutY(261.0);
        okButton.setMnemonicParsing(false);
        okButton.setPrefHeight(26.0);
        okButton.setPrefWidth(55.0);
        okButton.setOnAction(e -> {
        	System.out.print(game.id);
        	configManage.editListOfGames(
					game.id,
        			new Game(nameTextField.getText(),
        					Path.of(pathTextField.getText()),
        					Path.of(iconTextField.getText())
        					));
			Main.updateListOfGames();
        	((Stage) (okButton.getScene().getWindow())).close();
        });
		okButton.getStyleClass().add("bbutton");
        
        cancelButton.setLayoutX(364.0);
        cancelButton.setLayoutY(261.0);
        cancelButton.setMnemonicParsing(false);
        cancelButton.setPrefHeight(26.0);
        cancelButton.setPrefWidth(55.0);
        cancelButton.setOnAction(e -> {
        	((Stage) (cancelButton.getScene().getWindow())).close();
        });
		cancelButton.getStyleClass().add("bbutton");
        
        deleteButton.setLayoutX(430.0);
        deleteButton.setLayoutY(261.0);
        deleteButton.setMnemonicParsing(false);
        deleteButton.setPrefHeight(26.0);
        deleteButton.setPrefWidth(55.0);
        deleteButton.setOnAction(e -> {
        	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);  
        	alert.setTitle("Confirmation Dialog");  
        	alert.setHeaderText("Are you sure?");  
        	Optional<ButtonType> result = alert.showAndWait();  
        	if (result.isPresent() && result.get() == ButtonType.OK) {  
        	    configManage.deleteGameFromList(game.id);
				Main.updateListOfGames();
        		((Stage) (okButton.getScene().getWindow())).close();
        	}  
        });
		deleteButton.getStyleClass().add("deletebutton");

        AnchorPane temp = new AnchorPane();
        temp.getChildren().addAll(
				topPanel,
        		gameNameLabel, gamePathLabel, gameIconLabel,
                nameTextField, pathTextField, iconTextField,
                pathButton, iconButton,
                okButton, cancelButton, deleteButton
                );
		temp.setStyle("-fx-border-color: black");
        return temp;
	}
	
}
