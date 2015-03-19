package garret.opossum;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpossumResponse {
	
	private int code;
	private String status;
	private Object data;
	
	public OpossumResponse(int code,String status, Object data){
		this.setCode(code);
		this.setData(data);
		this.setStatus(status);
	}

	@JsonProperty
	public int getCode() {
		return code;
	}

	@JsonProperty
	public void setCode(int code) {
		this.code = code;
	}

	@JsonProperty
	public String getStatus() {
		return status;
	}

	@JsonProperty
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty
	public Object getData() {
		return data;
	}

	@JsonProperty
	public void setData(Object data) {
		this.data = data;
	}
	
	public static OpossumResponse getOk(Object data){
		return new OpossumResponse(200,"OK",data);
	}
	
	public static OpossumResponse getError(String message){
		return new OpossumResponse(400,"ERROR",message);
	}

}
