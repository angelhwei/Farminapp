package com.neverleave0916.farmin.ui.record;

public class recordview2 {

    private String transaction_create_dt;
    private String transaction_product_name;
    private String transaction_participate_product_amount;
    private String transaction_product_unit_price;
    private String transaction_discount;
    private String transaction_total;

    public recordview2(){

    }
    public recordview2(String transaction_create_dt, String product_name, String transaction_participate_product_amount, String product_unit_price, String transaction_discount, String transaction_total) {
        this.transaction_create_dt = transaction_create_dt;
        this.transaction_product_name = product_name;
        this.transaction_participate_product_amount = transaction_participate_product_amount;
        this.transaction_product_unit_price = product_unit_price;
        this.transaction_discount = transaction_discount;
        this.transaction_total = transaction_total;
    }
    public String getTransaction_create_dt() {
        return transaction_create_dt;
    }

    public String getTransaction_participate_product_amount() {
        return transaction_participate_product_amount;
    }

    public String getTransaction_discount() {
        return transaction_discount;
    }

    public String getTransaction_total() {
        return transaction_total;
    }

    public String getTransaction_product_name() {
        return transaction_product_name;
    }

    public String getTransaction_product_unit_price() {
        return transaction_product_unit_price;
    }

    public void setTransaction_create_dt(String transaction_create_dt) {
        this.transaction_create_dt = transaction_create_dt;
    }

    public void setTransaction_participate_product_amount(String transaction_participate_product_amount) {
        this.transaction_participate_product_amount = transaction_participate_product_amount;
    }

    public void setTransaction_discount(String transaction_discount) {
        this.transaction_discount = transaction_discount;
    }

    public void setTransaction_total(String transaction_total) {
        this.transaction_total = transaction_total;
    }

    public void setTransaction_product_name(String transaction_product_name) {
        this.transaction_product_name = transaction_product_name;
    }

    public void setTransaction_product_unit_price(String transaction_product_unit_price) {
        this.transaction_product_unit_price = transaction_product_unit_price;
    }
}
