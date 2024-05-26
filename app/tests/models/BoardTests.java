package models;

import models.contracts.Board;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTests {

    @Test
    public void testCreateBoardWithValidName() {
        Board board = new BoardImpl("DevBoard");
        assertEquals("DevBoard", board.getName());
    }

    @Test
    public void testCreateBoardWithInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BoardImpl("D");
        });
    }

    @Test
    public void testAddTaskToBoard() {
        Board board = new BoardImpl("TestBoard");
        board.addTask(1);
        assertTrue(board.getTasks().contains(1));
    }
}
