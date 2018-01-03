package com.example.shdemo.service;

import com.example.shdemo.domain.Console;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class SystemManagerTest {

    @Autowired
    SystemManager systemManager;

    private final static String NAME_1 = "PlayStation";
    private final static String BRAND_1 = "Sony";
    private final static int PREMIERE_1 = 1996;
    private final static double PRICE_1 = 1599.00;

    private final static String NAME_2 = "PlayStation 2";
    private final static String BRAND_2 = "Sony";
    private final static int PREMIERE_2 = 2001;
    private final static double PRICE_2 = 2699.00;

    private final static String NAME_3 = "Dreamcast";
    private final static String BRAND_3 = "Sega";
    private final static int PREMIERE_3 = 1999;
    private final static double PRICE_3 = 1450.00;

    @Test
    public void addConsoleCheck() {

        List<Console> consolesRetrieved = systemManager.getAllConsoles();

        for (Console console : consolesRetrieved) {
            if (console.getName().equals(NAME_1)) {
                systemManager.deleteConsole(console);
            }
        }

        Console console = new Console();
        console.setName(NAME_1);
        console.setBrand(BRAND_1);
        console.setPremiere(PREMIERE_1);
        console.setPrice(PRICE_1);

        systemManager.addConsole(console);

        Console consoleRetrieved = systemManager.searchConsoleByName(NAME_1);

        assertEquals(NAME_1, consoleRetrieved.getName());
        assertEquals(BRAND_1, consoleRetrieved.getBrand());
        assertEquals(PREMIERE_1, consoleRetrieved.getPremiere());
        assertEquals(PRICE_1, consoleRetrieved.getPrice(), 0.00001);
    }

    @Test
    public void updateConsoleNameCheck() {

        Console console = new Console();

        console.setName(NAME_2);
        console.setBrand(BRAND_2);
        console.setPremiere(PREMIERE_2);
        console.setPrice(PRICE_2);

        systemManager.addConsole(console);
        systemManager.updateConsole(console.getId(), NAME_1);

        Console consoleRetrieved = systemManager.searchConsoleByName(NAME_1);

        assertEquals(NAME_1, consoleRetrieved.getName());
    }

    @Test
    public void deleteConsoleCheck() {

        Console console = new Console();

        console.setName(NAME_3);
        console.setBrand(BRAND_3);
        console.setPremiere(PREMIERE_3);
        console.setPrice(PRICE_3);

        systemManager.addConsole(console);
        systemManager.deleteConsole(console);

        assertEquals(0,systemManager.getAllConsoles().size());
    }
}