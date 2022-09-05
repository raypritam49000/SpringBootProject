package com.consume.rest.api.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consume.rest.api.models.Book;
import com.consume.rest.api.services.BookService;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
	
	@Autowired
	private BookService bookService;

	@GetMapping
	public List<Book> getAllBooks(){
		return bookService.findAll();
	}
	
	@GetMapping("/{id}")
	public Book getBook(@PathVariable("id") Integer id){
		return bookService.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public Map<?,?> deleteBook(@PathVariable("id") Integer id){
		return Map.of("success",bookService.deleteById(id));
	}
	
	@PostMapping("/")
	public Book getBook(@RequestBody Book book){
		return bookService.createBook(book);
	}
	
	@PutMapping("/{id}")
	public Book updateBook(@PathVariable("id") Integer id,@RequestBody Book book){
		return bookService.updateBook(id, book);
	}
}
