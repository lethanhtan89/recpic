package vn.com.recpic.SettingScreen.model;

/**
 * Created by Administrator on 2/8/2017.
 */

public class FAQ {
    private int id;
    private String content;
    public FAQ(){

    }

    public FAQ(int id, String content){
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
