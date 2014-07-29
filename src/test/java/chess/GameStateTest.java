package chess;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
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
		state.reset();
		state.setCurrentPlayer(Player.Black);

		Pawn pawn = (Pawn) state.getPieceAt("g7");
		assertEquals(2, pawn.getMoves(new Position("g7"), state).size());
	}

	@Test
	public void testBishopMovesTest(){
		// Start the game
		testBoard();
		state.setCurrentPlayer(Player.Black);

		Bishop bishop = (Bishop) state.getPieceAt("g7");
		List<Position> moves = bishop.getMoves(new Position("g7"), state);
		assertEquals(4, moves.size());
		assertTrue(moves.get(0).equals(new Position("h8")));
		assertTrue(moves.get(1).equals(new Position("f8")));
		assertTrue(moves.get(2).equals(new Position("f6")));
		assertTrue(moves.get(3).equals(new Position("h6")));
	}

	@Test
	public void testKingMovesTest(){
		// Start the game
		testBoard();
		state.setCurrentPlayer(Player.Black);

		King king = (King) state.getPieceAt("g8");
		List<Position> moves = king.getMoves(new Position("g8"), state);
		assertEquals(2, moves.size());
		assertTrue(moves.get(0).equals(new Position("h8")));
		assertTrue(moves.get(1).equals(new Position("f8")));
	}

	@Test
	public void testRookMovesTest(){
		// Start the game
		testBoard();
		state.setCurrentPlayer(Player.Black);

		Rook rook = (Rook) state.getPieceAt("d8");
		List<Position> moves = rook.getMoves(new Position("d8"), state);

		assertEquals(5, moves.size());
		assertTrue(moves.get(0).equals(new Position("c8")));
		assertTrue(moves.get(1).equals(new Position("b8")));
		assertTrue(moves.get(2).equals(new Position("e8")));
		assertTrue(moves.get(3).equals(new Position("f8")));
		assertTrue(moves.get(4).equals(new Position("d7")));
	}

	@Test
	public void testQueenMovesTest(){
		// Start the game
		testBoard();
		state.setCurrentPlayer(Player.Black);

		Queen queen = (Queen) state.getPieceAt("h4");
		List<Position> moves = queen.getMoves(new Position("h4"), state);

		assertEquals(12, moves.size());
		assertTrue(moves.get(0).equals(new Position("h5")));
		assertTrue(moves.get(1).equals(new Position("g5")));
		assertTrue(moves.get(2).equals(new Position("f6")));
		assertTrue(moves.get(3).equals(new Position("e7")));
		assertTrue(moves.get(4).equals(new Position("g4")));
		assertTrue(moves.get(5).equals(new Position("f4")));
		assertTrue(moves.get(6).equals(new Position("e4")));
		assertTrue(moves.get(7).equals(new Position("g3")));
		assertTrue(moves.get(8).equals(new Position("f2")));
		assertTrue(moves.get(9).equals(new Position("e1")));
		assertTrue(moves.get(10).equals(new Position("h3")));
		assertTrue(moves.get(11).equals(new Position("h2")));
	}

	@Test
	public void testKnightMovesTest(){
		// Start the game
		testBoard();
		state.setCurrentPlayer(Player.White);

		Knight knight = (Knight) state.getPieceAt("d5");
		List<Position> moves = knight.getMoves(new Position("d5"), state);

		assertEquals(8, moves.size());
		assertTrue(moves.get(0).equals(new Position("e7")));
		assertTrue(moves.get(1).equals(new Position("c7")));
		assertTrue(moves.get(2).equals(new Position("e3")));
		assertTrue(moves.get(3).equals(new Position("c3")));
		assertTrue(moves.get(4).equals(new Position("f6")));
		assertTrue(moves.get(5).equals(new Position("b6")));
		assertTrue(moves.get(6).equals(new Position("f4")));
		assertTrue(moves.get(7).equals(new Position("b4")));
	}

	@Test
	public void testNonExistentPieceAndPositionMove(){
		// Start the game
		testBoard();
		state.setCurrentPlayer(Player.White);

		assertEquals("Piece does not exist at that location",state.validateMoveAndPlacePiece(state.getPieceAt("c1"), Validator.createPosition("c4")));
		assertEquals("Piece does not exist at that location",state.validateMoveAndPlacePiece(state.getPieceAt("asd"), Validator.createPosition("c4")));
		assertEquals("Piece does not exist at that location",state.validateMoveAndPlacePiece(state.getPieceAt(""), Validator.createPosition("c4")));


		assertEquals("New position is Illegal",state.validateMoveAndPlacePiece(state.getPieceAt("b1"), Validator.createPosition("c9")));
		assertEquals("New position is Illegal",state.validateMoveAndPlacePiece(state.getPieceAt("b1"), Validator.createPosition("asdas")));
		assertEquals("New position is Illegal",state.validateMoveAndPlacePiece(state.getPieceAt("b1"), Validator.createPosition("")));
		assertEquals("New position is Illegal",state.validateMoveAndPlacePiece(state.getPieceAt("b1"), Validator.createPosition(null)));
	}

	@Test
	public void testLegalMove(){
		// Start the game
		testBoard();
		state.setCurrentPlayer(Player.White);
		state.listAllPossibleMoves();

		state.validateMoveAndPlacePiece(state.getPieceAt("b1"), Validator.createPosition("c1"));
		assertNotNull(state.getPieceAt("c1"));
		assertEquals(state.getPieceAt("c1").getIdentifier(), 'k');
		assertNull(state.getPieceAt("b1"));
	}

	public void checkDrawBoardScenario(){
		state = new GameState();
		// White Pieces
		state.placePiece(new King(Player.White), new Position("h1"));

		// Black Pieces
		state.placePiece(new King(Player.Black), new Position("g8"));
		state.placePiece(new Queen(Player.Black), new Position("g3"));
		state.placePiece(new Pawn(Player.Black), new Position("h3"));
		state.placePiece(new Pawn(Player.Black), new Position("g4"));
		state.placePiece(new Pawn(Player.Black), new Position("f5"));
	}

	@Test
	public void testDrawScenario(){
		checkDrawBoardScenario();

		state.setCurrentPlayer(Player.White);
		state.listAllPossibleMoves();

		state.checkDrawStatus();
		assertTrue(state.draw);
	}

	@Test
	public void testDrawFailedScenario(){
		checkDrawBoardScenario();
		
		// White Pieces
		state.placePiece(new Rook(Player.White), new Position("e1"));
		
		state.setCurrentPlayer(Player.White);
		state.listAllPossibleMoves();

		state.checkDrawStatus();
		assertFalse(state.draw);
	}
	
	@Ignore
	@Test
	public void testCheckMateScenario(){
		checkDrawBoardScenario();

		state.placePiece(new Rook(Player.Black), new Position("e2"));
		
		state.setCurrentPlayer(Player.Black);
		state.listAllPossibleMoves();

		state.checkDrawStatus();
		assertFalse(state.draw);
		
		assertEquals("checkMate", state.validateMoveAndPlacePiece(state.getPieceAt("e2"), Validator.createPosition("e1")));
	}
	
	@Test
	public void testCheckMateSaveScenario(){
		checkDrawBoardScenario();
		
		state.placePiece(new Rook(Player.White), new Position("f2"));
		state.placePiece(new Rook(Player.Black), new Position("e2"));
		
		state.setCurrentPlayer(Player.Black);
		state.listAllPossibleMoves();

		state.checkDrawStatus();
		assertFalse(state.draw);
		
		assertEquals("", state.validateMoveAndPlacePiece(state.getPieceAt("e2"), Validator.createPosition("e1")));
	}
}
