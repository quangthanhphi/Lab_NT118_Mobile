package vn.dlu.chuyendoitienle;


import java.io.Serializable;

public class DoDai implements Serializable {
    private String name;
    private double doDai;

    public DoDai(String name, double doDai) {
        this.name = name;
        this.doDai = doDai;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDoDai() {
        return doDai;
    }

    public void setDoDai(double doDai) {
        this.doDai = doDai;
    }
}
