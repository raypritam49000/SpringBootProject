package com.cache.api.utils;

import java.util.List;

public class Response {
	public String status;
	public String statusCode;
	public boolean isSuccess;
	public String message;
	public List<?> data;

	public Response(String status, String statusCode, boolean isSuccess, String message, List<?> data) {
		super();
		this.status = status;
		this.statusCode = statusCode;
		this.isSuccess = isSuccess;
		this.message = message;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Response [status=" + status + ", statusCode=" + statusCode + ", isSuccess=" + isSuccess + ", message="
				+ message + ", data=" + data + "]";
	}

}
