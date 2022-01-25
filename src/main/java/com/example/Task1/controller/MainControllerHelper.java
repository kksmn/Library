package com.example.Task1.controller;

import com.example.Task1.сommand.ICommand;
import com.example.Task1.сommand.impl.Library.*;
import com.example.Task1.сommand.impl.Page.CommandGetBookTable;
import com.example.Task1.сommand.impl.Page.CommandGetReaderTable;
import com.example.Task1.сommand.impl.Page.CommandGetSearchPage;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class MainControllerHelper {
    private static MainControllerHelper instance = null;
    private static final String COMMAND = "command";

    HashMap<String, ICommand> commands = new HashMap<String, ICommand>();

    private MainControllerHelper() {
        commands.put("addBook", new CommandAddBook());
        commands.put("addReader", new CommandAddReader());
        commands.put("returnBook", new CommandReturnBook());
        commands.put("getBook", new CommandGetBook());
        commands.put("getBookTable", new CommandGetBookTable());
        commands.put("getReader", new CommandGetReader());
        commands.put("getReaderTable", new CommandGetReaderTable());
        commands.put("returnBook", new CommandReturnBook());
        commands.put("addAuthor", new CommandAddAuthor());
        commands.put("checkUser", new CommandCheckUser());
        commands.put("getSearchPage", new CommandGetSearchPage());
        commands.put("checkUser", new CommandCheckUser());
    }

    public ICommand getCommand(HttpServletRequest request) {
        ICommand command = null;
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
