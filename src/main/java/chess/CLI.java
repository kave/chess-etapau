package chess;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Random;

import chess.pieces.Piece;

/**
 * This class provides the basic CLI interface to the Chess game.
 */
public class CLI {
	private static final String NEWLINE = System.getProperty("line.separator");

	private BufferedReader inReader;
	private final PrintStream outStream;

	private GameState gameState = null;

	public CLI(InputStream inputStream, PrintStream outStream) {
		this.inReader = new BufferedReader(new InputStreamReader(inputStream));
		this.outStream = outStream;
		writeOutput("Welcome to Chess!");
	}

	public void newCommand(InputStream in){
		this.inReader = new BufferedReader(new InputStreamReader(in));
	}
	/**
	 * Write the string to the output
	 * @param str The string to write
	 */
	private void writeOutput(String str) {
		this.outStream.println(str);
	}

	/**
	 * Retrieve a string from the console, returning after the user hits the 'Return' key.
	 * @return The input from the user, or an empty-length string if they did not type anything.
	 */
	private String getInput() {
		try {
			this.outStream.print("> ");
			return inReader.readLine();
		} catch (IOException e) {
			throw new RuntimeException("Failed to read from input: ", e);
		}
	}

	private List<String> moves;
	public boolean automated;

	public String startEventLoop() {
		writeOutput("Type 'help' for a list of commands.");
		doNewGame();

		while (true) {
			showBoard();

			moves = gameState.listAllPossibleMoves();

			gameState.checkDrawStatus();
			if(gameState.draw){
				writeOutput("You Won! Draw");
				return "You Won! Draw";
			}

			writeOutput(gameState.getCurrentPlayer() + "'s Move");
			String input = null;

			if(automated){
				Map<Piece, List<Position>> legalMoves = gameState.getCurrentPlayerlegalMoves();

				Random rand = new Random();

				int randomPiece = rand.nextInt((legalMoves.keySet().size() - 0)) + 0;

				int x = 0;
				for(Piece piece:legalMoves.keySet()){
					if(x==randomPiece){
						List<Position> pos = legalMoves.get(piece);
						int randomPos = rand.nextInt((pos.size() - 0)) + 0;
						input = "move "+piece.getCurrentPosition().toString()+ " "+ pos.get(randomPos).toString();
						break;
					}
					x++;
				}
			}
			else{
				input = getInput();
			}

			if (input == null) {
				break; // No more input possible; this is the only way to exit the event loop
			} else if (input.length() > 0) {
				if (input.equals("help")) {
					showCommands();
				} else if (input.equals("new")) {
					doNewGame();
				} else if (input.equals("quit")) {
					writeOutput("Goodbye!");
					System.exit(0);
				} else if (input.equals("board")) {
					writeOutput("Current Game:");
				} else if (input.equals("list")) {
					list();
				} else if (input.startsWith("move")) {
					String[] args = input.split(" ");
					if(!move(args[1], args[2]).isEmpty()){
						return "Check Mate";
					}
				} else {
					writeOutput("I didn't understand that.  Type 'help' for a list of commands.");
				}
			}
		}
		
		return "";
	}

	/**
	 * List command
	 */
	public void list(){
		writeOutput("White's Possible Moves: ");
		for(String move: moves){
			writeOutput(move);
		}
	}

	/**
	 * Move command
	 */
	public String move(String piece, String newPosition){
		String errorMsg = gameState.validateMoveAndPlacePiece(gameState.getPieceAt(piece), Validator.createPosition(newPosition));

		if(errorMsg.equals("checkMate")){
			writeOutput("You Won! CheckMate");
			return ("You Won! CheckMate");
		}

		if (!errorMsg.isEmpty()){
			writeOutput("==x== " + errorMsg + "==x==");
			writeOutput("==x== Please try again ==x==");
		}
		
		return "";
	}

	private void doNewGame() {
		gameState = new GameState();
		gameState.reset();
	}

	public String showBoard() {
		writeOutput(getBoardAsString());
		
		return getBoardAsString();
	}

	private void showCommands() {
		writeOutput("Possible commands: ");
		writeOutput("    'help'                       Show this menu");
		writeOutput("    'quit'                       Quit Chess");
		writeOutput("    'new'                        Create a new game");
		writeOutput("    'board'                      Show the chess board");
		writeOutput("    'list'                       List all possible moves");
		writeOutput("    'move <colrow> <colrow>'     Make a move");
	}

	/**
	 * Display the board for the user(s)
	 */
	String getBoardAsString() {
		StringBuilder builder = new StringBuilder();
		builder.append(NEWLINE);

		printColumnLabels(builder);
		for (int i = Position.MAX_ROW; i >= Position.MIN_ROW; i--) {
			printSeparator(builder);
			printSquares(i, builder);
		}

		printSeparator(builder);
		printColumnLabels(builder);

		return builder.toString();
	}


	private void printSquares(int rowLabel, StringBuilder builder) {
		builder.append(rowLabel);

		for (char c = Position.MIN_COLUMN; c <= Position.MAX_COLUMN; c++) {
			Piece piece = gameState.getPieceAt(String.valueOf(c) + rowLabel);
			char pieceChar = piece == null ? ' ' : piece.getIdentifier();
			builder.append(" | ").append(pieceChar);
		}
		builder.append(" | ").append(rowLabel).append(NEWLINE);
	}

	private void printSeparator(StringBuilder builder) {
		builder.append("  +---+---+---+---+---+---+---+---+").append(NEWLINE);
	}

	private void printColumnLabels(StringBuilder builder) {
		builder.append("   ");
		for (char c = Position.MIN_COLUMN; c <= Position.MAX_COLUMN; c++) {
			builder.append(" ").append(c).append("  ");
		}

		builder.append(NEWLINE);
	}

	public static void main(String[] args) {
		CLI cli = new CLI(System.in, System.out);
		cli.startEventLoop();
	}

	public GameState getGameState() {
		return gameState;
	}
}
