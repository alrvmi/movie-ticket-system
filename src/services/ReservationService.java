package services;

import models.Movie;
import models.Ticket;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationService {
    private List<Movie> movies = new ArrayList<>();
    private List<Ticket> tickets = new ArrayList<>();
    private List<User> users = new ArrayList<>();


    public void addMovie(Movie movie) {
        movies.add(movie);
    }


    public List<Movie> searchByTitle(String title) {
        return movies.stream()
                .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }


    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }


    public List<Ticket> getAllTickets() {
        return new ArrayList<>(tickets);
    }


    public void addUser(User user) {
        users.add(user);
    }


    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}
