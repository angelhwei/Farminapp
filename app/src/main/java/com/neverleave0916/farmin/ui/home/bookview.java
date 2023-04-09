package com.neverleave0916.farmin.ui.home;

public class bookview {

    private String transaction_src_id;
    private String transaction_status_id;
    private String transaction_participate_product_amount;
    private String transaction_total;
    private String transaction_create_dt;


    public bookview(){

    }

    public String getTransaction_src_id() {
        return transaction_src_id;
    }

    public void setTransaction_src_id(String transaction_src_id) {
        this.transaction_src_id = transaction_src_id;
    }

    public String getTransaction_status_id() {
        return transaction_status_id;
    }

    public void setTransaction_status_id(String transaction_status_id) {
        this.transaction_status_id = transaction_status_id;
    }

    public String getTransaction_participate_product_amount() {
        return transaction_participate_product_amount;
    }

    public void setTransaction_participate_product_amount(String transaction_participate_product_amount) {
        this.transaction_participate_product_amount = transaction_participate_product_amount;
    }

    public String getTransaction_total() {
        return transaction_total;
    }

    public void setTransaction_total(String transaction_total) {
        this.transaction_total = transaction_total;
    }

    public String getTransaction_create_dt() {
        return transaction_create_dt;
    }

    public void setTransaction_create_dt(String transaction_create_dt) {
        this.transaction_create_dt = transaction_create_dt;
    }



    public bookview(String transaction_src_id, String transaction_status_id, String transaction_total, String transaction_participate_product_amount, String transaction_create_dt){
        this.transaction_src_id = transaction_src_id;
        this.transaction_status_id = transaction_status_id;
        this.transaction_participate_product_amount = transaction_participate_product_amount;
        this.transaction_total = transaction_total;
        this.transaction_create_dt = transaction_create_dt;


    }

}
