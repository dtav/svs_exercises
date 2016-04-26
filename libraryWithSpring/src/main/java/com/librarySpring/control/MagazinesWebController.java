package com.librarySpring.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.librarySpring.domain.Magazine;
import com.librarySpring.service.LibraryService;


@Controller
@RequestMapping("/magazines")
public class MagazinesWebController {
	
	@Autowired
	private LibraryService libraryService2;
	
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
		return libraryService2.getListOfMagazines();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String registerMagazine(@ModelAttribute("magazine") Magazine magazine){
		Magazine magazineObj = new Magazine(magazine.getIssn(), magazine.getTitle());
		if (magazine.getId() == null) {
			System.out.println("id is null");
			libraryService2.registerMagazine(magazineObj);
		} else {
			System.out.println("id is NOT null");
			libraryService2.updateMagazine(magazineObj);
		}
		return "redirect:/magazines";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editEntry(@PathVariable("id") Long id, Model model){
		Magazine magazine = libraryService2.getMagazineByID(id);
		return "redirect:/magazines";
	}

}
