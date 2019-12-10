package com.example.e_petrol;

public class Posts {
    String mark,liter,date,day,month,year;
    public Posts(){

    }
    public Posts(String mark,String liter,String date){
        this.mark=mark;
        this.liter=liter;
        this.date=date;
    }
    public Posts(String mark,String liter,String date,String day,String month,String year){
        this.mark=mark;
        this.liter=liter;
        this.date=date;
        this.day=day;
        this.month=month;
        this.year=year;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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
