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

}
