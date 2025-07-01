package io.github.mikhkuts.gamelauncher.frameElement;


import io.github.mikhkuts.gamelauncher.forGL.addFolder;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class TitlePane {
	public AnchorPane topPanel = new TopPanel(true).topPanel;

	private final Label title = new Label("GameLauncher");
	private final MenuButton addGames = new MenuButton("Add Games");
	public AnchorPane titlePanel = new AnchorPane(title, addGames);

	public TitlePane() {
		initComponents();
	}

	private void initComponents() {

		AnchorPane.setTopAnchor(title, 12.0);
		AnchorPane.setRightAnchor(title, 150.0);
		AnchorPane.setLeftAnchor(title, 150.0);
		title.setFont(new Font("System Bold", 48.0));
		title.getStyleClass().add("title");
		title.getStyleClass().add("black-text");

		AnchorPane.setRightAnchor(addGames, 20.0);
		AnchorPane.setTopAnchor(addGames, 20.0);
		titlePanel.getStyleClass().add("black-text");

		addGames.getStyleClass().add("bbutton");
		MenuItem mi1 = new MenuItem("From folder");
		mi1.setOnAction(event -> {
			addFolder.chooseFolder();
		});
		MenuItem mi2 = new MenuItem("From file");
		mi2.setOnAction(event -> {
			addFolder.chooseFile();
		});
		addGames.getItems().addAll(mi1, mi2);
	}
	
}
