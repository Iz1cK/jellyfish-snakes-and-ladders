package boardtests;

import static org.junit.Assert.*;
import org.junit.Test;
import Model.Board; // Make sure this import matches the package of your Board class
import Model.Player; // Adjust this import based on your actual Player class package
import Model.DIFFICULTY; // Adjust this import based on your actual DIFFICULTY enum package

public class BoardTests {

    @Test
    public void testBoardInitialization() {
        Board easyBoard = new Board(DIFFICULTY.EASY);
        assertEquals(7, easyBoard.getRows());
        assertEquals(7, easyBoard.getColumns());
        assertNotNull(easyBoard.getDiceOptions());
        assertFalse(easyBoard.getDiceOptions().isEmpty());
        
        Board mediumBoard = new Board(DIFFICULTY.MEDIUM);
        assertEquals(10, mediumBoard.getRows());
        assertEquals(10, mediumBoard.getColumns());
        assertNotNull(mediumBoard.getDiceOptions());
        assertFalse(mediumBoard.getDiceOptions().isEmpty());
        assertTrue(mediumBoard.getDiceOptions().size() > easyBoard.getDiceOptions().size());
        
        Board hardBoard = new Board(DIFFICULTY.HARD);
        assertEquals(13, hardBoard.getRows());
        assertEquals(13, hardBoard.getColumns());
        assertNotNull(hardBoard.getDiceOptions());
        assertFalse(hardBoard.getDiceOptions().isEmpty());
        assertTrue(hardBoard.getDiceOptions().size() > mediumBoard.getDiceOptions().size());
    }

    @Test
    public void testRollDice() {
        Board board = new Board(DIFFICULTY.MEDIUM);
        String diceResult = board.rollDice();
        assertNotNull(diceResult);
        assertTrue(board.getDiceOptions().contains(diceResult));
    }

    @Test
    public void testCheckWinner() {
        Board board = new Board(DIFFICULTY.EASY);
        Player player1 = new Player();
        Player player2 = new Player();
        board.getPlayers().add(player1);
        board.getPlayers().add(player2);
        board.getPlayersPositions().put(player1, 1);
        board.getPlayersPositions().put(player2, board.getRows() * board.getColumns());

        Player winner = board.checkWinner();
        assertNotNull(winner);
        assertEquals(player2, winner);
    }
}
