package br.com.bookleweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	@RequestMapping(value = "/admin")
	public String executeAdmin(){
		return "/admin/home";
	}
	
}
