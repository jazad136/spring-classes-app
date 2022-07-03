package com.jsaddlercs.classapp.response;

public class PingResponse {
	private boolean success;
	
	public PingResponse() { } 
	
	public PingResponse(boolean successVal) { success = successVal; } 
	
	public boolean getSuccess() { return success; }
	
	@Override
	public String toString() {
		return "PingResponse [success=" + success + "]";
	}
}
