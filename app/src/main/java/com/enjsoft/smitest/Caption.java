package com.enjsoft.smitest;

public class Caption {

    long caption_time = 0;
    String caption_text = "";

    public Caption(long time, String text){
        caption_text = text;
        caption_time = time;
    }

    public long getCaption_time() {
        return caption_time;
    }

    public void setCaption_time(long caption_time) {
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
