package io.github.mikhkuts.gamelauncher.main;

import io.github.mikhkuts.gamelauncher.forGL.Game;
import io.github.mikhkuts.gamelauncher.forGL.InitializeGameLauncher;
import io.github.mikhkuts.gamelauncher.forGL.configManage;
import io.github.mikhkuts.gamelauncher.frameElement.TitlePane;
import io.github.mikhkuts.gamelauncher.frameElement.paneOfGames;
import io.github.mikhkuts.gamelauncher.frames.SettingWindow;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.*;

public class Main extends Application {
	private static final VBox gamesList = new VBox();
	
	public static void main(String[] args) {
		InitializeGameLauncher.init();
		Application.launch(args);
	}
	
	public void start(Stage stage) throws Exception {
		updateListOfGames();
		String stylesheet = "file:./res/style.css";

		ScrollPane sc1 = new ScrollPane(gamesList);
		sc1.setPrefHeight(532.0);

		sc1.getStyleClass().add("scrollPane");
		TitlePane tPane = new TitlePane();
		VBox baseComponents = new VBox(tPane.topPanel ,tPane.titlePanel, sc1);
		
		AnchorPane ap = new AnchorPane(baseComponents);
		ap.getStyleClass().add("root");
		StackPane root = new StackPane(ap);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(stylesheet);
		stage.setScene(scene);
		stage.getIcons().add(
				new Image("file:./res/icon.png")
		);
        stage.setTitle("GameLauncher");
        stage.setWidth(634);
        stage.setHeight(640);
        stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.show();
    }

	public static void updateListOfGames() {
		gamesList.getChildren().clear();
		for (Game game : configManage.getListOfGames()) {
			paneOfGames name = new paneOfGames(game);
			gamesList.getChildren().add(name.pane);
			name.settings.setOnAction(e -> {
				new SettingWindow(game);
	        });
		}
	}
}
