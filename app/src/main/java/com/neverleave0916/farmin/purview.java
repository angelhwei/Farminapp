package com.neverleave0916.farmin;

public class purview {

    private String buyname ;
    private float buyprice;
    private int buycount;

    public purview(String buyname, float buyprice, int buycount) {
        this.buyname = buyname;
        this.buyprice = buyprice;
        this.buycount = buycount;
    }


    public String getBuyname() {
        return buyname;
    }

    public float getBuyprice() {
        return buyprice;
    }

    public int getBuycount() {
        return buycount;
    }

    public void setBuyname(String buyname) {
        this.buyname = buyname;
    }

    public void setBuyprice(Integer buyprice) {
        this.buyprice = buyprice;
    }

    public void setBuycount(Integer buycount) {
        this.buycount = buycount;
    }

}
