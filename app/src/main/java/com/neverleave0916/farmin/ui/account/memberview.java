package com.neverleave0916.farmin.ui.account;

public class memberview {

    private String member_id ;
    private String member_card_id;
    private String member_name;
    private String member_gender;
    private String member_birthday;
    private String member_phone;
    private String member_email;
    private String member_address;
    private String member_balance;


    public memberview(){

    }

    public memberview(String member_balance,String member_id, String member_card_id, String member_name, String member_gender , String member_birthday, String member_phone, String member_email, String member_address){
        this.member_id = member_id;
        this.member_card_id = member_card_id;
        this.member_name = member_name;
        this.member_gender = member_gender;
        this.member_birthday = member_birthday;
        this.member_phone = member_phone;
        this.member_email = member_email;
        this.member_address = member_address;
        this.member_balance = member_balance;

    }

    public String getMember_id() {
        return member_id;
    }

    public String getMember_card_id() {
        return member_card_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public String getMember_gender() {
        return member_gender;
    }

    public String getMember_birthday() {
        return member_birthday;
    }

    public String getMember_phone() {
        return member_phone;
    }

    public String getMember_email() {
        return member_email;
    }

    public String getMember_address() {
        return member_address;
    }

    public String getMember_balance() {
        return member_balance;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public void setMember_card_id(String member_card_id) {
        this.member_card_id = member_card_id;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public void setMember_gender(String member_gender) {
        this.member_gender = member_gender;
    }

    public void setMember_birthday(String member_birthday) {
        this.member_birthday = member_birthday;
    }

    public void setMember_phone(String member_phone) {
        this.member_phone = member_phone;
    }

    public void setMember_email(String member_email) {
        this.member_email = member_email;
    }

    public void setMember_address(String member_address) {
        this.member_address = member_address;
    }

    public void setMember_balance(String member_balance) {
        this.member_balance = member_balance;
    }
}
