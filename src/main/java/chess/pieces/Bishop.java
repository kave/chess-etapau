package chess.pieces;

import java.util.Set;

import chess.GameState;
import chess.Player;
import chess.Position;
import chess.Validator;

/**
 * The 'Bishop' class
 */
public class Bishop extends Piece {
	public Bishop(Player owner) {
		super(owner);
	}

	@Override
	protected char getIdentifyingCharacter() {
		return 'b';
	}

	@Override
	public Set<Position> getMoves(Position origin, GameState gameState){
		c = origin.getColumn();
		r = origin.getRow();
		quadrant = 0;
		Position position = null;
		while(quadrant < 4){
			//Top Right
			if(quadrant == 0){
				c++;r++;}
			//Top Left
			else if(quadrant == 1){
				c--;r++;}
			//Bottom left
			else if(quadrant == 2){
				c--;r--;}
			//Bottom right
			else if (quadrant == 3){
				c++; r--;}

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
