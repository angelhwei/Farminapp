package com.neverleave0916.farmin.ui.record;

public class recordview {

    private String deposit_amount ;
    private String deposit_dt;

    public recordview(){

    }

    public recordview(String deposit_amount, String deposit_dt) {
        this.deposit_amount = deposit_amount;
        this.deposit_dt = deposit_dt;
    }

    public String getDeposit_amount() {
        return deposit_amount;
    }

    public String getDeposit_dt() {
        return deposit_dt;
    }

    public void setDeposit_amount(String deposit_amount) {
        this.deposit_amount = deposit_amount;
    }

    public void setDeposit_dt(String deposit_dt) {
        this.deposit_dt = deposit_dt;
    }
}
