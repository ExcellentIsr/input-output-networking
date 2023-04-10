package telran.net.application;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.*;
import telran.net.*;

class TcpExampleTest {
	
static NetworkClient client;

	@BeforeAll
	static void connection() throws Exception {
		client = new UdpClient("localhost", 4000);
	}

	@Test
	void test() {
		assertEquals("olleH", client.send("reverse", "Hello"));
		assertEquals((Integer) 5, client.send("length", "Hello"));
	}
	
	@AfterAll
	//@Disabled
	static void disconnection() throws IOException {
		client.close();
	}

}