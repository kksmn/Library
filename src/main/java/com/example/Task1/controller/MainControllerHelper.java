package com.example.Task1.controller;

import com.example.Task1.сommand.ICommand;
import com.example.Task1.сommand.impl.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class MainControllerHelper {
    private static MainControllerHelper instance = null;
    private static final String COMMAND = "command";

    HashMap<String, ICommand> commands = new HashMap<String, ICommand>();

    private MainControllerHelper() {
        commands.put("addBook", new CommandAddBook());
        commands.put("addReader", new CommandAddReader());
        commands.put("findBook", new CommandFindBook());
        commands.put("getBook", new CommandGetBook());
        commands.put("getBookTable", new CommandGetBookTable());
        commands.put("getReader", new CommandGetReader());
        commands.put("getReaderTable", new CommandGetReaderTable());
        commands.put("returnBook", new CommandReturnBook());
        commands.put("addAuthor", new CommandAddAuthor());
        commands.put("checkUser", new CommandCheckUser());
        commands.put("getSearchPage", new CommandGetSearchPage());
        commands.put("getSearch", new CommandGetSearch());
    }

    public ICommand getCommand(HttpServletRequest request) {
        ICommand command = null;
        Object req=request.getParameterMap();
        String commandString=request.getParameter("command");
        command = commands.get(commandString);

        return command;
    }
    public static MainControllerHelper getInstance() {
        if (instance == null) {
            instance = new MainControllerHelper();
        }
        return instance;
    }
}
