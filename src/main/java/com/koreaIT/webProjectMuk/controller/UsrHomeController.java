package com.koreaIT.webProjectMuk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsrHomeController {
	
	@GetMapping("/usr/home/main")
	public String showMain() {
		return "usr/home/main";
	}
	
	@GetMapping("/")
	public String showRoot() {
		return "redirect:/usr/home/main";
	}
	
	@GetMapping("/usr/home/popUp")
	public String popUp() {
		return "usr/home/popUp";
	}
	
	@GetMapping("/usr/home/apiTest1")
	public String apiTest1() {
		return "usr/home/apiTest1";
	}
	@GetMapping("/usr/home/apiTest2")
	public String apiTest2() {
		return "usr/home/apiTest2";
	}
}