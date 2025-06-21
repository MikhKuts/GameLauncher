package ForGL;

import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class addFolder extends JFrame{
	
	public ArrayList<Icon> iconFromGames = new ArrayList<Icon>();
	private static ArrayList<Game> searchedGames = new ArrayList<Game>();
	//private static ArrayList<File> foldersForSearch = new ArrayList<File>();
	
	public static void chooseFolder() {
		searchedGames.clear();
		DirectoryChooser folderChooser = new DirectoryChooser();
		folderChooser.setTitle("Выбор директории");
        
        File folder = folderChooser.showDialog(null);
        if (folder != null) {
        	searchGames(folder);	
        }
        configManage.addListOfGames(searchedGames);
        main.Main.updateListOfGames();
        
	}
	public static void chooseFile() {
		searchedGames.clear();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Выбор файла");
        fileChooser.setSelectedExtensionFilter(new ExtensionFilter("Executable files", "*.exe"));
        	File file = fileChooser.showOpenDialog(null);
        	if (file != null) {
        	addGame(file);
        	configManage.addListOfGames(searchedGames);
        	main.Main.updateListOfGames();
        }
	}
	
	private static void searchGames(File dir) {
		for(File item : dir.listFiles()){
		    if(item.isDirectory()){
		        searchGames(item);
		    } else {
		        if(item.getName().endsWith(".exe")) {
		            addGame(item);
		        }
		    }
		}
	}

	public static void addGame(File item) {
		searchedGames.add(
				new Game(
						item.getName().substring(0, item.getName().length()-4),
						item.toPath(), 
						null
					));
	}
	
}
