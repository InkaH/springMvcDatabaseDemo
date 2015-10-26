package com.springcookbook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springcookbook.model.User;

//@Repository allows the UserDAO class to be automatically discovered and instantiated
//as a bean.
@Repository
public class UserDAO {

	//	The JdbcTemplate field will be initialized automatically by Spring via dependency injection
	//	with the JdbcTemplate bean defined in AppConfig
	//  The jdbcTemplate object takes care of the JDBC boilerplate code; opening and closing a
	//  connection to the database and handling the exceptions.
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	//  An inline class implementing RowMapper. The class defines how to generate a User object from a database row.
	private class UserMapper implements RowMapper<User> {
		public User mapRow(ResultSet row, int rowNum) throws
		SQLException {
			User user = new User();
			user.setId(row.getLong("id"));
			user.setFirstName(row.getString("first_name"));
			user.setAge(row.getInt("age"));
			return user;
		}
	}

	//  Method that defines an SQL insert query with question marks as placeholders for the actual row values. 
	//  The update() method executes the query using the actual values from the User object in place of the question marks.
	public void add(User user) {
		String sql = "insert into user (first_name, age) values (?, ?)";
		jdbcTemplate.update(sql, user.getFirstName(), user.getAge());
	}

	//  Method that uses the UserMapper object to generate a User object from the resulting database row.
	//  It retrieves a user by its ID, which is the second argument of
	//  queryForObject() as an element of an array.
	public User findById(Long id) {
		String sql = "select * from user where id=?";
		User user = jdbcTemplate.queryForObject(sql, new
				Object[]{id}, new UserMapper());
		return user;
	}

	//  Method that performs an SQL select query and generates a list of User objects from the result using
	//  RowMapper.
	public List<User> findAll() {
		String sql = "select * from user";
		List<User> userList = jdbcTemplate.query(sql, new UserMapper());
		return userList;
	}

	//  Method that updates an existing row in the database with the User object's fields.
	public void update(User user) {
		String sql = "update user set first_name=?, age=? where id=?";
		jdbcTemplate.update(sql, user.getFirstName(), user.getAge(),
				user.getId());
	}

	//  Method that creates a new database row if it doesn't exist, else it updates the existing row.
	public void save(User user) {
		if (user.getId() == null) {
			add(user);
		}
		else {
			update(user);
		}
	}

	//  Method that deletes existing row from the database.
	public void delete(User user) {
		String sql = "delete from user where id=?";
		getJdbcTemplate().update(sql, user.getId());
	}
}
