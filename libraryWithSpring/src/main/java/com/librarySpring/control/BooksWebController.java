package com.librarySpring.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.librarySpring.domain.Book;
import com.librarySpring.service.LibraryService;
import com.librarySpring.util.Logger;

@Controller
@RequestMapping("/books")
public class BooksWebController {

	@Autowired
	private LibraryService libraryService;

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "books";
	}

	@ModelAttribute("book")
	public Book book() {
		return new Book();
	}

	@ModelAttribute("books")
	public List<Book> books() {
		return libraryService.getListOfBooks();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registerBook(@ModelAttribute("book") Book book) {
		Book bookObj = new Book(book.getIsbn(), book.getTitle());
		if (book.getId() == null) {
			System.out.println("id is null");
			libraryService.registerBook(bookObj);
		} else {
			System.out.println("id is NOT null");
			libraryService.updateBook(bookObj);
		}
		return "redirect:/books";
	}

	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	public String deleteEntry(@PathVariable("id") long id) {

		Logger.log("DEBUG IN");
		libraryService.unregisterBook(id);
		return "redirect:/books";
	}

}
