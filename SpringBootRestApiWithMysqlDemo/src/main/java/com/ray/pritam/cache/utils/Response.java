package com.ray.pritam.cache.utils;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response {
	private String message;
	private Boolean success;
	private String status;
	private Integer statusCode;
	private List<?> body;

	public Response(String message, Boolean success, String status, Integer statusCode, List<?> body) {
		super();
		this.message = message;
		this.success = success;
		this.status = status;
		this.statusCode = statusCode;
		this.body = body;
	}
   

}
