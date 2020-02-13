package com.project.MyMovie.data;

public class CommentData  {
    String name;
    String mention;

    public CommentData(String name, String mention){
        this.name = name;
        this.mention = mention;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMention() {
        return mention;
    }

    public void setMention(String mention) {
        this.mention = mention;
    }
}
