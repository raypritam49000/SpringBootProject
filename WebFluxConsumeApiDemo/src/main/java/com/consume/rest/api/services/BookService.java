package com.consume.rest.api.services;

import java.util.List;

import com.consume.rest.api.models.Book;

public interface BookService {
	public List<Book> findAll();
	public Book findById(int id);
	public Boolean deleteById(int id);
	public Book createBook(Book book);
	public Book updateBook(int id,Book book);
}
