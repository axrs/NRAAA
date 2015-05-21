package com.cadcoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {

    private static final String[] GRADES = {
            "Target Rifle A", "Target Rifle B", "Target Rifle C",
            "F Standard A", "F Standard B",
            "F Open FO", "F/TR A"
    };

    private static final int DISCIPLINE = 0;
    private static final int GRADE = 1;
    private static final int NAME = 4;
    private static final int SURNAME = 3;
    private static final int CLUB = 5;

    private static List<String> filenames = new LinkedList<String>();

    public static void main(String[] args) {

        Map<String, Grade> grades = new HashMap<String, Grade>();
        String parsingGrade = "";


        final File folder = new File("/users/xander/Desktop");
        listFilesForFolder(folder);

        for (String file : filenames) {
            System.out.println("Reading File: " + folder.getPath() + "/" + file);
            List<String[]> lines = CsvReader.readCSV(folder.getPath() + "/" + file);
            String[] line;
            int resultIndex = 0;

            for (int i = 1; i < lines.size(); i++) {
                line = lines.get(i);
                resultIndex = line.length - 1;

                //Note: Skipping first CSV Line.
                if (!parsingGrade.equals(Grade.NameFromDisciplineAndGrade(line[DISCIPLINE], line[GRADE]))) {
                    parsingGrade = Grade.NameFromDisciplineAndGrade(line[DISCIPLINE], line[GRADE]);

                    if (!grades.containsKey(parsingGrade))
                        grades.put(parsingGrade, new Grade(parsingGrade));
                    else
                        grades.get(parsingGrade).nextRange();
                }
                grades.get(parsingGrade).addShooterWithResult(line[NAME], line[SURNAME], line[CLUB], line[resultIndex]);

            }
            if (!parsingGrade.isEmpty()){
                grades.get(parsingGrade).balanceShooters();
            }
        }

        try {
            PrintWriter writer = new PrintWriter("results.csv", "UTF-8");
            for (String s : GRADES) {
                System.out.println(String.format("Writing Results for: %s", s));

                //Write title
                writer.println(String.format("\"%s\"",s));

                grades.get(s).sort();

                //Apply rankings
                int rank = 1;

                //Write CSV information
                for(String line : grades.get(s).csvLines()){
                    writer.println(String.format("%d,%s",rank,line));
                    rank++;
                }
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        notifyEnd();
    }

    private static void listFilesForFolder(final File folder) {

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                if (fileEntry.getName().contains(".csv"))
                    filenames.add(fileEntry.getName());
            }
        }
    }


    private static void notifyEnd() {
        System.out.println("Press Enter to close...");
        try {
            System.in.read();
        } catch (Exception e) {
            //do nothing
        }
    }
}
