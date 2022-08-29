package org.example.cache;

import java.io.*;
import java.util.*;

public class Cache implements CacheParent {
    private final String[] cacheFile;
    private final String pathFile;
    private final int numberColum;
    private final int countLine;

    @Override
    public int getCountLine() {
        return countLine;
    }
    @Override
    public int getNumberColum() {
        return numberColum;
    }
    @Override
    public String getPathFile() {
        return pathFile;
    }
    @Override
    public String[] getCacheFile() {
        return cacheFile;
    }

    public Cache(String pathFile, int numberColum) throws IOException {
        this.pathFile = pathFile;
        this.numberColum = numberColum;
        this.countLine = countLine();
        this.cacheFile = cacheReadCVM();
    }
    // подсчёт количества строк
    private int countLine() throws IOException{
        //int countLine = 0;
        //File file = new File(pathFile);
        //try {
        //    Scanner scanner = new Scanner(file);
        //    while (scanner.hasNextLine())
        //    {
        //       String line = scanner.nextLine();
        //        countLine++;
        //    }
        //    scanner.close();
        //}
        //catch (Exception e){
         //   e.printStackTrace();
        //}
        //return countLine;
        try (var lnr = new LineNumberReader(new FileReader(pathFile))) {
            while (lnr.readLine() != null) ;
            return lnr.getLineNumber();
        }
    }
    //загрузка в кэш данных
    private String[] cacheReadCVM(){
        int countLine = getCountLine();
        String[] cacheFile = new String[2500];
        try{
            BufferedReader bufferedReader = new BufferedReader( new FileReader(pathFile), 256);
            if(countLine < 2500){
                for (int i = 0; i < countLine; i++) {
                    String line = bufferedReader.readLine();
                    cacheFile[i] = line;
                }
            }
            else{
                int count = 0;
                int i = 0;
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    count++;
                    if(count >= countLine - 2499 && count <= countLine){
                        cacheFile[i] = line;
                        i++;
                    }
                }
            }
            bufferedReader.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return cacheFile;
    }
}
