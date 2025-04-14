package org.example.services;

import org.example.models.Match;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreboardService {
    List<Match> matches=new ArrayList<>();
    public void startMatch(String homeTeam, String awayTeam){
        matches.add(new Match(homeTeam,awayTeam));
    }

    public List<String> getTotal(){
        return matches.stream().map(Match::toString).collect(Collectors.toList());
    }

}
