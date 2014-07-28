package chess.pieces;

import java.util.ArrayList;
import java.util.List;

import chess.GameState;
import chess.Player;
import chess.Position;

/**
 * A base class for chess pieces
 */
public abstract class Piece {
    private final Player owner;
    protected List<Position> moves = new ArrayList<Position>();
    protected Position currentPosition;
	protected char c;
	protected int r;
	private int quadrant;
	
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
    
    public abstract List<Position> getMoves(Position origin, GameState gameState);

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
    
    public List<Position> getMoves() {
		return moves;
	}

	public char getC() {
		return c;
	}

	public int getR() {
		return r;
	}

	public int getQuadrant() {
		return quadrant;
	}

	public void setC(char c) {
		this.c = c;
	}

	public void setR(int r) {
		this.r = r;
	}

	public void setQuadrant(int quadrant) {
		this.quadrant = quadrant;
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
	}
}
