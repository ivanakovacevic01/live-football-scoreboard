package org.example.services;

import org.example.models.Match;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScoreboardService {
    List<Match> matches=new ArrayList<>();
    public void startMatch(String homeTeam, String awayTeam){
        if (homeTeam == null || awayTeam == null || homeTeam.trim().isEmpty() || awayTeam.trim().isEmpty()) {
            throw new IllegalArgumentException("Team names cannot be null or empty.");
        }
        else if(homeTeam.equals(awayTeam)){
            throw new IllegalArgumentException("Home and away team cannot be the same. ");
        }
        matches.add(new Match(homeTeam,awayTeam));
    }

    public List<String> getAll(){
        if(getNotFinishedMatches().isEmpty())
            return List.of();
        return getNotFinishedMatches().stream().sorted(Comparator.comparingInt(Match::getTotalScore).thenComparing(Match::getStartTime).reversed()).map(Match::toString).collect(Collectors.toList());
    }
    public List<Match> getNotFinishedMatches(){
        return matches.stream().filter(match -> !match.isFinished()).toList();
    }
    public void updateScore(String homeTeam, String awayTeam,int homeTeamScore,int awayTeamScore){
        for(Match match:matches){
            if(homeTeamScore<0 || awayTeamScore<0){
                throw new IllegalArgumentException("Score cannot be negative.");
            }
            else if(match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam)){
                if (match.isFinished()) {
                    throw new IllegalStateException("Cannot update score after match is finished.");
                }
                match.updateScore(homeTeamScore,awayTeamScore);
                return;
            }
        }
        throw new IllegalArgumentException("Match not found.");
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        Match matchToFinish = matches.stream()
                .filter(match -> match.getHomeTeam().equals(homeTeam) && match.getAwayTeam().equals(awayTeam))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Match not found: " + homeTeam + " - " + awayTeam));

        if (matchToFinish.isFinished()) {
            throw new IllegalStateException("Match is already finished.");
        }

        matchToFinish.setFinished(true);
    }


    public boolean matchExists(String homeTeam, String awayTeam){
        for(Match m:matches){
            if(m.getHomeTeam().equals(homeTeam) && m.getAwayTeam().equals(awayTeam))
                return true;
        }
        return false;
    }
}
