package com.idm.server.db;

import org.mariadb.jdbc.Statement;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepository {

    private Connection connection;

    public UserRepository() {
        String url = "jdbc:mariadb://localhost:3306/medical_office";
        String username = "root";
        String password = "admin";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            this.connection = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.print(e.getMessage());
        }
    }

    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setId_user(resultSet.getInt("user_id"));
                    System.out.println(resultSet.getInt("user_id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(resultSet.getString("role"));
                    return user;
                }
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return null;
    }

    public User save(User user) throws SQLException {
        String query = "INSERT INTO users (role, username, password) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getRole());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Failed to create new user");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId_user(generatedKeys.getInt(1));
                    System.out.println(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Nu exista id");
                }
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            throw  e;
        }
        return user;
    }

}
