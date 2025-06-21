package main;

import java.nio.file.Path;

import ForGL.Game;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class paneOfGames{
	
	public ImageView iconOfGame = new ImageView();
	public Label nameOfGame = new Label();
	public Label pathOfGameLabel = new Label();
	public Button playGame = new Button();
	public Button settings = new Button();
	public  Pane pane = new Pane(iconOfGame,
			nameOfGame,
			pathOfGameLabel,
			playGame,
			settings);
	public Path pathOfGame;
	
	public paneOfGames(Game game) {
        this.pathOfGame = game.gamePath;
        initComponents(game);
    }
    
    private void initComponents(Game game) {
        iconOfGame.setFitHeight(50.0);
        iconOfGame.setFitWidth(50.0);
        iconOfGame.setLayoutX(14.0);
        iconOfGame.setLayoutY(11.0);
        iconOfGame.setPickOnBounds(true);
        iconOfGame.setPreserveRatio(true);
        
        nameOfGame.setText(game.Name);
        nameOfGame.setLayoutX(112.0);
        nameOfGame.setLayoutY(7.0);
        nameOfGame.setPrefHeight(62.0);
        nameOfGame.setPrefWidth(366.0);
        nameOfGame.setFont(new Font(20.0));
        
        pathOfGameLabel.setText(game.gamePath.toString());
        pathOfGameLabel.setLayoutX(112);
        pathOfGameLabel.setLayoutY(45);
        pathOfGameLabel.setPrefHeight(30.0);
        pathOfGameLabel.setPrefWidth(360.0);
        
        playGame.setText("Play");
        playGame.setId("play_button");
        playGame.setAlignment(Pos.CENTER);
        playGame.setContentDisplay(ContentDisplay.CENTER);
        playGame.setLayoutX(499.0);
        playGame.setLayoutY(17.0);
        playGame.setMnemonicParsing(false);
        playGame.setTextFill(Color.WHITE);
        playGame.setFont(Font.font(18.0));
        playGame.setOnAction(e -> {
            Game.launch(this.pathOfGame);
        });
        
        settings.setText("a");
        settings.setAlignment(Pos.CENTER);
        settings.setContentDisplay(ContentDisplay.CENTER);
        settings.setLayoutX(580.0);
        settings.setLayoutY(17.0);
        settings.setMnemonicParsing(false);
        settings.setTextFill(Color.WHITE);
        settings.setFont(Font.font(18.0));
        
        
        pane.setBorder(new Border(new BorderStroke(
            Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1.0))));
        pane.setPrefWidth(618);
    }
}
