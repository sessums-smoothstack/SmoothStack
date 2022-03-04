package com.smoothstack.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.smoothstack.app.model.Author;

@Component
public class AuthorDao {

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "sam", "3301");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public Author getAuthorById(int authorId) {
		String sqlQuery = "SELECT * FROM library.tbl_author where authorId=?;";
		Connection conn = getConnection();
		Author author = new Author();
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, authorId);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				author.setAuthorId(result.getInt("authorId"));
				author.setAuthorName(result.getString("authorName"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return author;
	}
	public void addAuthor(Author author) {
		String sqlQuery = "INSERT INTO `library`.`tbl_author` (`authorId`, `authorName`) VALUES (?, ?);";
		Connection conn = getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, author.getAuthorId());
			ps.setString(2, author.getAuthorName());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
