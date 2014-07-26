package chess;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;

/**
 * Basic unit tests for the GameState class
 */
public class GameStateTest {

    private GameState state;

    @Before
    public void setUp() {
        state = new GameState();
    }

    @Test
    public void testStartsEmpty() {
        // Make sure all the positions are empty
        for (char col = Position.MIN_COLUMN; col <= Position.MAX_COLUMN; col++) {
            for (int row = Position.MIN_ROW; row <= Position.MAX_ROW; row++) {
                assertNull("All pieces should be empty", state.getPieceAt(String.valueOf(col) + row));
            }
        }
    }

    @Test
    public void testInitialGame() {
        // Start the game
        state.reset();

        // White should be the first player
        Player current = state.getCurrentPlayer();
        assertEquals("The initial player should be White", Player.White, current);

        // Spot check a few pieces
        Piece whiteRook = state.getPieceAt("a1");
        assertTrue("A rook should be at a1", whiteRook instanceof Rook);
        assertEquals("The rook at a1 should be owned by White", Player.White, whiteRook.getOwner());


        Piece blackQueen = state.getPieceAt("d8");
        assertTrue("A queen should be at d8", blackQueen instanceof Queen);
        assertEquals("The queen at d8 should be owned by Black", Player.Black, blackQueen.getOwner());
    }
      
    public void testBoard(){
    	state = new GameState();
    	// White Pieces
    	state.placePiece(new Rook(Player.White), new Position("d1"));
        state.placePiece(new Knight(Player.White), new Position("d5"));
        state.placePiece(new Queen(Player.White), new Position("d2"));
        state.placePiece(new King(Player.White), new Position("b1"));
        state.placePiece(new Rook(Player.White), new Position("h1"));
        state.placePiece(new Pawn(Player.White), new Position("a3"));
        state.placePiece(new Pawn(Player.White), new Position("b2"));
        state.placePiece(new Pawn(Player.White), new Position("e4"));
        state.placePiece(new Pawn(Player.White), new Position("f3"));
        state.placePiece(new Pawn(Player.White), new Position("g2"));
        state.placePiece(new Pawn(Player.White), new Position("h2"));
        state.placePiece(new Pawn(Player.White), new Position("h5"));

        // Black Pieces
        state.placePiece(new Rook(Player.Black), new Position("a8"));
        state.placePiece(new Queen(Player.Black), new Position("h4"));
        state.placePiece(new King(Player.Black), new Position("g8"));
        state.placePiece(new Bishop(Player.Black), new Position("g7"));
        state.placePiece(new Rook(Player.Black), new Position("d8"));
        state.placePiece(new Pawn(Player.Black), new Position("a7"));
        state.placePiece(new Pawn(Player.Black), new Position("b7"));
        state.placePiece(new Pawn(Player.Black), new Position("d6"));
        state.placePiece(new Pawn(Player.Black), new Position("e5"));
        state.placePiece(new Pawn(Player.Black), new Position("f7"));
        state.placePiece(new Pawn(Player.Black), new Position("g6"));
        state.placePiece(new Pawn(Player.Black), new Position("h7"));
    }
    @Test
    public void testPawnInitalMovesTest(){
    	// Start the game
        //state.reset();
    	testBoard();
    	state.setCurrentPlayer(Player.Black);
        
    	Pawn pawn = (Pawn) state.getPieceAt("g6");
    	assertEquals(2, pawn.getMoves(new Position("g6"), state).size());
    }
    
    @Test
    public void testBishopMovesTest(){
    	// Start the game
    	testBoard();
    	state.setCurrentPlayer(Player.Black);
        
    	Bishop bishop = (Bishop) state.getPieceAt("g7");
    	assertEquals(4, bishop.getMoves(new Position("g7"), state).size());
    }
    
    @Test
    public void testKingMovesTest(){
    	// Start the game
    	testBoard();
    	state.setCurrentPlayer(Player.Black);
    	
    	King king = (King) state.getPieceAt("g8");
    	assertEquals(2, king.getMoves(new Position("g8"), state).size());
    }
    
    @Test
    public void testRookMovesTest(){
    	// Start the game
    	testBoard();
    	state.setCurrentPlayer(Player.Black);
    	
    	Rook rook = (Rook) state.getPieceAt("d8");
    	assertEquals(5, rook.getMoves(new Position("d8"), state).size());
    }
}
