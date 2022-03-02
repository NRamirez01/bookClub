package com.nathanielramirez.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nathanielramirez.bookclub.models.Book;
import com.nathanielramirez.bookclub.models.User;
import com.nathanielramirez.bookclub.repositories.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private UserService userService;
	
	public List<Book> allBooks() {
		return bookRepository.findAll();
	}
	
	public Book createBook(Book b) {
		return bookRepository.save(b);
	}
	
	public String deleteBook(String title) {
			return bookRepository.deleteByTitle(title);
		}
	
	public void deleteId(Long id) {
		bookRepository.deleteById(id);
	}
	
	public Book findBook(Long id) {
		Optional<Book> book = bookRepository.findById(id);
		if(book.isPresent()) {
			return book.get();
		} else {
			return null;
		}
	}
}
