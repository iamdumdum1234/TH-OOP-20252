package hust.soict.hedspi.aims.Aims;

import hust.soict.hedspi.aims.cart.Cart.Cart;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.store.Store.Store;

import java.util.Scanner;

public class Aims {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Store store = new Store();
        Cart cart = new Cart();

        // Sample data for testing
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladdin", "Animation", 18.99f);
        store.addMedia(dvd1, dvd2, dvd3);

        handleMainMenu(scanner, store, cart);
        scanner.close();
        System.out.println("Goodbye!");
        System.exit(0);
    }

    public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3");
    }

    public static void handleMainMenu(Scanner scanner, Store store, Cart cart) {
        int choice = -1;
        while (choice != 0) {
            showMenu();
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        handleViewStore(scanner, store, cart);
                        break;
                    case 2:
                        handleUpdateStore(scanner, store);
                        break;
                    case 3:
                        handleSeeCurrentCart(scanner, cart);
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    public static void storeMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media’s details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4");
    }

    public static void handleViewStore(Scanner scanner, Store store, Cart cart) {
        System.out.println("--- Store Items ---");
        for (Media media : store.getItemsInStore()) {
            System.out.println(media.toString());
        }
        int choice = -1;
        while (choice != 0) {
            storeMenu();
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        handleMediaDetails(scanner, store, cart);
                        break;
                    case 2:
                        handleAddMediaToCart(scanner, store, cart);
                        break;
                    case 3:
                        handlePlayMedia(scanner, store);
                        break;
                    case 4:
                        handleSeeCurrentCart(scanner, cart);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("Invalid input.");
                scanner.nextLine();
            }
        }
    }

    public static void mediaDetailsMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2");
    }

    public static void handleMediaDetails(Scanner scanner, Store store, Cart cart) {
        System.out.println("Please enter the title of the media: ");
        String title = scanner.nextLine();
        Media media = store.findMediaByTitle(title);
        if (media != null) {
            System.out.println(media.toString());
            int choice = -1;
            while (choice != 0) {
                mediaDetailsMenu();
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    switch (choice) {
                        case 1:
                            cart.addMedia(media);
                            System.out.println("Media added to cart.");
                            break;
                        case 2:
                            if (media instanceof Playable) {
                                ((Playable) media).play();
                            } else {
                                System.out.println("This media is not playable.");
                            }
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                } else {
                    System.out.println("Invalid input.");
                    scanner.nextLine();
                }
            }
        } else {
            System.out.println("Media not found.");
        }
    }

    public static void handleAddMediaToCart(Scanner scanner, Store store, Cart cart) {
        System.out.println("Please enter the title of the media: ");
        String title = scanner.nextLine();
        Media media = store.findMediaByTitle(title);
        if (media != null) {
            cart.addMedia(media);
            int count = 0;
            for (Media m : cart.getItemsOrdered()) {
                if (m instanceof DigitalVideoDisc) count++;
            }
            System.out.println("Number of DVDs in cart: " + count);
        } else {
            System.out.println("Media not found.");
        }
    }

    public static void handlePlayMedia(Scanner scanner, Store store) {
        System.out.println("Please enter the title of the media: ");
        String title = scanner.nextLine();
        Media media = store.findMediaByTitle(title);
        if (media != null) {
            if (media instanceof Playable) {
                ((Playable) media).play();
            } else {
                System.out.println("This media is not playable.");
            }
        } else {
            System.out.println("Media not found.");
        }
    }

    public static void handleUpdateStore(Scanner scanner, Store store) {
        System.out.println("1. Add media to store");
        System.out.println("2. Remove media from store");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            System.out.println("Enter media title: ");
            String title = scanner.nextLine();
            System.out.println("Enter media category: ");
            String category = scanner.nextLine();
            System.out.println("Enter media cost: ");
            float cost = scanner.nextFloat();
            scanner.nextLine();
            // Defaulting to DVD for simplicity in adding from console unless specified
            store.addMedia(new DigitalVideoDisc(title, category, cost));
        } else if (choice == 2) {
            System.out.println("Enter media title to remove: ");
            String title = scanner.nextLine();
            Media media = store.findMediaByTitle(title);
            if (media != null) {
                store.removeMedia(media);
            } else {
                System.out.println("Media not found.");
            }
        }
    }

    public static void cartMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4-5");
    }

    public static void handleSeeCurrentCart(Scanner scanner, Cart cart) {
        System.out.println("--- Current Cart ---");
        for (Media media : cart.getItemsOrdered()) {
            System.out.println(media.toString());
        }
        System.out.println("Total cost: " + cart.totalCost());

        int choice = -1;
        while (choice != 0) {
            cartMenu();
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        handleFilterCart(scanner, cart);
                        break;
                    case 2:
                        handleSortCart(scanner, cart);
                        break;
                    case 3:
                        handleRemoveFromCart(scanner, cart);
                        break;
                    case 4:
                        handlePlayMediaInCart(scanner, cart);
                        break;
                    case 5:
                        System.out.println("Order created! Clearing cart.");
                        cart.clear();
                        choice = 0;
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("Invalid input.");
                scanner.nextLine();
            }
        }
    }

    public static void handleFilterCart(Scanner scanner, Cart cart) {
        System.out.println("1. Filter by ID");
        System.out.println("2. Filter by Title");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            System.out.println("Enter ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Media m = cart.searchById(id);
            if (m != null) System.out.println(m.toString());
            else System.out.println("Not found.");
        } else if (choice == 2) {
            System.out.println("Enter Title: ");
            String title = scanner.nextLine();
            Media m = cart.searchByTitle(title);
            if (m != null) System.out.println(m.toString());
            else System.out.println("Not found.");
        }
    }

    public static void handleSortCart(Scanner scanner, Cart cart) {
        System.out.println("1. Sort by Title");
        System.out.println("2. Sort by Cost");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            cart.sortByTitle();
        } else if (choice == 2) {
            cart.sortByCost();
        }
        for (Media media : cart.getItemsOrdered()) {
            System.out.println(media.toString());
        }
    }

    public static void handleRemoveFromCart(Scanner scanner, Cart cart) {
        System.out.println("Enter title to remove: ");
        String title = scanner.nextLine();
        Media m = cart.searchByTitle(title);
        if (m != null) {
            cart.removeMedia(m);
        } else {
            System.out.println("Not found in cart.");
        }
    }

    public static void handlePlayMediaInCart(Scanner scanner, Cart cart) {
        System.out.println("Enter title to play: ");
        String title = scanner.nextLine();
        Media m = cart.searchByTitle(title);
        if (m != null) {
            ((Playable) m).play();
        } else {
            System.out.println("Not found or not playable.");
        }
    }
}