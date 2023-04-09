package telran.io.test;
import java.io.*;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InputOutputStreamTest {
	final static String fileName = "test.txt";
	final static String fileNameCopy = "copyTest.txt";
	String hello = "Hello World";
	@BeforeAll
	static void setUp() throws Exception {
		Files.deleteIfExists(Path.of(fileName));
		Files.deleteIfExists(Path.of(fileNameCopy));
	}
	
	@Order(1)
	@Test
	void OutputStreamTest() throws Exception{
		try(OutputStream output = new FileOutputStream(fileName, true)){
			byte[] helloBytes = hello.getBytes();
			
			output.write(helloBytes);
		}
	}

	@Order(2)
	@Test
	void InputStreamTest() throws Exception{
		readFileTest(fileName);
	}

	private void readFileTest(String fileName) throws IOException, FileNotFoundException {
		try(InputStream input = new FileInputStream(fileName)){
			byte[] buffer = input.readAllBytes();
			String str = new String(buffer);
			
			assertEquals(hello, str);
		}
	}
	
	@Test
	void transferToTest() throws Exception{
		try(InputStream input = new FileInputStream(fileName);
				OutputStream output = new FileOutputStream(fileNameCopy)){
			input.transferTo(output);
		}
	}
	
	@Test
	void copyTest() throws Exception{
		//readFileTest(fileNameCopy);
	}
}
