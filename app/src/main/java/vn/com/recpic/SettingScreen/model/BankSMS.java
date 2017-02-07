package vn.com.recpic.SettingScreen.model;

/**
 * Created by Administrator on 2/7/2017.
 */

public class BankSMS {
    private int id;
    private String title;
    public BankSMS(){

    }

    public BankSMS(int  id, String title){
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
