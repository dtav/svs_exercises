package com.librarySpring.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.librarySpring.domain.Magazine;
import com.librarySpring.service.LibraryService;

@RestController
@RequestMapping("/api/magazines")
public class RestMagazineController {
	
	@Autowired
	LibraryService ls;

	@RequestMapping(method = RequestMethod.GET)
	public List<Magazine> listMagazines() {

		List<Magazine> listOfMagazines = ls.getListOfMagazines();
		
		return listOfMagazines;

	}

}
