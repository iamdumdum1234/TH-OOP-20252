package hust.soict.hedspi.aims.screen;

import hust.soict.hedspi.aims.cart.Cart.Cart;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store.Store;

import javax.swing.*;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfDirector, tfLength;

    public AddBookToStoreScreen(Store store, Cart cart) {
        super(store, cart, "Add DVD to Store");
    }

    @Override
    protected void addAdditionalInputs() {
    }

    @Override
    protected void addItemToStore() {
        String title = tfTitle.getText();
        String category = tfCategory.getText();
        float cost = tfCost.getText().isEmpty() ? 0 : Float.parseFloat(tfCost.getText());
        String director = tfDirector.getText();
        int length = tfLength.getText().isEmpty() ? 0 : Integer.parseInt(tfLength.getText());

        store.addMedia(new DigitalVideoDisc(title, category, cost));
    }
}