package hust.soict.hedspi.aims.screen;

import hust.soict.hedspi.aims.cart.Cart.Cart;
import hust.soict.hedspi.aims.store.Store.Store;

import javax.swing.*;
import java.awt.*;

public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected Cart cart;
    protected JPanel centerPanel;
    protected JTextField tfTitle, tfCategory, tfCost;

    public AddItemToStoreScreen(Store store, Cart cart, String title) {
        this.store = store;
        this.cart = cart;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);
        cp.add(createSouth(), BorderLayout.SOUTH);

        setTitle(title);
        setSize(1024, 768);
        setVisible(true);
    }

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());

        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel titleLabel = new JLabel("AIMS Subsystem");
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, 40));
        titleLabel.setForeground(Color.CYAN);
        header.add(titleLabel);
        north.add(header);

        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenu smUpdateStore = new JMenu("Update Store");
        JMenuItem addBook = new JMenuItem("Add Book");
        addBook.addActionListener(e -> { new AddBookToStoreScreen(store, cart); dispose(); });
        smUpdateStore.add(addBook);

        JMenuItem addCd = new JMenuItem("Add CD");
        addCd.addActionListener(e -> { new AddCDToStoreScreen(store, cart); dispose(); });
        smUpdateStore.add(addCd);

        JMenuItem addDvd = new JMenuItem("Add DVD");
        addDvd.addActionListener(e -> { new AddDVDToStoreScreen(store, cart); dispose(); });
        smUpdateStore.add(addDvd);

        menu.add(smUpdateStore);

        JMenuItem viewStore = new JMenuItem("View store");
        viewStore.addActionListener(e -> { new StoreScreen(store, cart); dispose(); });
        menu.add(viewStore);

        JMenuItem viewCart = new JMenuItem("View cart");
        viewCart.addActionListener(e -> { new CartScreen(cart, store); dispose(); });
        menu.add(viewCart);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);
        return menuBar;
    }

    JPanel createCenter() {
        centerPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        centerPanel.add(new JLabel("Title: *"));
        tfTitle = new JTextField();
        centerPanel.add(tfTitle);

        centerPanel.add(new JLabel("Category:"));
        tfCategory = new JTextField();
        centerPanel.add(tfCategory);

        centerPanel.add(new JLabel("Cost: *"));
        tfCost = new JTextField();
        centerPanel.add(tfCost);

        // Abstract Template method so subclasses can tack on more specific attributes
        addAdditionalInputs();

        return centerPanel;
    }

    JPanel createSouth() {
        JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addBtn = new JButton("Add Item");
        addBtn.addActionListener(e -> {
            addItemToStore();
            JOptionPane.showMessageDialog(this, "Item successfully added to store!");
        });
        south.add(addBtn);
        return south;
    }

    // Subclasses must implement these specific behaviors
    protected abstract void addAdditionalInputs();
    protected abstract void addItemToStore();
}