package vn.dlu.chuyendoitienle;

import java.io.Serializable;

public class Flag implements Serializable {
    private String name;
    private int imgFlag;
    private double money;

    public Flag(String name, int imgFlag, double money) {
        this.name = name;
        this.imgFlag = imgFlag;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgFlag() {
        return imgFlag;
    }

    public void setImgFlag(int imgFlag) {
        this.imgFlag = imgFlag;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
