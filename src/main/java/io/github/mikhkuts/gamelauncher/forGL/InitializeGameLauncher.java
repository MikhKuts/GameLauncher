package io.github.mikhkuts.gamelauncher.forGL;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InitializeGameLauncher {
	public static final Path resFolder = Path.of("./res");

	public static void init() {
		if (Files.notExists(resFolder)) {
			try {
				Files.createDirectory(resFolder);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		configManage.init();
	}
}
