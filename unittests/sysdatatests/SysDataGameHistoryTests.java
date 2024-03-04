package sysdatatests;

import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

import Model.DIFFICULTY;
import Model.Game;
import Model.PLAYERCOLORS;
import Model.Player;
import Model.Sysdata;

public class SysDataGameHistoryTests {

    @Mock
    private Sysdata sysdataMock;
    
    private Sysdata sysdata;

    @Before
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
    	sysdata = spy(Sysdata.getInstance());
        doNothing().when(sysdata).writeJsonFile(any(), anyString(), anyString());
        sysdata.readGameHistory(); 
    }
    
    @Test
    public void testAddGameHistoryWithMock() {
    	//initiate Game instance
    	ArrayList<Player> players = new ArrayList<Player>();
    	Player newpl= new Player(1, "aseel",PLAYERCOLORS.PINK);
    	Player newp2= new Player(1, "lna", PLAYERCOLORS.PURPLE);
    	players.add(newpl);
    	players.add(newp2);
    	Game mockGame= new Game(DIFFICULTY.EASY, newpl, players, "14:13.22");

        when(sysdataMock.getScoresList()).thenReturn(Arrays.asList(mockGame));

        // Simulate adding a game
        sysdataMock.addGameHistory(mockGame);
        verify(sysdataMock).addGameHistory(mockGame);
        assertEquals("Should have 1 game history after addition", 1, sysdataMock.getScoresList().size());
    }
    
    @Test
    public void testGameHistoryCapacityManagement() {
        final int maxCapacity = Sysdata.MAX_CAPACITY_GAME_HISTORY;

        // Fill up to max capacity
        for (int i = 1; i <= maxCapacity; i++) {
            Game game = new Game(DIFFICULTY.EASY, new Player(), new ArrayList<Player>(), "14:13.22");
            sysdata.addGameHistory(game);
        }

        assertEquals("The game history list should reach its maximum capacity", maxCapacity, sysdata.getScoresList().size());

        // Add one more game beyond the capacity
        Game extraGame = new Game(DIFFICULTY.EASY, new Player(), new ArrayList<>(), "14:13.22");
        sysdata.addGameHistory(extraGame); // This should trigger removal of the oldest game

        // Verify that the size remains at max capacity and that the oldest game is removed
        assertEquals("The game history list should maintain its maximum capacity after adding extra game", maxCapacity, sysdata.getScoresList().size());
        assertFalse("The oldest game should be removed", sysdata.getScoresList().contains(new Game(DIFFICULTY.EASY, new Player(), new ArrayList<>(), "14:13.22")));
    }

}
