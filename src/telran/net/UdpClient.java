package telran.net;

import java.io.*;
import java.net.*;

import static telran.net.UdpUtills.*;

public class UdpClient implements NetworkClient  {
	
	public UdpClient(String host, int port) {
		this.host = host;
		this.port = port;
		
		try {
			socket = new DatagramSocket();
		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		}
	}

	private String host; 
	private DatagramSocket socket;
	private int port;
	
	@Override
	public void close() throws IOException {
		socket.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T send(String type, Serializable requestData) {
		Request request = new Request(type, requestData);
		try {
			byte[] bufferSend = getBaitArray(request);
			byte[] bufferReceive = new byte[MAX_BUFFER_LENGTH];
			DatagramPacket packetSend = new DatagramPacket(bufferSend, bufferSend.length, InetAddress.getByName(host), port);
			DatagramPacket packetReceive = new DatagramPacket(bufferReceive, MAX_BUFFER_LENGTH);
			socket.send(packetSend);
			socket.receive(packetReceive);
			Response response = (Response) toSerializable(packetReceive.getData(), packetReceive.getLength());
			if(response.code != ResponseCode.OK) {
				throw new Exception(response.data.toString());
			}

			return (T) response.data;
		} catch (Exception e) {
			throw new RuntimeException(e.toString());
		}
		
	}
}
