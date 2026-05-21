package hust.soict.hedspi.aims.media;

import java.util.Objects;

public class Track implements Playable {
    private String title;
    private int length;

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public void play() {
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("Length: " + this.getLength());
    }

    @Override
    public boolean equals(Object obj) {
        // Check if the object is the same instance
        if (obj == this) {
            return true;
        }

        // Check if the object is not Media
        if (!(obj instanceof Track track)) {
            return false;
        }
        // Check if the object is of the same class
        // Compare by title
        return Objects.equals(this.title, track.title) && Objects.equals(this.length, track.length);
    }
}
