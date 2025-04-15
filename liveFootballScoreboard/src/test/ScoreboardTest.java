import org.example.iservices.IScoreboardService;
import org.example.models.Match;
import org.example.services.ScoreboardService;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class ScoreboardTest {
    @Test
    public void startMatch() {
        IScoreboardService scoreboard = new ScoreboardService();

        scoreboard.startMatch("Mexico", "Canada");
        assertEquals(1, scoreboard.getAll().size(), "Match should be started and added to the scoreboard.");
        assertTrue(scoreboard.getAll().getFirst().contains("Mexico"));
        assertTrue(scoreboard.getAll().getFirst().contains("Canada"));
    }
    @Test
    public void sameTeamsThrows() {
        IScoreboardService scoreboardService= new ScoreboardService();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboardService.startMatch("Spain", "Spain");
        });

        assertEquals("Home and away team cannot be the same. ", exception.getMessage());
    }

    @Test
    public void emptyTeamNamesThrows() {
        IScoreboardService scoreboardService = new ScoreboardService();

        assertThrows(IllegalArgumentException.class, () -> scoreboardService.startMatch("", "Brazil"),
                "Should throw when home team name is empty");

        assertThrows(IllegalArgumentException.class, () -> scoreboardService.startMatch("Spain", " "),
                "Should throw when away team name is blank");

        assertThrows(IllegalArgumentException.class, () -> scoreboardService.startMatch("", ""),
                "Should throw when both team names are empty");

        assertThrows(IllegalArgumentException.class, () -> scoreboardService.startMatch(null, ""),
                "Should throw when home team name is null");
    }

    @Test
    public void matchExistsReturnsCorrectValue(){
        IScoreboardService scoreboardService=new ScoreboardService();
        scoreboardService.startMatch("Spain", "Brazil");

        // match exists
        assertTrue(scoreboardService.isMatchExisting("Spain", "Brazil"));
        //match doesn't exist
        assertFalse(scoreboardService.isMatchExisting("Germany", "France"));
    }

    @Test
    public void updateScore() {
        IScoreboardService scoreboardService = new ScoreboardService();
        scoreboardService.startMatch("Spain", "Brazil");
        scoreboardService.updateScore("Spain", "Brazil", 2, 1);

        List<String> matches = scoreboardService.getAll();
        assertTrue(matches.getFirst().contains("Spain 2 - Brazil 1"));
    }

    @Test
    public void updateScoreNegativeThrows() {
        IScoreboardService scoreboardService = new ScoreboardService();
        scoreboardService.startMatch("Spain", "Brazil");

        // Attempt to set a negative result
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboardService.updateScore("Spain", "Brazil", -1, 2);
        });

        assertEquals("Score cannot be negative.", exception.getMessage());

    }

    @Test
    public void updateScoreMatchNotFoundThrows() {
        IScoreboardService scoreboardService = new ScoreboardService();
        scoreboardService.startMatch("Spain", "Brazil");


        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboardService.updateScore("Spain", "France", 2, 1);
        });
        assertEquals("Match not found: Spain - France", exception.getMessage());

    }

    @Test
    public void updateScoreAfterFinishThrows() {
        IScoreboardService scoreboardService = new ScoreboardService();
        scoreboardService.startMatch("Spain", "Brazil");

        // Match is finished
        scoreboardService.finishMatch("Spain", "Brazil");

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            scoreboardService.updateScore("Spain", "Brazil", 2, 2);
        });
        assertEquals("Cannot update score after match is finished.", exception.getMessage());
    }

    @Test
    public void finishMatch(){
        IScoreboardService scoreboardService=new ScoreboardService();
        scoreboardService.startMatch("Germany","France");
        scoreboardService.startMatch("Spain", "Brazil");

        assertEquals(2, scoreboardService.getAll().size());

        scoreboardService.finishMatch("Spain", "Brazil");

        // There should be one match
        List<String> total = scoreboardService.getAll();
        assertEquals(1, total.size());
        assertFalse(total.getFirst().contains("Spain"));
    }

    @Test
    public void finishMatchNotFoundThrows() {
        IScoreboardService scoreboardService = new ScoreboardService();

        scoreboardService.startMatch("Spain", "Brazil");

        assertThrows(IllegalArgumentException.class, () -> {
            scoreboardService.finishMatch("Germany", "France");
        });
    }

    @Test
    public void getAll() {
        IScoreboardService scoreboardService = new ScoreboardService();

        scoreboardService.startMatch("Mexico", "Canada");
        scoreboardService.updateScore("Mexico", "Canada", 0, 5); // total = 5

        scoreboardService.startMatch("Spain", "Brazil");
        scoreboardService.updateScore("Spain", "Brazil", 10, 2); // total = 12

        scoreboardService.startMatch("Germany", "France");
        scoreboardService.updateScore("Germany", "France", 2, 2); // total = 4

        scoreboardService.startMatch("Uruguay", "Italy");
        scoreboardService.updateScore("Uruguay", "Italy", 6, 6); // total = 12

        scoreboardService.startMatch("Argentina", "Australia");
        scoreboardService.updateScore("Argentina", "Australia", 3, 1); // total = 4

        List<String> matches = scoreboardService.getAll();

        // Sequence check
        assertEquals("Uruguay 6 - Italy 6", matches.get(0)); //newer with a total score of 12
        assertEquals("Spain 10 - Brazil 2", matches.get(1));
        assertEquals("Mexico 0 - Canada 5", matches.get(2));
        assertEquals("Argentina 3 - Australia 1", matches.get(3)); // newer than Germany-France
        assertEquals("Germany 2 - France 2", matches.get(4));
    }

    @Test
    public void getEmptyListWhenNoMatches(){
        IScoreboardService scoreboardService=new ScoreboardService();
        List<String> matches=scoreboardService.getAll();
        assertTrue(matches.isEmpty(), "Expected empty list when there are no matches.");
    }
}
