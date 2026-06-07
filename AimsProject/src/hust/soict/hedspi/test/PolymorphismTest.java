package hust.soict.hedspi.test;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;

import java.util.ArrayList;
import java.util.List;

public class PolymorphismTest {

    static void main(String[] args) {
        List<Media> MediaList = new ArrayList<Media>();

        CompactDisc cd1 = new CompactDisc("CD: The Movie", "Miscellaneous", 10.0f, 120, "Director Name", "Artist Name", null);
        DigitalVideoDisc dvd1 =  new DigitalVideoDisc("DVD: The Movie", "Miscellaneous", 20f, 120, "Director Name");
        Book book1 = new Book("Book: The Book", "Miscellaneous", 10.0f, null);
        MediaList.add(cd1);
        MediaList.add(dvd1);
        MediaList.add(book1);

        for (Media media : MediaList) {
            System.out.println(media.toString());
        }
    }
}
