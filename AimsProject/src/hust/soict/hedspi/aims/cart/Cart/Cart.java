package hust.soict.hedspi.aims.cart.Cart;

import hust.soict.hedspi.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Collections;

public class Cart {
    // Standard variables from lab assignment
    private final ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();

    public void addMedia(Media... mediaList) {
        Collections.addAll(itemsOrdered, mediaList);
    }

    public void removeMedia(Media media) {
        if (itemsOrdered.contains(media)) {
            System.out.println("Removing " + media.getTitle() + " from cart...");
            itemsOrdered.remove(media);
            System.out.println("Successfully removed it from the cart!");
        }
        else {
            System.out.println("The media does not exist in the cart!");
        }
    }

    public void clear() {
        itemsOrdered.clear();
    }

    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    public ObservableList<Media> searchById(int id) {
        ObservableList<Media> result = FXCollections.observableArrayList();
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                result.add(media);
            }
        }
        return result;
    }

    public ObservableList<Media> searchByTitle(String title) {
        ObservableList<Media> result = FXCollections.observableArrayList();
        for (Media media : itemsOrdered) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                result.add(media);
            }
        }
        return result;
    }

    public void sortByTitle() {
        itemsOrdered.sort(Media.COMPARE_BY_TITLE_COST);
    }

    public void sortByCost() {
        itemsOrdered.sort(Media.COMPARE_BY_COST_TITLE);
    }


    public float totalCost() {
        float totalPrice = 0;
        for (int i = 0; i < itemsOrdered.size(); i++) {
            if (itemsOrdered.get(i).getCost() == 0) {
                System.out.println("The price of " + itemsOrdered.get(i).getTitle() + " is not available! Skipping...");
            }
            else {
                totalPrice += itemsOrdered.get(i).getCost();
            }
        }
        return totalPrice;
    }
}