package com.rest.api.dataloader;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.rest.api.entity.Book;
import com.rest.api.repository.BookRepository;

@Component
public class SaveBooksRunner implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepo;

	@Override
	public void run(String... args) throws Exception {

		bookRepo.saveAll(List.of(new Book(500, "Core Java", 200, "Kathy Sierra", 1065.5),
				new Book(501, "JSP & Servlets", 350, "Kathy Sierra", 1749.0),
				new Book(502, "Spring in Action", 480, "Craig Walls", 940.75),
				new Book(503, "Pro Angular", 260, "Freeman", 1949.25),
				new Book(504, "HTML CSS", 100, "Thomas Powell", 2317.09),
				new Book(505, "Hibernate in Action", 180, "Gavin King", 889.25),
				new Book(506, "Practical MongoDB", 180, "Shakuntala Gupta", 785.0),
				new Book(507, "Pro Spring Boot", 850, "Felipe Gutierrez", 2167.99),
				new Book(508, "Beginning jQuery", 180, "Franklin", 1500.00),
				new Book(509, "Java Design Patterns", 114, "Devendra Singh", 919.99)

		));

		System.out.println("------All records has been saved successfully-------");

		Optional<Book> opt = bookRepo.getBookById(504);
		if (opt.isPresent()) {
			System.out.println(opt.get());
		} else {
			System.out.println("DATA NOT FOUND");
		}
		
		bookRepo.getBooksByAuthor("Kathy Sierra").forEach(System.out::println);
		
		bookRepo.getBooksByAuthorAndCost("Freeman",1949.25).forEach(System.out::println);
		
		bookRepo.getBooksByAuthorOrName("Kathy Sierra"," ").forEach(System.out::println);

		bookRepo.getBooksByAuthorOrName("Freeman","Spring in Action").forEach(System.out::println);
		
		Integer count = bookRepo.getBooksCountByAuthor("Kathy Sierra");
		System.out.println(count);
		
		bookRepo.getBooksByAuthorSortByName("Kathy Sierra").forEach(System.out::println);
		
		bookRepo.getBookNameAndAuthorByPages(180).forEach(System.out::println);
		
		bookRepo.getAllBooksByAuthor("Kathy Sierra").forEach(System.out::println);
		
		bookRepo.getBooksByAuthorRegEx("^S").forEach(System.out::println);
		
		bookRepo.getBooksByAuthorRegEx("man$").forEach(System.out::println);
		
		bookRepo.getBooksByAuthorRegEx("S").forEach(System.out::println);

	}

}