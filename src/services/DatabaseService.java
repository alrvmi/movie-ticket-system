package services;

import java.sql.*;

public class DatabaseService {
    private Connection connection;

    // Constructor to establish database connection
    public DatabaseService(String url, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    // Method to insert a movie into the database
    public void insertMovie(String title, String genre, int duration, String releaseDate) throws SQLException {
        String query = "INSERT INTO movies (title, genre, duration, release_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, title);
            statement.setString(2, genre);
            statement.setInt(3, duration);
            statement.setDate(4, Date.valueOf(releaseDate));
            statement.executeUpdate();
        }
    }

    // Method to retrieve all movies from the database
    public void getMovies() throws SQLException {
        String query = "SELECT * FROM movies";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") + ", Title: " + resultSet.getString("title") +
                        ", Genre: " + resultSet.getString("genre") + ", Duration: " + resultSet.getInt("duration") +
                        ", Release Date: " + resultSet.getDate("release_date"));
            }
        }
    }

    // Method to update a movie in the database
    public void updateMovie(int id, String title, String genre, int duration, String releaseDate) throws SQLException {
        String query = "UPDATE movies SET title = ?, genre = ?, duration = ?, release_date = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, title);
            statement.setString(2, genre);
            statement.setInt(3, duration);
            statement.setDate(4, Date.valueOf(releaseDate));
            statement.setInt(5, id);
            statement.executeUpdate();
        }
    }

    // Method to delete a movie from the database
    public void deleteMovie(int id) throws SQLException {
        String query = "DELETE FROM movies WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    // Close connection
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
