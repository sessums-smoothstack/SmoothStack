package com.smoothstack.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.app.model.Author;
import com.smoothstack.app.service.AuthorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;


@RestController
public class AuthorController {
	
	@Autowired
	AuthorService authorService;
	
	@RequestMapping(path = "/lms/authors/{authorId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public Author getAuthorById(@PathVariable int authorId) {
		
		return authorService.getAuthorById(authorId);
		
	}
	
	@RequestMapping(path = "/lms/author", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public void addAuthor(@RequestBody Author author) {
		authorService.addAuthor(author);
	}


}
