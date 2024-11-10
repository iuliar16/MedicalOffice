package com.idm.server.db;

import org.mariadb.jdbc.Statement;

import java.sql.*;

public class InvalidTokenRepository {
    private Connection connection;

    public InvalidTokenRepository() {
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
    public InvalidToken save(InvalidToken token) throws SQLException {
        String query = "INSERT INTO invalid_tokens (token,reason) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, token.getToken());
            preparedStatement.setString(2, token.getReason());
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Failed to add token to invalid_tokens");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    token.setId(generatedKeys.getInt(1));
                    System.out.println(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Nu exista id");
                }
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            throw  e;
        }
        return token;
    }

    public InvalidToken findByToken(String token) {
        String sql = "SELECT * FROM invalid_tokens WHERE token = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, token);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    InvalidToken invalidToken = new InvalidToken();
                    invalidToken.setId(resultSet.getInt("id"));
                    System.out.println(resultSet.getInt("id"));
                    invalidToken.setToken(resultSet.getString("token"));
                    invalidToken.setReason(resultSet.getString("reason"));
                    return invalidToken;
                }
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return null;
    }
}
