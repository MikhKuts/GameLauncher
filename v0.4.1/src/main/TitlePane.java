package main;


import ForGL.addFolder;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class TitlePane {
	public Label title = new Label("GameLauncher");
	public MenuButton addGames = new MenuButton("Add Games");
	public AnchorPane pane = new AnchorPane(title, addGames);
	
	public TitlePane() {
		initComponents();
	}

	@SuppressWarnings("static-access")
	private void initComponents() {
		pane.setTopAnchor(title, 12.0);
		pane.setRightAnchor(title, 150.0);
		pane.setLeftAnchor(title, 150.0);
		title.setFont(new Font("System Bold", 48.0));
		title.getStyleClass().add("title");
		title.getStyleClass().add("black-text");
		
		pane.setRightAnchor(addGames, 20.0);
		pane.setTopAnchor(addGames, 20.0);
		pane.getStyleClass().add("black-text");
		
		MenuItem mi1 = new MenuItem("From folder");
		mi1.setOnAction(event -> {
			addFolder.chooseFolder();
		});
		addGames.getItems().add(mi1);
		MenuItem mi2 = new MenuItem("From file");
		mi2.setOnAction(event -> {
			addFolder.chooseFile();
		});
		addGames.getItems().add(mi2);
	}
	
}
