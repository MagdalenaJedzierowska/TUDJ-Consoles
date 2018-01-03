package com.example.shdemo.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "console.all", query = "SELECT c FROM Console c"),
        @NamedQuery(name = "console.byName", query = "SELECT c FROM Console c WHERE c.name = :name")
})
public class Console {

    private Long id_console;
    private String name;
    private String brand;
    private int premiere;
    private double price;

    public Console() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId(){
        return id_console;
    }

    public void setId(Long id){
        this.id_console = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPremiere() {
        return premiere;
    }

    public void setPremiere(int premiere) {
        this.premiere = premiere;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

}