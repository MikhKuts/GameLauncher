package io.github.mikhkuts.gamelauncher.forGL;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class configManage {
	private static final Path gamesList = Path.of("./res/gamelist.json");
	private static short count = 0;
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static void init() {
		if (Files.notExists(gamesList)) {
			try {
				Files.createFile(gamesList);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void addListOfGames (ArrayList<Game> searchedGames){
		boolean hasExistGame = false;
		ArrayList<Game> tempGames = getListOfGames();
		ArrayList<String> temp = new ArrayList<>();
		for(Game game : searchedGames) {
			boolean containGame = tempGames.stream().anyMatch(Game -> Game.getGamePath().equals(game.getGamePath()));
			if (containGame) {
				hasExistGame = true;
				continue;
			}
			game.id = count;
			if (game.Icon == null) game.Icon = Path.of("/default");
			try {
				temp.add(toJSON(game));
			}catch (Exception e){
				e.printStackTrace();
			}
			count++;
		}
		try {
			Files.write(gamesList, temp, StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (hasExistGame) JOptionPane.showMessageDialog(null,
				"Некоторые объекты не добавлены, так как они присутствуют в списке",
				"",
				JOptionPane.WARNING_MESSAGE);
	}
	public static ArrayList<Game> getListOfGames () {
		ArrayList<Game> tempList = new ArrayList<>();
		try {
			List<String> list = Files.readAllLines(gamesList);
			for(String str : list){
				Game game = objectMapper.readValue(str, Game.class);
				tempList.add(game);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		if(!tempList.isEmpty())count = (short)(tempList.getLast().id + Short.parseShort("1"));
		return tempList;
	}
	public static void editListOfGames(short id, Game game) {
		game.id = id;
		try {
			ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(gamesList));
			for (int i = 0; i < fileContent.size(); i++) {
				if (fileContent.get(i).contains("\"id\":" + id)) {
					fileContent.set(i, toJSON(game));
				}
			}
			Files.write(gamesList, fileContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void deleteGameFromList(short id) {
		try {
			List<String> fileContent = new ArrayList<>(Files.readAllLines(gamesList));
			for (int i = 0; i < fileContent.size(); i++) {
				if (fileContent.get(i).contains(id + "")) {
					fileContent.remove(i);
					break;
				}
			}
			Files.write(gamesList, fileContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String toJSON(Game game) throws JsonProcessingException {
		return objectMapper.writeValueAsString(game);
	}
}