package io.github.mikhkuts.gamelauncher.frames;

import java.util.ArrayList;

import io.github.mikhkuts.gamelauncher.forGL.*;
import io.github.mikhkuts.gamelauncher.main.Main;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.*;

public class ChooseWindow {
	private final ScrollPane scrollPane = new ScrollPane();
	private final AnchorPane scrollContent = new AnchorPane();
	private final VBox vbox = new VBox();
	private final Button finishButton = new Button("Finish");
	private final Button cancelButton = new Button("Cancel");
	private final CheckBox selectAllCheckBox = new CheckBox("Select all");
	private final Label instructionLabel = new Label("Select the file to be added.");
	private final ArrayList<CheckBox> selectGames = new ArrayList<CheckBox>();
	private ArrayList<Game> games;

	public ChooseWindow(ArrayList<Game> searchedGames) {
		this.games = searchedGames;
		initWindow();
	}

	public void initWindow() {
		AnchorPane root = init();
        root.setPrefHeight(400.0);
        root.setPrefWidth(400.0);

        Scene scene = new Scene(root);
        Stage chooseWindow = new Stage();
        chooseWindow.initModality(Modality.APPLICATION_MODAL);
		chooseWindow.setWidth(435);
		chooseWindow.setHeight(450);
		chooseWindow.setResizable(false);
		chooseWindow.centerOnScreen();
		chooseWindow.show();
        chooseWindow.setScene(scene);
        chooseWindow.show();
    }
	
	private AnchorPane init() {

		scrollPane.setLayoutX(10.0);
        scrollPane.setLayoutY(100.0);
        scrollPane.setPrefHeight(250.0);
        scrollPane.setPrefWidth(400.0);
        scrollPane.getStyleClass().add("scrollPane");

        scrollContent.setMinHeight(0.0);
        scrollContent.setMinWidth(0.0);

        //vbox.setPrefWidth(380.0);

        for (Game game : games) {
        	selectGames.add(checkBox(game));
        	vbox.getChildren().add(selectGames.getLast());
		}

        scrollContent.getChildren().add(vbox);
        scrollPane.setContent(scrollContent);

        finishButton.setId("black-text");
        finishButton.setLayoutX(323.0);
        finishButton.setLayoutY(361.0);
        finishButton.setMnemonicParsing(false);
        finishButton.setPrefHeight(26.0);
        finishButton.setPrefWidth(60.0);
        finishButton.setOnAction(e -> {
        	ArrayList<Game> temp = new ArrayList<Game>();
        	for (int i = 0; i < vbox.getChildren().size(); i++) {
        		if (selectGames.get(i).isSelected())
        			temp.add(games.get(i));
			}
        	configManage.addListOfGames(temp);
        	Main.updateListOfGames();
        	((Stage) (finishButton.getScene().getWindow())).close();
        });

        cancelButton.setId("black-text");
        cancelButton.setLayoutX(263.0);
        cancelButton.setLayoutY(361.0);
        cancelButton.setMnemonicParsing(false);
        cancelButton.setOnAction(e -> {
        	((Stage) (cancelButton.getScene().getWindow())).close();
        });

        selectAllCheckBox.setId("black-text");
        selectAllCheckBox.setLayoutX(14.0);
        selectAllCheckBox.setLayoutY(365.0);
        selectAllCheckBox.setMnemonicParsing(false);
        selectAllCheckBox.setOnAction(e -> {
        	for (CheckBox checkboxes : selectGames) {
        		if (selectAllCheckBox.isSelected()) checkboxes.setSelected(true);
        		else checkboxes.setSelected(false);
			}
        });

        instructionLabel.setId("black-text");
        instructionLabel.setLayoutX(14.0);
        instructionLabel.setLayoutY(25.0);
        instructionLabel.setPrefHeight(50.0);
        instructionLabel.setPrefWidth(225.0);
        instructionLabel.setFont(new Font(18.0));

        AnchorPane temp = new AnchorPane();
        temp.getChildren().addAll(
            scrollPane,
            finishButton,
            cancelButton,
            selectAllCheckBox,
            instructionLabel
        );
		return temp;
	}
	
	private CheckBox checkBox(Game game) {
		CheckBox checkBox = new CheckBox(game.gamePath.toString());
        checkBox.setMnemonicParsing(false);
        checkBox.setPrefHeight(30.0);
        checkBox.setMinWidth(400.0);
        checkBox.setPadding(new Insets(0, 0, 0, 5.0));
		return checkBox;
	}
	
}
