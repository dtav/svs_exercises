package com.librarySpring.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.librarySpring.domain.Magazine;
import com.librarySpring.service.LibraryService;
import com.librarySpring.util.Logger;


@Controller
@RequestMapping("/magazines")
public class MagazinesWebController {
	
	@Autowired
	private LibraryService libraryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "magazines";
	}
	
	@ModelAttribute("magazine")
	public Magazine magazine(){
		return new Magazine();		
	}
	
	@ModelAttribute("magazines")
	public List<Magazine> magazines(){
		return libraryService.getListOfMagazines();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String registerMagazine(@ModelAttribute("magazine") Magazine magazine){
		Magazine magazineObj = new Magazine(magazine.getIssn(), magazine.getTitle());
		if (magazine.getId() == null) {
			System.out.println("id is null");
			libraryService.registerMagazine(magazineObj);
		} else {
			System.out.println("id is NOT null");
			libraryService.updateMagazine(magazineObj);
		}
		return "redirect:/magazines";
	}
	
	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	public String deleteEntry(@PathVariable("id") long id) {

		Logger.log("DEBUG IN MAG");
		libraryService.unregisterMagazine(id);
		return "redirect:/magazines";
	}

}
