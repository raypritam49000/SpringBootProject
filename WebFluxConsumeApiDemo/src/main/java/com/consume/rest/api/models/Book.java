package com.consume.rest.api.models;

public class Book {
	private Integer userId;
	private Integer id;
	private String title;
	private Boolean completed;

	public Book() {
		super();
	}

	public Book(Integer userId, Integer id, String title, Boolean completed) {
		super();
		this.userId = userId;
		this.id = id;
		this.title = title;
		this.completed = completed;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	@Override
	public String toString() {
		return "Book [userId=" + userId + ", id=" + id + ", title=" + title + ", completed=" + completed + "]";
	}

}