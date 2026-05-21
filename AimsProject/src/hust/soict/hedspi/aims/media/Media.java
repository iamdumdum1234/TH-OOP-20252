package hust.soict.hedspi.aims.media;

import java.util.Comparator;
import java.util.Objects;

public abstract class Media {

    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();

    private static int objID = 1;

    private int id;
    private String title;
    private String category;
    private float cost;

    public Media(String title, String category, float cost) {
        this.id = objID++;
        this.title = title;
        this.category = category;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object obj) {
        // Check if the object is the same instance
        if (obj == this) {
            return true;
        }

        // Check if the object is not Media
        if (!(obj instanceof Media media)) {
            return false;
        }
        // Check if the object is of the same class
        // Compare by title
        return Objects.equals(this.title, media.title);
    }
}
