package chess.pieces;

import java.util.Set;

import chess.GameState;
import chess.Player;
import chess.Position;
import chess.Validator;

/**
 * The Knight class
 */
public class Knight extends Piece {
    public Knight(Player owner) {
        super(owner);
    }

    @Override
    protected char getIdentifyingCharacter() {
        return 'n';
    }

	@Override
	public Set<Position> getMoves(Position origin, GameState gameState) {
		c = origin.getColumn();
		r = origin.getRow();
		
		moveKnight(incrementChar(c,1),r+2, gameState);
		moveKnight(decrementChar(c,1),r+2, gameState);
		
		moveKnight(incrementChar(c,1),r-2, gameState);
		moveKnight(decrementChar(c,1),r-2, gameState);
		
		moveKnight(incrementChar(c,2),r+1, gameState);
		moveKnight(decrementChar(c,2),r+1, gameState);
		
		moveKnight(incrementChar(c,2),r-1, gameState);
		moveKnight(decrementChar(c,2),r-1, gameState);
		
		return moves;
	}
	
	private void moveKnight(char c, int r,  GameState gameState){
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
