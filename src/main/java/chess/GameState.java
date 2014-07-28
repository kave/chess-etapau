package chess;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;

/**
 * Class that represents the current state of the game.  Basically, what pieces are in which positions on the
 * board.
 */
public class GameState {

	/**
	 * The current player
	 */
	private Player currentPlayer = Player.White;

	/**
	 * A map of board positions to pieces at that position
	 */
	private Map<Position, Piece> positionToPieceMap;

	/**
	 * Create the game state.
	 */
	public GameState() {
		positionToPieceMap = new HashMap<Position, Piece>();
	}

	public boolean checkMate;
	
	public boolean draw;
	
	/**
	 * Call to initialize the game state into the starting positions
	 */
	public void reset() {
		// White Pieces
		placePiece(new Rook(Player.White), new Position("a1"));
		placePiece(new Knight(Player.White), new Position("b1"));
		placePiece(new Bishop(Player.White), new Position("c1"));
		placePiece(new Queen(Player.White), new Position("d1"));
		placePiece(new King(Player.White), new Position("e1"));
		placePiece(new Bishop(Player.White), new Position("f1"));
		placePiece(new Knight(Player.White), new Position("g1"));
		placePiece(new Rook(Player.White), new Position("h1"));
		placePiece(new Pawn(Player.White), new Position("a2"));
		placePiece(new Pawn(Player.White), new Position("b2"));
		placePiece(new Pawn(Player.White), new Position("c2"));
		placePiece(new Pawn(Player.White), new Position("d2"));
		placePiece(new Pawn(Player.White), new Position("e2"));
		placePiece(new Pawn(Player.White), new Position("f2"));
		placePiece(new Pawn(Player.White), new Position("g2"));
		placePiece(new Pawn(Player.White), new Position("h2"));

		// Black Pieces
		placePiece(new Rook(Player.Black), new Position("a8"));
		placePiece(new Knight(Player.Black), new Position("b8"));
		placePiece(new Bishop(Player.Black), new Position("c8"));
		placePiece(new Queen(Player.Black), new Position("d8"));
		placePiece(new King(Player.Black), new Position("e8"));
		placePiece(new Bishop(Player.Black), new Position("f8"));
		placePiece(new Knight(Player.Black), new Position("g8"));
		placePiece(new Rook(Player.Black), new Position("h8"));
		placePiece(new Pawn(Player.Black), new Position("a7"));
		placePiece(new Pawn(Player.Black), new Position("b7"));
		placePiece(new Pawn(Player.Black), new Position("c7"));
		placePiece(new Pawn(Player.Black), new Position("d7"));
		placePiece(new Pawn(Player.Black), new Position("e7"));
		placePiece(new Pawn(Player.Black), new Position("f7"));
		placePiece(new Pawn(Player.Black), new Position("g7"));
		placePiece(new Pawn(Player.Black), new Position("h7"));
	}

	/**
	 * Get the piece at the position specified by the String
	 * @param colrow The string indication of position; i.e. "d5"
	 * @return The piece at that position, or null if it does not exist.
	 */
	public Piece getPieceAt(String colrow) {
		Position position = new Position(colrow);
		return getPieceAt(position);
	}

	/**
	 * Get the piece at a given position on the board
	 * @param position The position to inquire about.
	 * @return The piece at that position, or null if it does not exist.
	 */
	public Piece getPieceAt(Position position) {
		return positionToPieceMap.get(position);
	}

	/**
	 * Method to place a piece at a given position, and save that position to the piece.
	 * @param piece The piece to place
	 * @param position The position
	 */
	public void placePiece(Piece piece, Position position) {	
		positionToPieceMap.put(position, piece);
		positionToPieceMap.get(position).setCurrentPosition(position);
	}

	/**
	 * List all possible moves for current player
	 */
	public List<String> listAllPossibleMoves(){
		List<String> moves = new ArrayList<String>();
		legalMoves = new HashMap<Piece, List<Position>>();

		for(Position p :positionToPieceMap.keySet()){
			Piece piece = positionToPieceMap.get(p);
			if(piece.getOwner() == currentPlayer){
				List<Position> pieceMoves = piece.getMoves(p, this);
				if(!pieceMoves.isEmpty())
					legalMoves.put(piece, new ArrayList<Position>());

				for(Position move : pieceMoves){
					moves.add(p.toString() + " " + move.toString());
					legalMoves.get(piece).add(move);
				}
			}
			else{
				if(piece.getIdentifier() == 'k' || piece.getIdentifier() == 'K')
					opposingTeamKing = (King) piece;
			}
		}

		return moves;
	}

	/**
	 * Pieces that have available moves
	 */
	protected Map<Piece, List<Position>> legalMoves;

	public String validateMoveAndPlacePiece(Piece piece, Position position){
		if(piece == null)
			return "Piece does not exist at that location";
		if(position == null)
			return "New position is out of board boards";

		for(Position p: legalMoves.get(piece)){
			if(position.equals(p)){
				positionToPieceMap.remove(piece.getCurrentPosition());
				placePiece(piece, position);
				flipCurrentPlayer();
				return "";
			}
		}

		return "Illegal Move";
	}

	private void flipCurrentPlayer(){
		if(currentPlayer == Player.White)
			setCurrentPlayer(Player.Black);
		else if(currentPlayer == Player.Black)
			setCurrentPlayer(Player.White);
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	private King opposingTeamKing;
	public void checkMateStatus() {
		//CheckMate
		//if(opposing teams king list of moves are all in the the current players list of moves) then check mate
		if(opposingTeamKing.getMoves().isEmpty())
			return;
		
		for(Position kingMoves: opposingTeamKing.getMoves()){
			if(legalMoves.keySet().isEmpty())
				return;
			for(Piece currentTeamPiece: legalMoves.keySet()){
				if(!legalMoves.get(currentTeamPiece).contains(kingMoves))
					return;
			}
		}
		
		checkMate = true;
	}
	
	//Player is not in check and the only legal moves available to them is from the king. Which puts him in Checkmate
	public void checkDrawStatus() {
	}
}
