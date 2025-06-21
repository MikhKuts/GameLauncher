package ForGL;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class configManage {
	private static Path config = Path.of("./config.cnfg");
	private static short count;
	
	public static void init() {
		if (Files.notExists(config)) {
			try {
				Files.createFile(config);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void addListOfGames (ArrayList<Game> searchedGames) {
		boolean hasExistGame = false;
		for(Game game : searchedGames) {
			try { 
				if (Files.readAllLines(config).contains(game.gamePath.toString())) {
					hasExistGame = true;
					continue;
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			ArrayList<String> list = new ArrayList<String>();
			list.add("id");
			list.add("" + (++count));
			list.add("gameName");
			list.add(game.Name);
			list.add("gamePath");
			list.add(game.gamePath.toString());
			list.add("gameIcon");
			if (game.Icon != null)
				list.add(game.Icon.toString());
			else list.add("");
			try {
				Files.write(config, list, StandardOpenOption.APPEND);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (hasExistGame) JOptionPane.showMessageDialog(null, "Некоторые объекты могли быть не добавлены, так как они могут присутсвовать в списке", "", JOptionPane.WARNING_MESSAGE);
	}
	public static ArrayList<Game> getListOfGames () {
		ArrayList<Game> tempList = new ArrayList<Game>();
		 try {
	            List<String> list = Files.readAllLines(config);
	            count = 0;
	            
	            for(int i = 0; i < list.size(); i++) {
	            	if(i >= list.size()) break;
	            	if((list.get(i).length() != 2) && (list.get(i).length() <= 4)) continue;
	                Game tempGame = new Game();
	                if ((list.get(i).charAt(0) == 'i') && (list.get(i).charAt(1) == 'd')) {
	                	tempList.add(tempGame);
	                	tempList.get(count).id = Short.parseShort(list.get(i+1));
	                }
	                else if ((list.get(i).charAt(0) == 'g') && (list.get(i).charAt(4) == 'N'))
	                	tempList.get(count).Name = list.get(i+1);
	                else if ((list.get(i).charAt(0) == 'g') && (list.get(i).charAt(4) == 'P'))
	                	tempList.get(count).gamePath = Path.of(list.get(i+1));
	                else if ((list.get(i).charAt(0) == 'g') && (list.get(i).charAt(4) == 'I')) {
	                	tempList.get(count).Icon = Path.of(list.get(i+1));
	                	count++;
	                }
	                else continue;
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		return tempList;
	}
	public static void editListOfGames(Game game) {
		try {
			List<String> fileContent = new ArrayList<>(Files.readAllLines(config));
			for (int i = 0; i < fileContent.size(); i++) {
				if (fileContent.get(i).contains(game.id + "")) {
					fileContent.set(i+2, game.Name);
					System.out.println(game.Name);
					fileContent.set(i+4, game.gamePath.toString());
					System.out.println(game.gamePath.toString());
					fileContent.set(i+6, game.Icon.toString());
					System.out.println(game.Icon.toString());
					break;
				}
			}
			Files.write(config, fileContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void deleteGameFromList(short id) {
		try {
			List<String> fileContent = new ArrayList<>(Files.readAllLines(config));
			for (int i = 0; i < fileContent.size(); i++) {
				if (fileContent.get(i).contains(id + "")) {
					i--;
					for(int k = 0; k < 8; k++) {
						fileContent.remove(i);
					}
					break;
				}
			}
			Files.write(config, fileContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}