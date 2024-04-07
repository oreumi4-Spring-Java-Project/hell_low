package com.est.helllow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping("/mainpage")
	public String mainPage() {
		return "mainpage";
	}

	@GetMapping("/list")
	public String listPage() {
		return "list";
	}

	@GetMapping("/food")
	public String foodPage() {
		return "food";
	}

	@GetMapping("/give")
	public String givePage() {
		return "give";
	}

	@GetMapping("/post")
	public String postPage() {
		return "post";
	}

	@GetMapping("/picture")
	public String picturePage() {
		return "picture";
	}


}

