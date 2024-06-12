package cl.coopeuch.pruebatecnica.response;

public class ResponseHttp {
	//status
	public final static Boolean STATUS_DONE = true;
	public final static Boolean STATUS_ERROR = false;
	
	private Boolean status;
	private String message;
	
	public ResponseHttp(Boolean status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
