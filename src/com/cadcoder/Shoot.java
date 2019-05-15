package com.cadcoder;


public class Shoot implements Comparable<Shoot> {

    private int score;
    private int centers;

    public Shoot(){

    }

    public Shoot(String result){
        String[] parts = result.split("\\.");

        if (parts.length > 1){
            setCenters(Integer.parseInt(parts[1]));
            setScore(Integer.parseInt(parts[0]));
        }else{
            setScore(Integer.parseInt(result));
        }
    }

    public Shoot(int score, int centers) {
        this.score = score;
        this.centers = centers;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCenters() {
        return centers;
    }

    public void setCenters(int centers) {
        this.centers = centers;
    }

    @Override
    public String toString() {
        return String.format("%d.%03d",score,centers);
    }

    @Override
    public int compareTo(Shoot o) {
        if (this.getScore() == o.getScore() && this.getCenters() == o.getCenters()){
            //equals
            return 0;
        }else if (this.getScore() > o.getScore() || //Greater score
                (this.getScore() == o.getScore() && this.getCenters() > o.getCenters())){ //Same score, more centers
            return 1;
        }

        //All other comparisons are less
        return -1;
    }
}
