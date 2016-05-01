package com.librarySpring.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.librarySpring.domain.Book;
import com.librarySpring.service.LibraryService;

@RestController
@RequestMapping("/api/books")
public class RestBookController {
	
	@Autowired
	LibraryService ls;

	@RequestMapping(method = RequestMethod.GET)
	public List<Book> listBooks() {

		List<Book> listOfTweets = ls.getListOfBooks();
		
		return listOfTweets;

	}

}
