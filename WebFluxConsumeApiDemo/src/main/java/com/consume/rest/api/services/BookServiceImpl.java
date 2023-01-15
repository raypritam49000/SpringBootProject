package com.consume.rest.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.consume.rest.api.models.Book;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private WebClient webClient;

	@Override
	public List<Book> findAll() {
		Flux<Book> books = webClient.get().uri("/").retrieve().bodyToFlux(Book.class);
		List<Book> bookList = books.collectList().block();
		return bookList;
	}

	@Override
	public Book findById(int id) {
		Mono<Book> bookMono = webClient.get().uri("/" + id).retrieve().bodyToMono(Book.class);
		Book book = bookMono.block();
		return book;
	}

	@Override
	public Boolean deleteById(int id) {
		Mono<Void> voidMono = webClient.delete().uri("/" + id).retrieve().bodyToMono(Void.class);
		return true;
	}

	@Override
	public Book createBook(Book book) {
		Mono<Book> bookMono = webClient.post().uri("/").body(Mono.just(book), Book.class).retrieve().bodyToMono(Book.class);
		Book createBook = bookMono.block();
		return createBook;
	}

	@Override
	public Book updateBook(int id, Book book) {
		Mono<Book> bookMono = webClient.put().uri("/"+id).body(Mono.just(book), Book.class).retrieve().bodyToMono(Book.class);
		Book updateBook = bookMono.block();
		return updateBook;
	}

}
