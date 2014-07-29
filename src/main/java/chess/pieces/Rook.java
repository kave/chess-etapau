package chess.pieces;

import java.util.List;

import chess.GameState;
import chess.Player;
import chess.Position;
import chess.Validator;

/**
 * The 'Rook' class
 */
public class Rook extends Piece {

	public Rook(Player owner) {
		super(owner);
	}

	@Override
	protected char getIdentifyingCharacter() {
		return 'r';
	}

	@Override
	public List<Position> getMoves(Position origin, GameState gameState) {
		setC(origin.getColumn());
		setR(origin.getRow());
		setQuadrant(0);
		
		Position position = null;
		while(getQuadrant() < 4){
			//Top
			if(getQuadrant() == 0){
				r++;}
			//Left
			else if(getQuadrant() == 1){
				c--;}
			//right
			else if(getQuadrant() == 2){
				c++;}
			//Bottom
			else if (getQuadrant() == 3){
				r--;}

			position = Validator.createPosition(String.valueOf(c) + String.valueOf(r));
			Piece piece = gameState.getPieceAt(position);

			if(piece == null){
				if(position == null){
					incrementQuadrant(origin);
				}
				else
					moves.add(position);
			}
			else{
				if(Validator.canEat(position, gameState)){
					moves.add(position);
				}
				incrementQuadrant(origin);
			}
		}

		return moves;
	}
}
