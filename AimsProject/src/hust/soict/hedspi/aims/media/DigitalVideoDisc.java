package hust.soict.hedspi.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {
    // public static int nbDigitalVideoDiscs = 0;

    // One singular master constructor for everything, ensuring code reusability

    public DigitalVideoDisc(String title, String category, float cost, int length, String director) {
        super(title, category, cost, length, director);
    }

    // Title only
    public DigitalVideoDisc(String name) {
        this(name, null, 0.0f, 0, null);
    }

    // Name, category, cost only
    public DigitalVideoDisc(String name, String category, float cost) {
        this(name, category, null, cost);
    }

    // Name, category, director, cost only
    public DigitalVideoDisc(String name, String category, String director, float cost) {
        this(name, category, cost, 0, director);
    }

    @Override
    public void play() {
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("Length: " + this.getLength());
    }

    @Override
    public String toString() {
        return "Item #" + this.getId() +
                "\nType: DVD" +
                "\nTitle: " + this.getTitle() +
                "\nCategory: " + this.getCategory() +
                "\nLength: " + this.getLength() +
                "\nDirector: " + this.getDirector() +
                "\nCost: " + this.getCost();
    }
}
