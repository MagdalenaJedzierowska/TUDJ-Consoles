package com.example.jdbcdemo.service;

import com.example.jdbcdemo.domain.Brand;

import java.util.List;

public interface BrandManager {

    public int addBrand(Brand brand);
    public int deleteBrands(List<Brand> brands);
    public int updateBrand(Brand brand, Brand newBrand);
    public void addBrands(List<Brand> brands);
    public List<Brand> getAllBrands();
    public List<Brand> searchBrandByName(Brand brand);
}
