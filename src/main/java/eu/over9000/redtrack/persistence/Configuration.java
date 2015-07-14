package eu.over9000.redtrack.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Simple properties based config.
 */
public class Configuration {
	private static final Path PATH_TO_FILE = Paths.get(System.getProperty("user.home"), ".redtrack", "config.properties");
	private static Properties configFile = new Properties();

	private static void checkDirAndFile() throws IOException {
		Files.createDirectories(PATH_TO_FILE.getParent());
		if (!Files.exists(PATH_TO_FILE)) {
			Files.createFile(PATH_TO_FILE);
		}
	}

	public static void load() {
		try {
			checkDirAndFile();
			InputStream stream = Files.newInputStream(PATH_TO_FILE);
			configFile.load(stream);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void save() {
		try {
			checkDirAndFile();
			OutputStream stream = Files.newOutputStream(PATH_TO_FILE);
			configFile.store(stream, "Redtrack configuration");
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getValue(String key) {
		return configFile.getProperty(key);
	}

	public static void setValue(String key, String value) {
		configFile.setProperty(key, value);
	}
}
