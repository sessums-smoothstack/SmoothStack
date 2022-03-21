package com.smoothstack.controljobflow.models;


import java.io.Serializable;

public class Transaction implements Serializable {

    //Integer id;

    private String dte;

    private String county;

    
    private String area;

    
    private String number;

    
    private String ttl_area;

    private String avg_area;

    private String ttl_trans_amt;

    private String min_trans_amt;

    private String max_trans_amt;

    private String unit_price_min;

    private String unit_price_max;

    private String unit_price_med;

    private String unit_price_avg;

    private String unit_price_std;

    private String mnth;

    private String yar;

    private String index;

    public Transaction() {
    }

    public Transaction(String dte, String county, String area, String number, String ttl_area, String avg_area,
                       String ttl_trans_amt, String min_trans_amt, String max_trans_amt, String unit_price_min,
                       String unit_price_max, String unit_price_med, String unit_price_avg, String unit_price_std,
                       String mnth, String yar, String index) {
        this.dte = dte;
        this.county = county;
        this.area = area;
        this.number = number;
        this.ttl_area = ttl_area;
        this.avg_area = avg_area;
        this.ttl_trans_amt = ttl_trans_amt;
        this.min_trans_amt = min_trans_amt;
        this.max_trans_amt = max_trans_amt;
        this.unit_price_min = unit_price_min;
        this.unit_price_max = unit_price_max;
        this.unit_price_med = unit_price_med;
        this.unit_price_avg = unit_price_avg;
        this.unit_price_std = unit_price_std;
        this.mnth = mnth;
        this.yar = yar;
        this.index = index;
    }

    public String getDate() {
        return dte;
    }

    public void setDate(String dte) {
        this.dte = dte;
    }

    public String getCountry() {
        return county;
    }

    public void setCountry(String country) {
        this.county = country;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTtl_area() {
        return ttl_area;
    }

    public void setTtl_area(String ttl_area) {
        this.ttl_area = ttl_area;
    }

    public String getAvg_area() {
        return avg_area;
    }

    public void setAvg_area(String avg_area) {
        this.avg_area = avg_area;
    }

    public String getTtl_trans_amt() {
        return ttl_trans_amt;
    }

    public void setTtl_trans_amt(String ttl_trans_amt) {
        this.ttl_trans_amt = ttl_trans_amt;
    }

    public String getMin_trans_amt() {
        return min_trans_amt;
    }

    public void setMin_trans_amt(String min_trans_amt) {
        this.min_trans_amt = min_trans_amt;
    }

    public String getMax_trans_amt() {
        return max_trans_amt;
    }

    public void setMax_trans_amt(String max_trans_amt) {
        this.max_trans_amt = max_trans_amt;
    }

    public String getUnit_price_min() {
        return unit_price_min;
    }

    public void setUnit_price_min(String unit_price_min) {
        this.unit_price_min = unit_price_min;
    }

    public String getUnit_price_max() {
        return unit_price_max;
    }

    public void setUnit_price_max(String unit_price_max) {
        this.unit_price_max = unit_price_max;
    }

    public String getUnit_price_med() {
        return unit_price_med;
    }

    public void setUnit_price_med(String unit_price_med) {
        this.unit_price_med = unit_price_med;
    }

    public String getUnit_price_avg() {
        return unit_price_avg;
    }

    public void setUnit_price_avg(String unit_price_avg) {
        this.unit_price_avg = unit_price_avg;
    }

    public String getUnit_price_std() {
        return unit_price_std;
    }

    public void setUnit_price_std(String unit_price_std) {
        this.unit_price_std = unit_price_std;
    }

    public String getMonth() {
        return mnth;
    }

    public void setMonth(String mnth) {
        this.mnth = mnth;
    }

    public String getYear() {
        return yar;
    }

    public void setYear(String yar) {
        this.yar = yar;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date='" + dte + '\'' +
                ", country='" + county + '\'' +
                ", area='" + area + '\'' +
                ", number='" + number + '\'' +
                ", ttl_area='" + ttl_area + '\'' +
                ", avg_area='" + avg_area + '\'' +
                ", ttl_trans_amt='" + ttl_trans_amt + '\'' +
                ", min_trans_amt='" + min_trans_amt + '\'' +
                ", max_trans_amt='" + max_trans_amt + '\'' +
                ", unit_price_min='" + unit_price_min + '\'' +
                ", unit_price_max='" + unit_price_max + '\'' +
                ", unit_price_med='" + unit_price_med + '\'' +
                ", unit_price_avg='" + unit_price_avg + '\'' +
                ", unit_price_std='" + unit_price_std + '\'' +
                ", month='" + mnth + '\'' +
                ", year='" + yar + '\'' +
                ", index='" + index + '\'' +
                '}';
    }
}
