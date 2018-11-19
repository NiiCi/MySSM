package com.nc.entity;

import java.util.Map;

public class UploadResult {
	private int okCount;
	private int errCount;
	private Map<Integer,String> errors;
	public int getOkCount() {
		return okCount;
	}
	public void setOkCount(int okCount) {
		this.okCount = okCount;
	}
	public int getErrCount() {
		return errCount;
	}
	public void setErrCount(int errCount) {
		this.errCount = errCount;
	}
	public Map<Integer, String> getErrors() {
		return errors;
	}
	public void setErrors(Map<Integer, String> errors) {
		this.errors = errors;
	}
	
}
