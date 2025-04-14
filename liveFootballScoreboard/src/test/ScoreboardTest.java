import org.example.services.ScoreboardService;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class ScoreboardTest {
    @Test
    public void testStartMatch() {
        ScoreboardService scoreboard = new ScoreboardService();

        scoreboard.startMatch("Mexico", "Canada");
        assertEquals(1, scoreboard.getTotal().size(), "Match should be started and added to the scoreboard");
        assertTrue(scoreboard.getTotal().getFirst().contains("Mexico"));
        assertTrue(scoreboard.getTotal().getFirst().contains("Canada"));
    }
    @Test
    public void testSameTeams() {
        ScoreboardService scoreboardService= new ScoreboardService();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboardService.startMatch("Spain", "Spain");
        });

        assertEquals("Home and away team cannot be the same", exception.getMessage());
    }

    @Test
    public void testUpdateScore() {
        ScoreboardService scoreboardService = new ScoreboardService();
        scoreboardService.startMatch("Spain", "Brazil");
        scoreboardService.updateScore("Spain", "Brazil", 2, 1);

        List<String> summary = scoreboardService.getTotal();
        assertTrue(summary.getFirst().contains("Spain 2 - Brazil 1"));
    }

    @Test
    public void testNegativeScore() {
        ScoreboardService scoreboardService = new ScoreboardService();
        scoreboardService.startMatch("Spain", "Brazil");

        // Attempt to set a negative result
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboardService.updateScore("Spain", "Brazil", -1, 2);
        });

        assertEquals("Score cannot be negative", exception.getMessage());

    }

    @Test
    public void testMatchNotFound() {
        ScoreboardService scoreboardService = new ScoreboardService();
        scoreboardService.startMatch("Spain", "Brazil");


        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            scoreboardService.updateScore("Spain", "France", 2, 1);
        });
        assertEquals("Match not found.", exception.getMessage());

    }

    @Test
    public void testFinishMatch(){
        ScoreboardService scoreboardService=new ScoreboardService();
        scoreboardService.startMatch("Germany","France");
        scoreboardService.startMatch("Spain", "Brazil");

        assertEquals(2, scoreboardService.getTotal().size());

        scoreboardService.finishMatch("Spain", "Brazil");

        // There should be one match
        List<String> total = scoreboardService.getTotal();
        assertEquals(1, total.size());
        assertFalse(total.getFirst().contains("Spain"));
    }

    @Test
    public void testFinishMatchNotFound() {
        ScoreboardService scoreboardService = new ScoreboardService();

        scoreboardService.startMatch("Spain", "Brazil");

        assertThrows(IllegalArgumentException.class, () -> {
            scoreboardService.finishMatch("Germany", "France");
        });
    }

    @Test
    public void testGetTotal() {
        ScoreboardService scoreboardService = new ScoreboardService();

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

        List<String> total = scoreboardService.getTotal();

        // Sequence check
        assertEquals("Uruguay 6 - Italy 6", total.get(0)); //newer with a total score of 12
        assertEquals("Spain 10 - Brazil 2", total.get(1));
        assertEquals("Mexico 0 - Canada 5", total.get(2));
        assertEquals("Argentina 3 - Australia 1", total.get(3)); // newer than Germany-France
        assertEquals("Germany 2 - France 2", total.get(4));
    }
}
