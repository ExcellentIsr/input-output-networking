package telran.net;

import java.io.Serializable;

public class Response implements Serializable {

	public Response(ResponseCode code, Serializable data) {
		super();
		this.code = code;
		this.data = data;
	}
	
	private static final long serialVersionUID = 1L;
	public ResponseCode code;
	public Serializable data;
}
