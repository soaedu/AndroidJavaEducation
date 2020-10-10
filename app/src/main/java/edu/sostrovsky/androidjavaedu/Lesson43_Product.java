package edu.sostrovsky.androidjavaedu;

/**
 * Created by sostrovschi on 12/13/16.
 */

public class Lesson43_Product {

    private String name;
    private int price;
    private int image;
    private boolean box;


    Lesson43_Product(String _describe, int _price, int _image, boolean _box) {
        name    = _describe;
        price   = _price;
        image   = _image;
        box     = _box;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isBox() {
        return box;
    }

    public void setBox(boolean box) {
        this.box = box;
    }
}