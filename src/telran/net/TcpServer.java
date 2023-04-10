package telran.net;

import java.io.IOException;
import java.net.*;

public class TcpServer implements Runnable {
	
	public TcpServer(Protocol protocol, int port) throws Exception{
		this.protocol = protocol;
		this.port = port;
		serverSocket = new ServerSocket(port);
	}
	
	private ServerSocket serverSocket;
	private Protocol protocol;
	private int port;
	
	@Override
	public void run() {
		System.out.println("Server listening in port " + this.port);
		while(true) {
			try {
				Socket socket = serverSocket.accept();
				TcpServerClient serverCLient = new TcpServerClient(socket, protocol);
				serverCLient.run();
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}
	}
}
