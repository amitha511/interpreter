package com.ofir.taboola;

import com.ofir.taboola.exceptions.InvalidCommandException;
import com.ofir.taboola.executor.CommandExecutor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("invalid number of arguments");
            return;
        }

        List<String> commands = extractCommandsFromFile(args[0]);
        if(commands == null) return;

        CommandExecutor executor = new CommandExecutor();
        for(String command : commands){
            try {
                executor.execute(command);
            } catch (InvalidCommandException e) {
                System.out.println("can't execute command: " + command);
                System.out.println(e.getMessage());
                return;
            }
        }

        System.out.println(executor.getState());
    }

    private static List<String> extractCommandsFromFile(String filePath){
        List<String> commands = null;
        try {
            commands = readFromFile(filePath);
        } catch (FileNotFoundException e){
            System.out.println(filePath + " not found");
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("IO error while reading from " + filePath);
            e.printStackTrace();
        }
        return commands;
    }

    private static List<String> readFromFile(String filepath) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader reader;

        reader = new BufferedReader((new FileReader(filepath)));
        String line = reader.readLine();
        while( line != null ){
            lines.add(line.trim());
            line = reader.readLine();
        }
        reader.close();
        return lines;
    }
}