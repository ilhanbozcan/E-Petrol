package com.example.e_petrol;

public class Posts {
    String mark,liter,date;
    public Posts(){

    }
    public Posts(String mark,String liter,String date){
        this.mark=mark;
        this.liter=liter;
        this.date=date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getLiter() {
        return liter;
    }

    public void setLiter(String liter) {
        this.liter = liter;
    }
}
