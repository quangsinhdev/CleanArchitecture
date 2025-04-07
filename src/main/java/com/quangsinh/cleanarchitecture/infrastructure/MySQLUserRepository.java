
package com.quangsinh.cleanarchitecture.infrastructure;

import com.quangsinh.cleanarchitecture.domain.entity.User;
import com.quangsinh.cleanarchitecture.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class MySQLUserRepository implements UserRepository {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testdb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "your_password";

	public MySQLUserRepository() {
		initializeDatabase();
	}

	private void initializeDatabase() {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				Statement stmt = conn.createStatement()) {
			stmt.execute("CREATE TABLE IF NOT EXISTS users (" + "id BIGINT PRIMARY KEY, " + "name VARCHAR(255), "
					+ "email VARCHAR(255))");
		} catch (SQLException e) {
			throw new RuntimeException("Failed to initialize MySQL database", e);
		}
	}

	@Override
	public User save(User user) {
		String sql = "INSERT INTO users (id, name, email) VALUES (?, ?, ?)";
		try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setLong(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getEmail());
			pstmt.executeUpdate();
			return user;
		} catch (SQLException e) {
			throw new RuntimeException("Failed to save user to MySQL", e);
		}
	}

	@Override
	public User findById(Long id) {
		String sql = "SELECT * FROM users WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new User(rs.getLong("id"), rs.getString("name"), rs.getString("email"));
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException("Failed to find user in MySQL", e);
		}
	}

	@Override
	public void deleteById(Long id) {
		String sql = "DELETE FROM users WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Failed to delete user from MySQL", e);
		}
	}

	@Override
	public List<User> findAll() {
		String sql = "SELECT * FROM users";
		List<User> users = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				users.add(new User(rs.getLong("id"), rs.getString("name"), rs.getString("email")));
			}
			return users;
		} catch (SQLException e) {
			throw new RuntimeException("Failed to fetch all users from MySQL", e);
		}
	}
}
