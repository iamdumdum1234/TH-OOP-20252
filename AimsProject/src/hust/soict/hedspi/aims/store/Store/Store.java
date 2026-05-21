package hust.soict.hedspi.aims.store.Store;

import hust.soict.hedspi.aims.media.Media;

import java.util.ArrayList;

public class Store {
    private final ArrayList<Media> itemsInStore = new ArrayList<Media>();

    public void addMedia(Media... media) {
        for (int i = 0; i < media.length; i++) {
            itemsInStore.add(media[i]);
        }
        System.out.println(media.length + " items added to the store!");
    }

    public void removeMedia(Media media) {
        if (itemsInStore.contains(media)) {
            itemsInStore.remove(media);
            System.out.println(media.getTitle() + " removed from the store!");
        }
        else {
            System.out.println("The media does not exist in the store!");
        }
    }

    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }

    public Media findMediaByTitle(String title) {
        for (Media media : itemsInStore) {
            if (media.getTitle().equals(title)) {
                return media;
            }
        }
        return null;
    }
}