package com.example.jdbcdemo.service;

import java.util.List;

import com.example.jdbcdemo.domain.Console;

public interface ConsoleManager {

    public int addConsole(Console console);
    public int deleteConsoles(List<Console> consoles);
    public int updateConsole(Console console, Console newConsole);
    public void addConsoles(List<Console> consoles);
    public List<Console> getAllConsoles();
    public List<Console> searchConsoleByName(Console console);
}
