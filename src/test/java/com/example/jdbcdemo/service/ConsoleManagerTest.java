package com.example.jdbcdemo.service;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.Console;
import com.example.jdbcdemo.domain.Brand;

public class ConsoleManagerTest {

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

	@Test
	public void checkConnection(){
		assertNotNull(connectionManager.getConnection());
	}

	@Test // CHECK ADDING SINGLE
	public void checkAdding() {

		consoleManager.clearConsoles();

		assertEquals(1, consoleManager.addConsole(console1));

		List<Console> consoles = consoleManager.getAllConsoles();
		Console consoleRetrieved = consoles.get(0);

		assertEquals(NAME_1, consoleRetrieved.getName());
		assertEquals(BRAND_1, consoleRetrieved.getBrand());
		assertEquals(PREMIERE_1, consoleRetrieved.getPremiere());
		assertEquals(PRICE_1, consoleRetrieved.getPrice(), 0.00001);
	}

	@Test // CHECK ADDING ALL
	public void checkAddAll(){
		int size;

		consoleManager.clearConsoles();

		List<Console> consoles = new ArrayList<>();
		consoles.add(console1);
		consoles.add(console2);
		consoles.add(console3);
		consoles.add(console4);

		consoleManager.addConsoles(consoles);

		size = consoleManager.getAllConsoles().size();

		assertThat(size, either(is(4)).or(is(0)));
	}

	@Test // CHECK UPDATE SINGLE
	public void checkUpdate(){

		consoleManager.clearConsoles();

		consoleManager.addConsole(console2);
		assertEquals(1, consoleManager.updateConsole(console2, console3));

		List<Console> consoles = consoleManager.searchConsoleByName(console3);
		Console consoleRetrieved = consoles.get(0);

		assertEquals(NAME_3, consoleRetrieved.getName());
		assertEquals(BRAND_3, consoleRetrieved.getBrand());
		assertEquals(PREMIERE_3, consoleRetrieved.getPremiere());
		assertEquals(PRICE_3, consoleRetrieved.getPrice(), 0.00001);
	}

	@Test // CHECK DELETING WITH FKs
	public void checkDeleteConsolesForeignKey(){

		int size;

		consoleManager.clearConsoles();
		brandManager.clearBrands();

		List<Console> consoles = new ArrayList<>();
		List<Brand> brands = new ArrayList<>();

		consoles.add(console1);
		consoles.add(console2);
		consoles.add(console3);
		consoles.add(console4);

		brands.add(brand1);
		brands.add(brand2);
		brands.add(brand3);
		brands.add(brand4);

		consoleManager.addConsoles(consoles);
		brandManager.addBrands(brands);

		size = consoleManager.deleteConsoles(consoles);

		assertThat(size, either(is(4)).or(is(0)));

		List<Brand> brandsReceived = brandManager.getAllBrands();

		size = brandsReceived.size();

		assertEquals(0, size);
	}

	@Test
	public void checkSearchByName(){

		consoleManager.clearConsoles();
		consoleManager.addConsole(console1);

		List<Console> consoles = consoleManager.searchConsoleByName(console1);
		Console consoleRetrieved = consoles.get(0);

		assertEquals(NAME_1, consoleRetrieved.getName());
		assertEquals(BRAND_1, consoleRetrieved.getBrand());
		assertEquals(PREMIERE_1, consoleRetrieved.getPremiere());
		assertEquals(PRICE_1, consoleRetrieved.getPrice(), 0.00001);
	}
}
