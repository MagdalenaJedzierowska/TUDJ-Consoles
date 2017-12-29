package com.example.jdbcdemo.domain;

public class Brand {

    private int id_brand;
    private String brand_name;
    private String country;

    public Brand(){

    }

    public int getId(){
        return id_brand;
    }

    public void setId(int id_brand){
        this.id_brand = id_brand;
    }

    public Brand(String brand_name1, String country1){
        this.brand_name = brand_name1;
        this.country = country1;
    }

    public String getBrandName() {
        return brand_name;
    }

    public void setBrandName(String brand_name){
        this.brand_name = brand_name;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }
}
