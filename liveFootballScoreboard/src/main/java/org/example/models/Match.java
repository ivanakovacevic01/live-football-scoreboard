package org.example.models;

public class Match {
    private String homeTeam;
    private String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    private long startTime;
    private boolean isFinished=false;

    public Match(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = 0;
        this.awayTeamScore = 0;
        this.startTime = System.nanoTime();
    }

    public int getHomeScore() {
        return homeTeamScore;
    }

    public int getAwayScore() {
        return awayTeamScore;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public long getStartTime() {
        return startTime;
    }

    public void updateScore(int home, int away){
        this.homeTeamScore=home;
        this.awayTeamScore=away;
    }

    public int getTotalScore(){
        return this.homeTeamScore+this.awayTeamScore;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    @Override
    public String toString() {
        return homeTeam+" "+homeTeamScore+" - "+awayTeam+" "+awayTeamScore;
    }
}
