package org.example.iservices;

import org.example.models.Match;

import java.util.List;

public interface IScoreboardService {
    void startMatch(String homeTeam, String awayTeam);
    List<String> getAll();
    List<Match> getNotFinishedMatches();
    void updateScore(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore);
    void finishMatch(String homeTeam, String awayTeam);
    boolean isMatchExisting(String homeTeam, String awayTeam);
}
