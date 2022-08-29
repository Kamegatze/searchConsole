package org.example.algoritmSearch;

import org.example.cache.Cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Search implements SearchParent {
    Cache cache;

    String search;

    public Search(Cache cache, String search){
        this.cache = cache;
        this.search = search;
    }
    //поиск по кэшу
    public ArrayList<String[]> sercheCache(String[] cacheFile){
        //String[] cacheFile = cache.getCacheFile();
        ArrayList<String[]> searchingAtributs = new ArrayList<>();
        for (int i = 0; i < cacheFile.length; i++) {
            String[] line = cacheFile[i].split(",");
            if(line[cache.getNumberColum()].regionMatches(true, 1, search, 0, search.length())){
                searchingAtributs.add(line);
            }
        }
        return searchingAtributs;
    }


    //поиск из файла
    public ArrayList<String[]> searchCSV(ArrayList<String[]> serching) {
        int countLine = cache.getCountLine();
        if(countLine < 2500){
        }
        else  {
            try{
                BufferedReader bufferedReader = new BufferedReader(new FileReader(cache.getPathFile()), 256);

                //File file = new File(cache.getPathFile());
                //Scanner scanner = new Scanner(file);
                int countLinePotok = 0;
                String lineRead;
                while ((lineRead = bufferedReader.readLine()) != null){
                    countLinePotok++;
                    int count = countLine - 2499;
                    long time = System.currentTimeMillis();
                    String[] line = lineRead.split(",");
                    if(count >= countLinePotok){
                        if(line[cache.getNumberColum()].regionMatches(true, 1, search, 0, search.length())){
                            serching.add(line);
                        }
                    }
                    else{
                        break;
                    }
                }
                bufferedReader.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        return serching;
    }
    //сортировка данных
    private ArrayList<String[]> sort(ArrayList<String[]> arrayList){
        Collections.sort(arrayList, new Comparator<String[]>() {
            @Override
            public int compare(String[] strings, String[] t1) {
                return strings[cache.getNumberColum()].compareTo(t1[cache.getNumberColum()]);
            }
        });
        return arrayList;
    }
    //вывод данных
    @Override
    public void outputString(ArrayList<String[]> searching) {
        searching = sort(searching);
        for (String [] line:
             searching) {
            System.out.print(line[cache.getNumberColum()] + "[");
            for (int i = 0; i < line.length; i++) {
                if(i != cache.getNumberColum()){
                    System.out.print(line[i]);
                }
            }
            System.out.println("]");
        }
    }
}
