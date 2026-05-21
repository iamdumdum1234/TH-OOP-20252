package hust.soict.hedspi.aims.cart.Cart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import hust.soict.hedspi.aims.media.Media;

public class Cart {
    // Standard variables from lab assignment
    private final List<Media> itemsOrdered = new ArrayList<Media>();

    public void addMedia(Media... mediaList) {
        for (int i = 0; i < mediaList.length; i++) {
            itemsOrdered.add(mediaList[i]);
        }
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

    public List<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    public Media searchById(int id) {
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                return media;
            }
        }
        return null;
    }

    public Media searchByTitle(String title) {
        for (Media media : itemsOrdered) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null;
    }

    public void sortByTitle() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
    }

    public void sortByCost() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
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