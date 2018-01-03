package com.example.shdemo.service;

import com.example.shdemo.domain.Console;
import com.example.shdemo.domain.Brand;

import java.util.List;

public interface SystemManager {

    void addConsole(Console console);
    void deleteConsole(Console console);
    void updateConsole(Long id, String name);
    /*public void addConsoles(List<Console> consoles);*/
    List<Console> getAllConsoles();
    Console searchConsoleByName(String name);

    void addBrand(Brand brand);
    void deleteBrand(Brand brand);
    void updateBrand(Brand brand, Brand newBrand);
    /*public void addBrands(List<Brand> brands);*/
    List<Brand> getAllBrands();
    Brand searchBrandByName(String brand_name);


}
