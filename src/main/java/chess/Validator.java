package chess;

import chess.pieces.Piece;

public class Validator {
    public static final int MIN_ROW = 1;
    public static final int MAX_ROW = 8;
    public static final char MIN_COLUMN = 'a';
    public static final char MAX_COLUMN = 'h';

    public static Position createPosition(String colrow){
    	//Possibly use StringUtils from apache commons to check for null
    	if(colrow == null || colrow.isEmpty())
    		return null;
    	
    	char column = colrow.toCharArray()[0];
		int row = Character.digit(colrow.toCharArray()[1], 10);
		
    	if (column < MIN_COLUMN || column > MAX_COLUMN) {
    		 return null;
        }

        if (row < MIN_ROW || row > MAX_ROW) {
        	 return null;
        }
        
        return new Position(colrow);
    }
    
    public static boolean canEat(Position position, GameState gameState){
    	Piece piece = gameState.getPieceAt(position);
    	
    	if(piece.getOwner() != gameState.getCurrentPlayer()){
			return true;
		}
    	
    	return false;
    }
}
