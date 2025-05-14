import java.util.*;
import java.util.stream.Collectors;

class Movie {
    private String title;
    private String director;
    private String genre;
    private int year;
    private double rating;

    public Movie(String title, String director, String genre, int year, double rating) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }

    // Getters
    public String getTitle() { return title; }
    public String getDirector() { return director; }
    public String getGenre() { return genre; }
    public int getYear() { return year; }
    public double getRating() { return rating; }

    @Override
    public String toString() {
        return String.format("%-20s %-15s %-10s %4d %.1f", 
            title, director, genre, year, rating);
    }
}

public class MovieCollection {
    private ArrayList<Movie> movies = new ArrayList<>();

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void removeMovie(String title) {
        movies.removeIf(movie -> movie.getTitle().equalsIgnoreCase(title));
    }

    public List<Movie> filterByGenre(String genre) {
        return movies.stream()
            .filter(movie -> movie.getGenre().equalsIgnoreCase(genre))
            .collect(Collectors.toList());
    }

    public List<Movie> filterByDirector(String director) {
        return movies.stream()
            .filter(movie -> movie.getDirector().equalsIgnoreCase(director))
            .collect(Collectors.toList());
    }

    public List<Movie> filterByYear(int year) {
        return movies.stream()
            .filter(movie -> movie.getYear() == year)
            .collect(Collectors.toList());
    }

    public void sortByTitle() {
        movies.sort(Comparator.comparing(Movie::getTitle));
    }

    public void sortByYear() {
        movies.sort(Comparator.comparingInt(Movie::getYear));
    }

    public void sortByRating() {
        movies.sort(Comparator.comparingDouble(Movie::getRating).reversed());
    }

    public void displayMovies() {
        if (movies.isEmpty()) {
            System.out.println("No movies in collection");
            return;
        }
        System.out.printf("%-20s %-15s %-10s %4s %4s\n", 
            "Title", "Director", "Genre", "Year", "Rating");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    public static void main(String[] args) {
        MovieCollection collection = new MovieCollection();
        collection.addMovie(new Movie("Inception", "Christopher Nolan", "Sci-Fi", 2010, 8.8));
        collection.addMovie(new Movie("The Shawshank Redemption", "Frank Darabont", "Drama", 1994, 9.3));
        collection.addMovie(new Movie("Pulp Fiction", "Quentin Tarantino", "Crime", 1994, 8.9));
        
        System.out.println("All Movies:");
        collection.displayMovies();
        
        System.out.println("\nMovies from 1994:");
        List<Movie> movies1994 = collection.filterByYear(1994);
        movies1994.forEach(System.out::println);
        
        System.out.println("\nSorted by Rating:");
        collection.sortByRating();
        collection.displayMovies();
    }
}
