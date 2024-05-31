package deso1.doanquanghuy.dlu_2015597;

import java.io.Serializable;

public class Food implements Serializable {
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNameFood() {
        return NameFood;
    }

    public void setNameFood(String nameFood) {
        NameFood = nameFood;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public int getImageFood() {
        return ImageFood;
    }

    public void setImageFood(int imageFood) {
        ImageFood = imageFood;
    }

    private  int ID;
    private String NameFood;
    private int Price;

    public Food(int ID, String nameFood, int price, String unit, int imageFood) {
        this.ID = ID;
        NameFood = nameFood;
        Price = price;
        Unit = unit;
        ImageFood = imageFood;
    }

    private String Unit;
    private int ImageFood;
}
