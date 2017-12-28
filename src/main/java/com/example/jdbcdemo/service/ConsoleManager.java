package com.example.jdbcdemo.service;

import java.util.List;

import com.example.jdbcdemo.domain.Console;

public interface ConsoleManager {
	public int addConsole(Console console);
	public List<Console> getAllConsoles();

	public void addAllConsoles(List<Console> consoles);
}
