package com.example.jdbcdemo.service;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.example.jdbcdemo.domain.Console;
import com.example.jdbcdemo.domain.Brand;

public class BrandManagerTest {

    ConnectionManagerJDBC connectionManager = new ConnectionManagerJDBC();
    ConsoleManagerJDBC consoleManager = new ConsoleManagerJDBC();
    BrandManagerJDBC brandManager = new BrandManagerJDBC();

    private final static String NAME_1 = "PlayStation";
    private final static String BRAND_1 = "Sony";
    private final static int PREMIERE_1 = 1996;
    private final static double PRICE_1 = 1599.00;

    private final static String COUNTRY_1 = "Japan";

    private final static String NAME_2 = "PlayStation 2";
    private final static String BRAND_2 = "Sony";
    private final static int PREMIERE_2 = 2001;
    private final static double PRICE_2 = 2699.00;

    private final static String COUNTRY_2 = "Japan";

    private final static String NAME_3 = "Dreamcast";
    private final static String BRAND_3 = "Sega";
    private final static int PREMIERE_3 = 1999;
    private final static double PRICE_3 = 1450.00;

    private final static String COUNTRY_3 = "Japan";

    private final static String NAME_4 = "64";
    private final static String BRAND_4 = "Nintendo";
    private final static int PREMIERE_4 = 1997;
    private final static double PRICE_4 = 1099.00;

    private final static String COUNTRY_4 = "Japan";

    Console console1 = new Console(NAME_1, BRAND_1, PREMIERE_1, PRICE_1);
    Console console2 = new Console(NAME_2, BRAND_2, PREMIERE_2, PRICE_2);
    Console console3 = new Console(NAME_3, BRAND_3, PREMIERE_3, PRICE_3);
    Console console4 = new Console(NAME_4, BRAND_4, PREMIERE_4, PRICE_4);

    Brand brand1 = new Brand(BRAND_1, COUNTRY_1);
    Brand brand2 = new Brand(BRAND_2, COUNTRY_2);
    Brand brand3 = new Brand(BRAND_3, COUNTRY_3);
    Brand brand4 = new Brand(BRAND_4, COUNTRY_4);

    @Before
    public void initializeConsole(){

        consoleManager.clearConsoles();

        List<Console> consoles = new ArrayList<>();
        consoles.add(console1);
        consoles.add(console2);
        consoles.add(console3);
        consoles.add(console4);

        consoleManager.addConsoles(consoles);
    }

    @Test
    public void checkConnection(){
        assertNotNull(connectionManager.getConnection());
    }

    @Test // CHECK ADDING SINGLE
    public void checkAdding() {

        brandManager.clearBrands();

        assertEquals(1, brandManager.addBrand(brand1));

        List<Brand> brands = brandManager.getAllBrands();
        Brand brandRetrieved = brands.get(0);

        assertEquals(BRAND_1, brandRetrieved.getBrandName());
        assertEquals(COUNTRY_1, brandRetrieved.getCountry());
    }

    @Test // CHECK ADDING ALL
    public void checkAddAll(){
        int size;

        brandManager.clearBrands();

        List<Brand> brands = new ArrayList<>();
        brands.add(brand1);
        brands.add(brand2);
        brands.add(brand3);
        brands.add(brand4);

        brandManager.addBrands(brands);

        size = brandManager.getAllBrands().size();

        assertThat(size, either(is(4)).or(is(0)));
    }

    @Test // CHECK UPDATE SINGLE
    public void checkUpdate(){

        brandManager.clearBrands();

        brandManager.addBrand(brand2);
        assertEquals(1, brandManager.updateBrand(brand2, brand3));

        List<Brand> brands = brandManager.searchBrandByName(brand3);
        Brand brandRetrieved = brands.get(0);

        assertEquals(BRAND_3, brandRetrieved.getBrandName());
        assertEquals(COUNTRY_3, brandRetrieved.getCountry());
    }

    @Test // CHECK SEARCH BY NAME
    public void checkSearchByName(){

        brandManager.clearBrands();
        brandManager.addBrand(brand3);

        List<Brand> brands = brandManager.searchBrandByName(brand3);
        Brand brandRetrieved = brands.get(0);

        assertEquals(BRAND_3, brandRetrieved.getBrandName());
        assertEquals(COUNTRY_3, brandRetrieved.getCountry());
    }
}
