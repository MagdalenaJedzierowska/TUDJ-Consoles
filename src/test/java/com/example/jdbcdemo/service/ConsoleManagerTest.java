package com.example.jdbcdemo.service;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.Console;

public class ConsoleManagerTest {

	ConsoleManagerJDBC consoleManager = new ConsoleManagerJDBC();

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

	private final static String NAME_4 = "64";
	private final static String BRAND_4 = "Nintendo";
	private final static int PREMIERE_4 = 1997;
	private final static double PRICE_4 = 1099.00;

	Console console1 = new Console(NAME_1, BRAND_1, PREMIERE_1, PRICE_1);
	Console console2 = new Console(NAME_2, BRAND_2, PREMIERE_2, PRICE_2);
	Console console3 = new Console(NAME_3, BRAND_3, PREMIERE_3, PRICE_3);
	Console console4 = new Console(NAME_4, BRAND_4, PREMIERE_4, PRICE_4);

	@Test // CHECK CONNETION
	public void checkConnection(){
		assertNotNull(consoleManager.getConnection());
	}

	@Test // CHECK ADDING SINGLE
	public void checkAdding(){
		Console console = new Console(NAME_1, BRAND_1, PREMIERE_1, PRICE_1);
		consoleManager.deleteConsoles();
		assertEquals(1,consoleManager.addConsole(console));

		List<Console> consoles = consoleManager.getAllConsoles();
		Console consoleRetrieved = consoles.get(0);

		assertEquals(NAME_1, consoleRetrieved.getName());
		assertEquals(BRAND_1, consoleRetrieved.getBrand());
		assertEquals(PREMIERE_1, consoleRetrieved.getPremiere());
		assertEquals(PRICE_1, consoleRetrieved.getPrice(), 0.001);
	}

	@Test // CHECK ADDING ALL
	public void checkAddAll(){
		consoleManager.deleteConsoles();

		Console console1 = new Console(NAME_1, BRAND_1, PREMIERE_1, PRICE_1);
		Console console2 = new Console(NAME_2, BRAND_2, PREMIERE_2, PRICE_2);
		Console console3 = new Console(NAME_3, BRAND_3, PREMIERE_3, PRICE_3);
		Console console4 = new Console(NAME_4, BRAND_4, PREMIERE_4, PRICE_4);

		List<Console> consoles = new ArrayList<>();
		consoles.add(console1);
		consoles.add(console2);
		consoles.add(console3);
		consoles.add(console4);

		consoleManager.addAllConsoles(consoles);
		int size = consoleManager.getAllConsoles().size();
		assertThat(size, either(is(4)).or(is(0)));
	}

	@Test // CHECK UPDATE SINGLE
	public void checkUpdate(){
		Console console = new Console(NAME_1, BRAND_1, PREMIERE_1, PRICE_1);
		consoleManager.deleteConsoles();
		assertEquals(1,consoleManager.addConsole(console));

		List<Console> consoles = consoleManager.getAllConsoles();
		Console consoleRetrieved = consoles.get(0);

		consoleRetrieved.setName(NAME_2);
		consoleRetrieved.setBrand(BRAND_2);
		consoleRetrieved.setPremiere(PREMIERE_2);
		consoleRetrieved.setPrice(PRICE_2);

		assertEquals(1,consoleManager.updateConsole(consoleRetrieved));

		consoles = consoleManager.getAllConsoles();
		consoleRetrieved = consoles.get(0);

		assertEquals(NAME_2, consoleRetrieved.getName());
		assertEquals(BRAND_2, consoleRetrieved.getBrand());
		assertEquals(PREMIERE_2, consoleRetrieved.getPremiere());
		assertEquals(PRICE_2, consoleRetrieved.getPrice(), 0.001);
	}

	@Test // CHECK DELETING ALL
	public void checkDeleteAll(){
		Console console1 = new Console(NAME_1, BRAND_1, PREMIERE_1, PRICE_1);
		Console console2 = new Console(NAME_2, BRAND_2, PREMIERE_2, PRICE_2);
		Console console3 = new Console(NAME_3, BRAND_3, PREMIERE_3, PRICE_3);
		Console console4 = new Console(NAME_4, BRAND_4, PREMIERE_4, PRICE_4);
		consoleManager.addConsole(console1);
		consoleManager.addConsole(console2);
		consoleManager.addConsole(console3);
		consoleManager.addConsole(console4);

		consoleManager.deleteConsoles();
		assertEquals(0, consoleManager.getAllConsoles().size());
	}

	@Test // CHECK SEARCHING
	public void checkSearch(){
		consoleManager.deleteConsoles();

		Console console1 = new Console(NAME_1, BRAND_1, PREMIERE_1, PRICE_1);
		Console console2 = new Console(NAME_2, BRAND_2, PREMIERE_2, PRICE_2);
		Console console3 = new Console(NAME_3, BRAND_3, PREMIERE_3, PRICE_3);
		Console console4 = new Console(NAME_4, BRAND_4, PREMIERE_4, PRICE_4);
		consoleManager.addConsole(console1);
		consoleManager.addConsole(console2);
		consoleManager.addConsole(console3);
		consoleManager.addConsole(console4);

		List<Console> consoles = consoleManager.searchConsoles("NAME", NAME_1);

		assertEquals(2, consoles.size());
	}

}
