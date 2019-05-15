package com.cadcoder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    private static final String DELIMETER = ",";

    public static List<String[]> readCSV(String file) {

        BufferedReader br = null;
        String line = "";
        List<String[]> lines = new ArrayList<String[]>();

        try {
            br = new BufferedReader(new FileReader(file));

            while ((line = br.readLine()) != null) {
                lines.add(line.split(DELIMETER));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return lines;
    }

}