package main;

import services.DatabaseService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseService databaseService = null;
        try {
            // Establish database connection
            databaseService = new DatabaseService("jdbc:postgresql://localhost:5432/moviedb", "postgres", "qwe123");

            boolean running = true;
            while (running) {
                System.out.println("Select an option:");
                System.out.println("1. Insert Movie");
                System.out.println("2. View Movies");
                System.out.println("3. Update Movie");
                System.out.println("4. Delete Movie");
                System.out.println("5. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.println("Enter movie title: ");
                        String title = scanner.nextLine();

                        System.out.println("Enter movie genre: ");
                        String genre = scanner.nextLine();

                        System.out.println("Enter movie duration (in minutes): ");
                        int duration = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Enter movie release date (YYYY-MM-DD): ");
                        String releaseDate = scanner.nextLine();

                        databaseService.insertMovie(title, genre, duration, releaseDate);
                        System.out.println("Movie inserted successfully.");
                        break;

                    case 2:
                        System.out.println("Movies in the database:");
                        databaseService.getMovies();
                        break;

                    case 3:
                        System.out.println("Enter movie ID to update: ");
                        int idToUpdate = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Enter new title: ");
                        String newTitle = scanner.nextLine();

                        System.out.println("Enter new genre: ");
                        String newGenre = scanner.nextLine();

                        System.out.println("Enter new duration (in minutes): ");
                        int newDuration = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Enter new release date (YYYY-MM-DD): ");
                        String newReleaseDate = scanner.nextLine();

                        databaseService.updateMovie(idToUpdate, newTitle, newGenre, newDuration, newReleaseDate);
                        System.out.println("Movie updated successfully.");
                        break;

                    case 4:
                        System.out.println("Enter movie ID to delete: ");
                        int idToDelete = scanner.nextInt();
                        scanner.nextLine();

                        databaseService.deleteMovie(idToDelete);
                        System.out.println("Movie deleted successfully.");
                        break;

                    case 5:
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (databaseService != null) {
                    databaseService.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            scanner.close();
        }
    }
}
