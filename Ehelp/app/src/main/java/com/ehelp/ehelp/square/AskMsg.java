package com.ehelp.ehelp.square;

import java.util.List;

/**
 * Created by UWTH on 2015/10/24.
 */
public class AskMsg {
    private int head;
    private String name;
    private String time;
    private String title;
    private int thumbnum;
    private int replynum;

    public AskMsg(int head, String name, String time, String title, int thumbnum, int replynum) {
        this.head = head;
        this.name = name;
        this.time = time;
        this.title = title;
        this.thumbnum = thumbnum;
        this.replynum = replynum;
    }

    public int getHead() {
        return head;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public int getThumbnum() {
        return thumbnum;
    }

    public int getReplynum() {
        return replynum;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setThumbnum(int thumbnum) {
        this.thumbnum = thumbnum;
    }

    public void setReplynum(int replynum) {
        this.replynum = replynum;
    }
}
