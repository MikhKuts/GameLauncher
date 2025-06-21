package main;

import java.nio.file.Path;
import java.util.Optional;

import ForGL.Game;
import ForGL.configManage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SettingWindow {
	private Game game;
	private Label gameNameLabel = new Label("Game name");
	private TextField nameTextField = new TextField();
	private Label gamePathLabel = new Label("Game path");
	private TextField pathTextField = new TextField();
	private Button pathButton = new Button();
	private Label gameIconLabel = new Label("Game icon");
	private TextField iconTextField = new TextField();
	private Button iconButton = new Button();
	private Button okButton = new Button("OK");
	private Button cancelButton = new Button("Cancel");
	private Button deleteButton = new Button("Delete");
	
	public SettingWindow(Game game) {
		this.game = game;
		initWindow();
	}
	
	private void initWindow() {
		AnchorPane root = init();
		Scene scene = new Scene(root);
		Stage settingWindow = new Stage();
		settingWindow.setScene(scene);
		settingWindow.setTitle("GameLauncher");
		settingWindow.initModality(Modality.APPLICATION_MODAL);
		settingWindow.setWidth(500);
		settingWindow.setHeight(350);
		settingWindow.setResizable(false);
		settingWindow.centerOnScreen();
		settingWindow.show();
		settingWindow.setOnCloseRequest(e -> {
			if ((game.Name != nameTextField.getText()) &&
				(game.gamePath.toString() != pathTextField.getText()) &&
				(game.Icon.toString() != iconTextField.getText())
				){
				System.out.println(game.Name != nameTextField.getText());
				System.out.println(game.gamePath.toString() != pathTextField.getText());
				System.out.println(game.Icon.toString() != iconTextField.getText());
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);  
	        	alert.setTitle("Confirmation Dialog");  
	        	alert.setHeaderText("Are you sure?");  
	        	Optional<ButtonType> result = alert.showAndWait();  
	        	if (result.isPresent() && result.get() == ButtonType.OK) {  
	        	    configManage.editListOfGames(new Game(
						game.id,
						nameTextField.getText(),
						Path.of(pathTextField.getText()),
						Path.of(iconTextField.getText())
						));
	        	    Main.updateListOfGames();
	        		((Stage) (okButton.getScene().getWindow())).close();
	        	} else {
	        		
	        	}
			}
		});
	}
	
	private AnchorPane init(){
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
        pathTextField.setPrefWidth(275.0);
        
        pathButton.setLayoutX(445.0);
        pathButton.setLayoutY(123.0);
        pathButton.setMnemonicParsing(false);
        pathButton.setPrefHeight(26.0);
        pathButton.setPrefWidth(25.0);
        
        gameIconLabel.setLayoutX(40.0);
        gameIconLabel.setLayoutY(200.0);
        gameIconLabel.setFont(new Font(18.0));
        
        iconTextField.setText(game.Icon.toString());
        iconTextField.setLayoutX(163.0);
        iconTextField.setLayoutY(200.0);
        iconTextField.setPrefHeight(26.0);
        iconTextField.setPrefWidth(275.0);
        
        iconButton.setLayoutX(445.0);
        iconButton.setLayoutY(200.0);
        iconButton.setMnemonicParsing(false);
        iconButton.setPrefHeight(26.0);
        iconButton.setPrefWidth(25.0);
        
        okButton.setLayoutX(301.0);
        okButton.setLayoutY(261.0);
        okButton.setMnemonicParsing(false);
        okButton.setPrefHeight(26.0);
        okButton.setPrefWidth(55.0);
        okButton.setOnAction(e -> {
        	System.out.print(game.id);
        	configManage.editListOfGames(
        			new Game(
        					game.id,
        					nameTextField.getText(),
        					Path.of(pathTextField.getText()),
        					Path.of(iconTextField.getText())
        					));
        	Main.updateListOfGames();
        	((Stage) (okButton.getScene().getWindow())).close();
        });
        
        cancelButton.setLayoutX(364.0);
        cancelButton.setLayoutY(261.0);
        cancelButton.setMnemonicParsing(false);
        cancelButton.setPrefHeight(26.0);
        cancelButton.setPrefWidth(55.0);
        cancelButton.setOnAction(e -> {
        	((Stage) (cancelButton.getScene().getWindow())).close();
        });
        
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
        
        AnchorPane temp = new AnchorPane();
        temp.getChildren().addAll(
        		gameNameLabel, gamePathLabel, gameIconLabel,
                nameTextField, pathTextField, iconTextField,
                pathButton, iconButton,
                okButton, cancelButton, deleteButton
                );
        return temp;
	}
	
}
