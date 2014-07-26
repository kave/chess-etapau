package chess.pieces;

import java.util.Set;

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
	public Set<Position> getMoves(Position origin, GameState gameState) {
		c = origin.getColumn();
		r = origin.getRow();
		Position position = null;
		while(quadrant < 4){
			//Top
			if(quadrant == 0){
				r++;}
			//Left
			else if(quadrant == 1){
				c--;}
			//right
			else if(quadrant == 2){
				c++;}
			//Bottom
			else if (quadrant == 3){
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
