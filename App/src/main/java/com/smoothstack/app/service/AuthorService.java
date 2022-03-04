package com.smoothstack.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smoothstack.app.dao.AuthorDao;
import com.smoothstack.app.model.Author;

@Service
public class AuthorService {
	
	@Autowired
	AuthorDao authorDao;

	public Author getAuthorById(int authorId) {
		return authorDao.getAuthorById(authorId);
	}

	public void addAuthor(Author author) {
		authorDao.addAuthor(author);
		
	}

}
