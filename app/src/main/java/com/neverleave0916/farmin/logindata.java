package com.neverleave0916.farmin;

import com.neverleave0916.farmin.PurchaseView;
import com.neverleave0916.farmin.ui.account.AccountFragment;

public class logindata {

    private static String id ;
    private static String card;
    private static String name;
    private static Integer gender;
    private static String birthday;
    private static String phone;
    private static String email;
    private static String address;
    private static Float balance;


    public static String getId() {
        return id;
    }

    public static Float getBalance() {
        return balance;
    }

    public logindata(String member_id, String member_card_id, String member_name, Integer member_gender, String member_birthday, String member_phone, String member_email, String member_address, Float member_balance) {
        id = member_id ;
        card = member_card_id;
        name = member_name;
        gender = member_gender;
        birthday = member_birthday;
        phone = member_phone;
        email = member_email;
        address = member_address;
        balance = member_balance;

    }


//    public static void showdata(){
//        AccountFragment.accountdata1.setText(name);
//        AccountFragment.accountdata2.setText(phone);
//        AccountFragment.accountdata3.setText(birthday);
//        AccountFragment.accountdata4.setText(email);
//        AccountFragment.accountdata5.setText(address);
//        AccountFragment.accountdata6.setText(balance.toString());
//    }
}
