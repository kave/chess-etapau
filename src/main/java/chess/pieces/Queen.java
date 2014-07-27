package chess.pieces;

import java.util.List;

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
	public List<Position> getMoves(Position origin, GameState gameState) {
		setC(origin.getColumn());
		setR(origin.getRow());
		setQuadrant(0);
		
		Position position = null;
		while(getQuadrant() < 8){
			//Top Right
			if(getQuadrant() == 0){
				c++;r++;}
			//Top
			else if(getQuadrant() == 1){
				r++;}
			//Top Left
			else if(getQuadrant() == 2){
				c--;r++;}
			//Right
			else if(getQuadrant() == 3){
				c++;}
			//Left
			else if(getQuadrant() == 4){
				c--;}
			//Bottom left
			else if(getQuadrant() == 5){
				c--;r--;}
			//Bottom
			else if(getQuadrant() == 6){
				r--;}
			//Bottom right
			else if (getQuadrant() == 7){
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
