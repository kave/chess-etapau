package chess.pieces;

import java.util.Set;

import chess.GameState;
import chess.Player;
import chess.Position;
import chess.Validator;

/**
 * The Queen
 */
public class Queen extends Piece{
	public Queen(Player owner) {
		super(owner);
	}

	@Override
	protected char getIdentifyingCharacter() {
		return 'q';
	}

	@Override
	public Set<Position> getMoves(Position origin, GameState gameState) {
		c = origin.getColumn();
		r = origin.getRow();
		Position position = null;
		while(quadrant < 8){
			//Top Right
			if(quadrant == 0){
				c++;r++;}
			//Top
			else if(quadrant == 1){
				r++;}
			//Top Left
			else if(quadrant == 2){
				c--;r++;}
			//Right
			else if(quadrant == 3){
				c++;}
			//Left
			else if(quadrant == 4){
				c--;}
			//Bottom left
			else if(quadrant == 5){
				c--;r--;}
			//Bottom
			else if(quadrant == 6){
				r--;}
			//Bottom right
			else if (quadrant == 7){
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
