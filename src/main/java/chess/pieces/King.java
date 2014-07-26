package chess.pieces;

import java.util.Set;

import chess.GameState;
import chess.Player;
import chess.Position;
import chess.Validator;

/**
 * The King class
 */
public class King extends Piece {
	public King(Player owner) {
		super(owner);
	}

	@Override
	protected char getIdentifyingCharacter() {
		return 'k';
	}

	@Override
	public Set<Position> getMoves(Position origin, GameState gameState) {
		c = origin.getColumn();
		r = origin.getRow();

		//Top Right
		moveKing(incrementChar(c,1),r+1, gameState);
		//Top Left
		moveKing(decrementChar(c,1),r+1, gameState);
		//Bottom left
		moveKing(decrementChar(c,1),r-1, gameState);
		//Bottom right
		moveKing(incrementChar(c,1),r-1, gameState);
		//Bottom
		moveKing(c,r-1, gameState);
		//Right
		moveKing(incrementChar(c,1),r, gameState);
		//Left
		moveKing(decrementChar(c,1),r, gameState);
		//Top
		moveKing(c,r+1, gameState);

		return moves;
	}

	private void moveKing(char c, int r,  GameState gameState){
		Position position = Validator.createPosition(String.valueOf(c) + String.valueOf(r));

		if(position != null){
			if(gameState.getPieceAt(position) == null){
				moves.add(position);
			}
			else if(Validator.canEat(position, gameState)){
				moves.add(position);
			}
		}
	}
}
