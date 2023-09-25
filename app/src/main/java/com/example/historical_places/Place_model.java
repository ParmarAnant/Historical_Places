package com.example.historical_places;

public class Place_model {
    int img_place;
    String txt_place, txt_place_state, txt_place_description, txt_place_url, txt_latitude, txt_longitude;

    public int getImg_place() {
        return img_place;
    }

    public void setImg_place(int img_place) {
        this.img_place = img_place;
    }

    public String getTxt_place() {
        return txt_place;
    }

    public void setTxt_place(String txt_place) {
        this.txt_place = txt_place;
    }

    public String getTxt_place_state() {
        return txt_place_state;
    }

    public void setTxt_place_state(String txt_place_state) {
        this.txt_place_state = txt_place_state;
    }

    public String getTxt_place_description() {
        return txt_place_description;
    }

    public void setTxt_place_description(String txt_place_description) {
        this.txt_place_description = txt_place_description;
    }

    public String getTxt_place_url() {
        return txt_place_url;
    }

    public void setTxt_place_url(String txt_place_url) {
        this.txt_place_url = txt_place_url;
    }

    public String getTxt_latitude() {
        return txt_latitude;
    }

    public void setTxt_latitude(String txt_latitude) {
        this.txt_latitude = txt_latitude;
    }

    public String getTxt_longitude() {
        return txt_longitude;
    }

    public void setTxt_longitude(String txt_longitude) {
        this.txt_longitude = txt_longitude;
    }

    public Place_model(int img_place, String txt_place,String txt_place_state, String txt_place_description, String txt_place_url, String txt_latitude, String txt_longitude) {
        this.img_place = img_place;
        this.txt_place = txt_place;
        this.txt_place_state = txt_place_state;
        this.txt_place_description = txt_place_description;
        this.txt_place_url = txt_place_url;
        this.txt_latitude = txt_latitude;
        this.txt_longitude = txt_longitude;
    }
}
