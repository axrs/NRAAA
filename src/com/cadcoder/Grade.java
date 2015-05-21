package com.cadcoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grade {

    private String name;
    private List<Shooter> shooters;
    private int currentRange = 0;

    public Grade(String name) {
        this.name = name;
        shooters = new ArrayList<Shooter>();
    }


    public Grade(String discipline, String club) {
        this.name = NameFromDisciplineAndGrade(discipline, club);
        shooters = new ArrayList<Shooter>();
    }

    public static String NameFromDisciplineAndGrade(String discipline, String grade) {
        return String.format("%s %s", discipline, grade);
    }


    public void addShooterWithResult(String name, String surname, String club, String result) {
        for (Shooter s : shooters) {
            if (s.equals(name, surname)) {
                s.addShoot(result);
                return;
            }
        }
        Shooter s = new Shooter(name, surname, club);
        //Null shooter as required
        for (int i = 0; i < currentRange; i++) {
            s.addNullShoot();
        }
        s.addShoot(result);
        shooters.add(s);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void nextRange() {
        currentRange++;
    }

    public void balanceShooters() {
        for (Shooter s : shooters) {
            for (int i = s.getShoots().size(); i <= currentRange; i++) {
                s.addNullShoot();
            }
        }
    }

    public void sort() {
        Collections.sort(shooters, Collections.reverseOrder());
    }

    public boolean equals(String discipline, String grade) {
        return this.getName().equals(NameFromDisciplineAndGrade(discipline, grade));
    }

    public void print() {
        for (Shooter s : shooters) {
            System.out.println(s.toCSV());
        }
    }

    public List<String> csvLines(){
        List<String> lines = new ArrayList<String>();
        for (Shooter s : shooters){
            lines.add(s.toCSV());
        }
        return lines;
    }
}
