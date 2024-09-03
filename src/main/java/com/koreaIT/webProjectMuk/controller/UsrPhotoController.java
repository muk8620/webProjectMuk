package com.koreaIT.webProjectMuk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreaIT.webProjectMuk.service.PhotoService;

@Controller
public class UsrPhotoController {
	
	private PhotoService photoService;
	
	public UsrPhotoController(PhotoService photoService) {
		this.photoService = photoService;
	}
	
	@GetMapping("/usr/photo/list")
	public String albumTest() {
		return "usr/photo/list";
	}
	
	@GetMapping("/usr/photo/folder")
	@ResponseBody
    public String createFolder() {
		
        return photoService.createFolder();
    }
	
}