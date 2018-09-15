package com.coffee.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

	@GetMapping({"/", "/home"})
	public ModelAndView gethome() {
		return super.view("home");
	}
	
	@GetMapping("/about")
	public ModelAndView getAbout() {
		return super.view("about");
	}
}