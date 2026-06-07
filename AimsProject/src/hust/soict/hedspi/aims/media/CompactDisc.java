package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

import java.util.ArrayList;
import java.util.List;

public class CompactDisc extends Disc implements Playable {
    private final String artist;
    private final List<Track> tracks;

    public String getArtist() {
        return artist;
    }

    public CompactDisc(String title, String category, float cost, int length, String director, String artist, List<Track> tracks) {
        super(title, category, cost, length, director);
        this.artist = artist;
        if (tracks == null) {
            this.tracks = new ArrayList<>(); // literally nothing
        }
        else {
            this.tracks = new ArrayList<>(tracks);
        }
    }

    public void addTrack(Track track) {
        if (!tracks.contains(track)) {
            tracks.add(track);
            System.out.println("Track added successfully!");
        }
        else {
            System.out.println("Track already exists!");
        }
    }

    public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
            System.out.println("Track removed successfully!");
        }
        else {
            System.out.println("Track does not exist!");
        }
    }

    public int getLength() {
        int totalLength = 0;
        for (int i = 0; i < tracks.size(); i++) {
            totalLength += tracks.get(i).getLength();
        }
        return totalLength;
    }

    // - Implement play() for CompactDisc
    // Since the CompactDisc class contains a ArrayList of Tracks, each of which can be played on its own. The play() method should output some information about the CompactDisc to
    // console. Loop through each track of the arraylist and call Track's play() method
    @Override
    // plays all tracks at once
    // this makes no sense
    // but hopefully as we progress we'll see a better implementation
    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            java.util.Iterator<Track> iter = tracks.iterator();
            Track nextTrack;
            while (iter.hasNext()) {
                nextTrack = (Track) iter.next();
                try {
                    nextTrack.play();
                }
                catch (PlayerException e) {
                    System.out.println("ERROR: " + e.getMessage());
                }
            }
        }
        else {
            throw new PlayerException("ERROR: CD length is non-positive!");
        }
    }

    // I didn't know that toString must be overridden until now
    @Override
    public String toString() {
        return "Item #" + this.getId() +
                "\nType: Compact Disc" +
                "\nCategory: " + this.getCategory() +
                "\nTitle: " + this.getTitle() +
                "\nArtist: " + this.getArtist() +
                "\nLength: " + this.getLength() +
                "\nDirector: " + this.getDirector() +
                "\nCost: " + this.getCost();
    }
}
