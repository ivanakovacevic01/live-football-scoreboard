package org.example;

import org.example.iservices.IScoreboardService;
import org.example.services.ScoreboardService;

public class Main {
    public static void main(String[] args) {
        IScoreboardService scoreboardService=new ScoreboardService();
        //start matches
        scoreboardService.startMatch("Mexico", "Canada");
        scoreboardService.startMatch("Spain", "Brazil");
        scoreboardService.startMatch("Germany", "France");
        scoreboardService.startMatch("Uruguay", "Italy");
        scoreboardService.startMatch("Argentina", "Australia");

        //update scores
        scoreboardService.updateScore("Mexico", "Canada",0,5);
        scoreboardService.updateScore("Spain", "Brazil",10,2);
        scoreboardService.updateScore("Germany", "France",2,2);
        scoreboardService.updateScore("Uruguay", "Italy",6,6);
        scoreboardService.updateScore("Argentina", "Australia",3,1);

        //reviews
        int i=1;
        for(int j=0;j<scoreboardService.getAll().size();j++){
            System.out.println(i++ + ". "+scoreboardService.getAll().get(j));
        }

    }
}