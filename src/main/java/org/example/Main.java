package org.example;

import org.example.algoritmSearch.Search;
import org.example.cache.Cache;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        try {
            int colum = Integer.parseInt(args[0]) - 1;
            //int colum = 1;
            if(colum < 0){
                System.out.println("Введите значение больше 0");
            }
            else {
                Cache cache = new Cache("//home//shirayev//Документы//searchConsole//src//main//java//org//example//airports.csv", colum);
                String[] cacheFile = cache.getCacheFile();
                while(true){
                    System.out.println("Введите строку: ");
                    Scanner scanner = new Scanner(System.in);
                    String searchString = scanner.nextLine();
                    if(searchString.isEmpty()){
                        System.out.println("Пустая строка, введите строку для поиска!!!");
                    }
                    else{
                        if(searchString.equals("!quit")){
                            break;
                        }
                        Search search = new Search(cache, searchString);
                        ArrayList<String[]> searching = null;
                        long time = System.currentTimeMillis();
                        searching = search.sercheCache(cacheFile);
                        searching = search.searchCSV(searching);
                        time = System.currentTimeMillis() - time;

                        search.outputString(searching);
                        System.out.println("Количество найденных строк: " + searching.size() + " затраченное время на поиск: " + time + " мс");
                    }
                }
            }

        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Введите нужную колонку для поиска");
        }
    }
}