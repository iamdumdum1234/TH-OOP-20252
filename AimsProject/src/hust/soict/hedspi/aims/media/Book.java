package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {
    private List<String> authors = new ArrayList<String>();

    public Book(String title, String category, float cost, List<String> authors) {
        super(title, category, cost);
        if (authors == null) {
            this.authors = new ArrayList<>();
        }
        else {
            this.authors = new ArrayList<>(authors);
        }
    }

    public void addAuthor(String author) {
        if (!authors.contains(author)) {
            authors.add(author);
        }
        else {
            System.out.println("Author already exists!");
        }
    }

    public void removeAuthor(String author) {
        if (authors.contains(author)) {
            authors.remove(author);
        }
        else {
            System.out.println("Author does not exist!");
        }
    }

    public List<String> getAuthors() {
        return authors;
    }

    @Override
    public String toString() {
        return  "Item #" + this.getId() +
                "\nType: Book" +
                "\nTitle: " + this.getTitle() +
                "\nCategory: " + this.getCategory() +
                "\nCost: " + this.getCost() +
                "\nAuthors: " + this.getAuthors();
    }
}
