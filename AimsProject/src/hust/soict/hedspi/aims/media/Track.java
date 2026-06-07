package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

import java.util.Objects;

public class Track implements Playable {
    private final String title;
    private final int length;

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

    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing DVD: " + this.getTitle());
            System.out.println("Length: " + this.getLength());
        }
        else {
            throw new PlayerException("ERROR: Track length is non-positive!");
        }

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
