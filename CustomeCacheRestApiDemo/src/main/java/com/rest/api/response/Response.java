package com.rest.api.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
	private String message;
	private String status;
	private int statusCode;
	private Boolean isSuccess;
	private List<?> data;
}
