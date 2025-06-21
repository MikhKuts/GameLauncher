package main;

import ForGL.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.*;

public class Main extends Application {
	private static VBox gamesList = new VBox();
	
	public static void main(String[] args) {
		InitializeGameLauncher.init();
		Application.launch(args);
	}
	
	public void start(Stage stage) throws Exception {
		updateListOfGames();
		String stylesheet = getClass().getResource("/styles.css").toExternalForm();
		
		ScrollPane sc1 = new ScrollPane(gamesList);
		sc1.setPrefHeight(520.0);
		
		VBox baseComponents = new VBox(new TitlePane().pane, sc1);
		
		AnchorPane ap = new AnchorPane(baseComponents);
		ap.getStyleClass().add("root");
		StackPane root = new StackPane(ap);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(stylesheet);
		stage.setScene(scene);
        stage.setTitle("GameLauncher");
        stage.setWidth(647);
        stage.setHeight(640);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

	public static void updateListOfGames() {
		gamesList.getChildren().clear();
		for (Game game :configManage.getListOfGames()) {
			paneOfGames name = new paneOfGames(game);
			gamesList.getChildren().add(name.pane);
			name.settings.setOnAction(e -> {
				new windows.SettingWindow(game);
	        });
		}
	}
}
