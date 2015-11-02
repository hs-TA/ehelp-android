package com.ehelp.ehelp.square;

/**
 * Created by UWTH on 2015/10/25.
 */
public class Reply {
    private int head;
    private String name;
    private String time;
    private String content;

    public Reply(int head, String name, String time, String content) {
        this.head = head;
        this.name = name;
        this.time = time;
        this.content = content;
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

    public String getContent() {
        return content;
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

    public void setContent(String content) {
        this.content = content;
    }
}
