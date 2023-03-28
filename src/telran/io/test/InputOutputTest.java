package telran.io.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class InputOutputTest {
	String fileName = "myFile";
	String directoryName = "myDirectory1";

	@BeforeEach
	void setUp() throws Exception {
		new File(fileName).delete();
		new File(directoryName).delete();
	}

	@Test
	@Disabled
	void fileTest() throws IOException {
		File f1 = new File(fileName);
		assertTrue(f1.createNewFile());
		assertTrue(f1.exists());
		File dir1 = new File(directoryName);
		assertTrue(dir1.mkdirs());
		System.out.println(dir1);
	}

	@Test
	@Disabled
	void filesTest() throws IOException {
		Path path = Path.of("D:\\GameCenter");
		System.out.println(path.toAbsolutePath());
	}

	@Test
	void printDirectoryFileTest() {
		printDirectoryFile(Path.of("."), 0);
	}

	void printDirectoryFile(Path path, int maxLevel) {
		File file = new File(path.getFileName().toString());
		if (!file.isDirectory()) {
			throw new IllegalArgumentException();
		}
		if (maxLevel < 1) {
			maxLevel = Integer.MAX_VALUE;
		}

		printDirContain(file, maxLevel, 0);
		System.out.println("------------------------------------------------");
	}

	private void printDirContain(File file, int maxLevel, int currentLevel) {
		System.out.printf("%s%5s - %s\n", getOffset(currentLevel), "DIR",
				getFileName(file.toPath().toAbsolutePath().normalize()));

		File[] files = file.listFiles();
		if (files != null) {
			for (File item : files) {
				if (item.isDirectory()) {
					if (maxLevel < 1 || currentLevel < maxLevel) {
						printDirContain(item, maxLevel, currentLevel + 1);
					} else {
						System.out.printf("%s%5s - %s\n", getOffset(currentLevel + 1), "DIR", item.getName());
					}
				} else {
					System.out.printf("%s%5s - %s\n", getOffset(currentLevel + 1), "FILE", item.getName());
				}
			}
		}
	}

	private String getOffset(int level) {
		return "=".repeat(level * 2) + ">|";
	}

	@Test
//	@Disabled
	void printDirectoryFilesTest() throws Exception {
		printDirectoryFiles(Path.of("."), 0);
	}

	void printDirectoryFiles(Path path, int maxLevel) throws IOException {
		if (!Files.isDirectory(path)) {
			throw new IllegalArgumentException();
		}
		if (maxLevel < 1) {
			maxLevel = Integer.MAX_VALUE;
		}

		Files.walk(path.toAbsolutePath().normalize(), maxLevel).forEach(
				item -> System.out.printf("%s%5s - %s\n", getOffset(item), getPathType(item), getFileName(item)));
	}

	private String getFileName(Path item) {
		return item.getFileName().toString();
	}

	private String getPathType(Path item) {
		return Files.isDirectory(item) ? " DIR" : " FILE";
	}

	private String getOffset(Path path) {
		return "=".repeat((path.getNameCount() - 2) * 2) + ">|";
	}
}
