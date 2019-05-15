package com.cadcoder;


import java.util.ArrayList;
import java.util.List;

public class Shooter implements Htmlable, Comparable<Shooter> {

    private String name;
    private String surname;
    private String club;

    List<Shoot> shoots;

    public Shooter(String name, String surname) {
        init(name, surname, "");

    }

    public Shooter(String name, String surname, String club) {
        init(name, surname, club);
    }

    private void init(String name, String surname, String club) {
        this.name = name;
        this.surname = surname;
        this.club = club;
        shoots = new ArrayList<Shoot>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Shoot> getShoots() {
        return shoots;
    }

    public void setShoots(List<Shoot> shoots) {
        this.shoots = shoots;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public void addNullShoot() {
        shoots.add(new Shoot());
    }

    public void addShoot(String result) {
        shoots.add(new Shoot(result));
    }

    public Shoot aggregate() {
        int score = 0;
        int centers = 0;

        for (Shoot s : shoots) {
            score += s.getScore();
            centers += s.getCenters();
        }
        return new Shoot(score, centers);
    }

    @Override
    public String toHTML() {
        return null;
    }

    public String toCSV(){
        String line = String.format("%s,%s,%s,", surname, name, club);
        for(Shoot s : shoots){
            line += String.format("\"%s\",", s.toString());
        }
        line += String.format("\"%s\"",aggregate().toString());
        return line;
    }

    @Override
    public String toString() {
        return String.format("%s - %s %s", this.aggregate().toString(), name, surname);
    }

    @Override
    public int compareTo(Shooter o) {
        return this.aggregate().compareTo(o.aggregate());
    }


    public boolean equals(String name, String surname) {
        return this.name.equals(name) && this.surname.equals(surname);
    }
}
