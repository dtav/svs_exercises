package com.svs.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TwitterIndexWebController {

	@RequestMapping("/")
	public String index(){
		return "index";
	}
}
