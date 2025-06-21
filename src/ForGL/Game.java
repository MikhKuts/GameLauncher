package ForGL;

import java.awt.Desktop;
import java.io.IOException;
import java.nio.file.*;

public class Game {
	public short id;
	public String Name;
	public Path Icon;
	public Path gamePath;
	
	public Game(){
		
	}
	public Game(short id ,String Name, Path gamePath, Path Icon){
		this.id = id;
		this.Name = Name;
		this.Icon = Icon;
		this.gamePath = gamePath;
	}
	public Game(String Name, Path gamePath, Path Icon){
		this.Name = Name;
		this.Icon = Icon;
		this.gamePath = gamePath;
	}
	public void launch() {
		try {
			Desktop.getDesktop().open(gamePath.toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void launch(Path gamePath) {
		try {
			Desktop.getDesktop().open(gamePath.toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
