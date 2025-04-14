package org.example.services;

import org.example.models.Match;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreboardService {
    List<Match> matches=new ArrayList<>();
    public void startMatch(String homeTeam, String awayTeam){
        if(homeTeam.equals(awayTeam)){
            throw new IllegalArgumentException("Home and away team cannot be the same");
        }
        matches.add(new Match(homeTeam,awayTeam));
    }

    public List<String> getTotal(){
        return matches.stream().map(Match::toString).collect(Collectors.toList());
    }
    public void updateScore(String homeTeam, String awayTeam,int homeTeamScore,int awayTeamScore){
        for(Match match:matches){
            if(homeTeamScore<0 || awayTeamScore<0){
                throw new IllegalArgumentException("Score cannot be negative");
            }
            if(match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam)){
                match.updateScore(homeTeamScore,awayTeamScore);
                return;
            }
        }
        throw new IllegalArgumentException("Match not found.");
    }
    public void finishMatch(String homeTeam, String awayTeam){
        boolean removed = matches.removeIf(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam));

        if (!removed) {
            throw new IllegalArgumentException("Match not found: " + homeTeam + " - " + awayTeam);
        }
    }
}
