package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public class MediaComparatorByCostTitle implements Comparator<Media> {
    // Abstraction in effect, I don't know what this thing does behind the scenes
    // But it sorts everything out for me
    @Override
    public int compare(Media media1, Media media2) {
        int costComparison = Float.compare(media2.getCost(), media1.getCost());
        if (costComparison != 0) {
            return costComparison;
        }
        return media1.getTitle().compareTo(media2.getTitle());
    }
}
