package com.rest.api.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Books")
public class Book {

	@Id
	private Integer id;

	private String name;
	private Integer pages;
	private String author;
	private Double cost;
}