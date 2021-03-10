package com.enjsoft.smitest;

public class Caption {

    int caption_time = 0;
    String caption_text = "";

    public Caption() {
    }

    public int getCaption_time() {
        return caption_time;
    }

    public void setCaption_time(int caption_time) {
        this.caption_time = caption_time;
    }

    public String getCaption_text() {
        return caption_text;
    }

    public void setCaption_text(String caption_text) {
        this.caption_text = caption_text;
    }

    @Override
    public String toString() {
        return "Caption{" +
                "caption_time=" + caption_time +
                ", caption_text='" + caption_text + '\'' +
                '}';
    }
}
