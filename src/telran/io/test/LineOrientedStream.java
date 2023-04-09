package telran.io.test;

import java.io.*;

import org.junit.jupiter.api.Test;

public class LineOrientedStream {
	final static String fileNamePrintStream = "linesStream.txt";
	final static String fileNamePrintWriter = "linesWriter.txt";
	final static String line = "Hello World";
	
	@Test
	void printStreamTest() throws Exception {
		PrintStream printStream = new PrintStream(fileNamePrintStream);
		printStream.println(line);
	}
	
	@Test
	void printWriterTest() throws Exception {
		try(PrintWriter printWriter = new PrintWriter(fileNamePrintWriter)) {
			printWriter.println(line);
		}
	}
}
