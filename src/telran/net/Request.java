package telran.net;

import java.io.Serializable;

public class Request implements Serializable{

	public Request(String type, Serializable data) {
		this.type = type;
		this.data = data;
	}
	private static final long serialVersionUID = 1L;
	public String type;
	public Serializable data;
}
