package chess.pieces;

import java.util.HashSet;
import java.util.Set;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * A base class for chess pieces
 */
public abstract class Piece {
    private final Player owner;
    protected Set<Position> moves = new HashSet<Position>();
    public char c;
	public int r;
	public int quadrant;
	
    protected Piece(Player owner) {
        this.owner = owner;
    }

    public char getIdentifier() {
        char id = getIdentifyingCharacter();
        if (owner.equals(Player.White)) {
            return Character.toLowerCase(id);
        } else {
            return Character.toUpperCase(id);
        }
    }

    public Player getOwner() {
        return owner;
    }
    
    public abstract Set<Position> getMoves(Position origin, GameState gameState);

    protected abstract char getIdentifyingCharacter();
    
    public char incrementChar(char c, int i){
    	return (char)((char)c+i);
    }
    
    public char decrementChar(char c, int i){
    	return (char)((char)c-i);
    }
    
    public void incrementQuadrant(Position origin){
    	c = origin.getColumn();
		r = origin.getRow();
		quadrant++;
    }
}
