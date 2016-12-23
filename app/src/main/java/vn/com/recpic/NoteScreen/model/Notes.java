package vn.com.recpic.NoteScreen.model;

import java.io.Serializable;

/**
 * Created by Administrator on 23/12/2016.
 */

public class Notes implements Serializable{
    private int id;
    private String title, content, time;

    public Notes(){

    }

    public Notes(String title, String content){
        this.title = title;
        this.content = content;
    }

    public Notes(int id, String title, String content, String time){
        this.id = id;
        this.title = title;
        this.content = content;
        this.time = time;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
