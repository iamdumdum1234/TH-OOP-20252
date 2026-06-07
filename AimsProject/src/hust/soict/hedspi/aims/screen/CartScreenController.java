package hust.soict.hedspi.aims.screen;

import hust.soict.hedspi.aims.cart.Cart.Cart;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store.Store;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class CartScreenController {

    private final Cart cart;
    private final Store store;
    private final javax.swing.JFrame parentFrame;

    @FXML
    private ToggleGroup filterCategory;

    @FXML
    private Button btnPlay, btnRemove;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private Label costLabel;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;

    @FXML
    private TextField tfFilter;

    public CartScreenController(Cart cart, Store store, javax.swing.JFrame parentFrame) {
        this.cart = cart;
        this.store = store;
        this.parentFrame = parentFrame;
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media != null) {
            cart.removeMedia(media);
        }
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media instanceof Playable) {
            try {
                ((Playable) media).play();
            } catch (PlayerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Play Error");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        if (!cart.getItemsOrdered().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Placing order");
            alert.setHeaderText(null);
            alert.setContentText("Your order has been placed! The cart will be cleared.");
            alert.showAndWait();

            cart.clear();
            tblMedia.setItems(cart.getItemsOrdered());
            btnPlay.setVisible(false);
            btnRemove.setVisible(false);
            costLabel.setText(cart.totalCost() + " $");
        }
    }

    private void navigateToScreen() {
        if (parentFrame != null) {
            javax.swing.SwingUtilities.invokeLater(parentFrame::dispose);
        }
    }

    @FXML
    void viewStorePressed(ActionEvent event) {
        new StoreScreen(store, cart);
        navigateToScreen();
    }

    @FXML
    void viewCartPressed(ActionEvent event) {
        new CartScreen(cart, store);
        navigateToScreen();
    }

    @FXML
    void addBookPressed(ActionEvent event) {
        new AddBookToStoreScreen(store, cart);
        navigateToScreen();
    }

    @FXML
    void addCDPressed(ActionEvent event) {
        new AddCDToStoreScreen(store, cart);
        navigateToScreen();
    }

    @FXML
    void addDVDPressed(ActionEvent event) {
        new AddDVDToStoreScreen(store, cart);
        navigateToScreen();
    }

    @FXML
    private void initialize() {
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        tblMedia.setItems(this.cart.getItemsOrdered());

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Media>() {
                    @Override
                    public void changed(ObservableValue<? extends Media> observable, Media oldValue, Media newValue) {
                        if (newValue != null) {
                            updateButtonBar(newValue);
                        }
                    }
                }
        );

        tfFilter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                showFilteredMedia(newValue);
            }
        });

        cart.getItemsOrdered().addListener((ListChangeListener<Media>) c ->
                costLabel.setText(cart.totalCost() + " $"));

        costLabel.setText(cart.totalCost() + " $");
    }

    void showFilteredMedia(String filter) {
        if (filter == null || filter.isEmpty()) {
            tblMedia.setItems(this.cart.getItemsOrdered());
            return;
        }

        if (radioBtnFilterId.isSelected()) {
            try {
                int id = Integer.parseInt(filter);
                tblMedia.setItems(this.cart.searchById(id));
            } catch (NumberFormatException e) {
                tblMedia.setItems(this.cart.getItemsOrdered());
            }
        } else if (radioBtnFilterTitle.isSelected()) {
            tblMedia.setItems(this.cart.searchByTitle(filter));
        }
    }

    void updateButtonBar(Media media) {
        btnRemove.setVisible(true);
        btnPlay.setVisible(media instanceof Playable);
    }
}