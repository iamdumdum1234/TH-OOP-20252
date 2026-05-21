package hust.soict.hedspi.test.disc.TestPassingParameter;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;

public class TestPassingParameter {
    public static void main(String[] args) {
        DigitalVideoDisc jungleDVD = new DigitalVideoDisc("Jungle");
        DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc("Cinderella");

        swap(jungleDVD, cinderellaDVD);
        System.out.println("Jungle DVD title: " + jungleDVD.getTitle());
        System.out.println("Cinderella DVD title: " + cinderellaDVD.getTitle());

        // changeTitle(jungleDVD, cinderellaDVD.getTitle());
        // System.out.println("Jungle DVD title: " + jungleDVD.getTitle());
    }

    // Java can't do this, because it's pass-by-value
    /* public static void swap(Object o1, Object o2) {
        Object tmp = o1;
        o1 = o2;
        o2 = tmp;
    } */

    // Better swap method
    public static void swap(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
        String oldTitle1 = dvd1.getTitle();
        String oldTitle2 = dvd2.getTitle();
        dvd1.setTitle(oldTitle2);
        dvd2.setTitle(oldTitle1);
    }

    public static void changeTitle(DigitalVideoDisc dvd, String title) {
        String oldTitle = dvd.getTitle();
        dvd.setTitle(title);
        dvd = new DigitalVideoDisc(oldTitle);
    }
}
