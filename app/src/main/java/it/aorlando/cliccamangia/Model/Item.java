package it.aorlando.cliccamangia.Model;

/**
 * Created by fagottino on 26/05/17.
 */

public class Item {
    public String name, description, image;
    public Double price;
    public int cl;

    public Item(String pName, String pDescription, String pImage, double pPrice) {
        this.name = pName;
        this.description = pDescription;
        this.image = pImage;
        this.price = pPrice;
    }

    public Item(String pName, String pDescription, String pImage, double pPrice, int pCl) {
        this.name = pName;
        this.description = pDescription;
        this.image = pImage;
        this.price = pPrice;
        this.cl = pCl;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCl() {
        return cl;
    }

    public void setCl(int cl) {
        this.cl = cl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
