package chess.pieces;

import java.util.List;

import chess.GameState;
import chess.Player;
import chess.Position;
import chess.Validator;

/**
 * The Pawn
 */
public class Pawn extends Piece {
	public Pawn(Player owner) {
		super(owner);
	}

	@Override
	protected char getIdentifyingCharacter() {
		return 'p';
	}

	@Override
	public List<Position> getMoves(Position origin, GameState gameState) {
		c = origin.getColumn();
		r = origin.getRow();

		if(getOwner() == Player.White){
			//Top Right
			movePawn(incrementChar(c,1), r+1, gameState, "eat");
			//Top Left
			movePawn(decrementChar(c,1), r+1, gameState, "eat");
			//Top	
			movePawn(c, r+1, gameState, "move");
			//Top Twice	
			if(r == 2)
				movePawn(c, r+2, gameState, "move");
		}
		else if(getOwner() == Player.Black){
			//Bottom Right
			movePawn(incrementChar(c,1),r-1, gameState, "eat");
			//Bottom Left
			movePawn(decrementChar(c,1),r-1, gameState, "eat");
			//Bottom	
			movePawn(c, r-1, gameState, "move");
			//Bottom Twice	
			if(r == 7)
				movePawn(c, r-2, gameState, "move");
		}

		return moves;
	}

	public void movePawn(char c, int r, GameState gameState, String moveType){
		String colRow = String.valueOf(c)+ String.valueOf(r);

		Position position = Validator.createPosition(colRow);
		Piece piece = gameState.getPieceAt(position);

		if(position != null){
			if(moveType.equals("move")){
				if(piece == null){
					moves.add(position);
				}
			}
			else if(moveType.equals("eat")){
				if( piece != null){
					if(Validator.canEat(position, gameState) ){
						moves.add(position);
					}
				}
			}
		}
	}
}

