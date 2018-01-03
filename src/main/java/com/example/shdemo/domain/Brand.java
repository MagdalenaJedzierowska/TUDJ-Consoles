package com.example.shdemo.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "brand.all", query = "SELECT b FROM Brand b"),
        @NamedQuery(name = "brand.byBrandName", query = "SELECT b FROM Brand b WHERE b.brandName = :brandName")
})
public class Brand {

    private Long id_brand;
    private String brandName;
    private String country;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId(){
        return id_brand;
    }

    public void setId(Long id_brand){
        this.id_brand = id_brand;
    }

    public Brand(String brand_name1, String country1){
        this.brandName = brand_name1;
        this.country = country1;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brand_name){
        this.brandName = brand_name;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }
}
