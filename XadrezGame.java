import java.util.Scanner;

public class ChessGame {

    public static void main(String[] args) {
        Board board = new Board();
        board.initBoard();
        board.printBoard();
        
        Scanner scanner = new Scanner(System.in);
        boolean gameEnd = false;
        
        while(!gameEnd) {
            System.out.print("Enter move: ");
            String move = scanner.nextLine();
            if(move.equals("quit")) {
                gameEnd = true;
                System.out.println("Game ended.");
            } else {
                boolean success = board.move(move);
                if(!success) {
                    System.out.println("Invalid move. Try again.");
                } else {
                    board.printBoard();
                }
            }
        }
        
        scanner.close();
    }

}

class Board {
    private Piece[][] board = new Piece[8][8];
    
    public void initBoard() {
        // Initialize pawns
        for(int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(PieceColor.BLACK);
            board[6][i] = new Pawn(PieceColor.WHITE);
        }
        
        // Initialize other pieces
        board[0][0] = new Rook(PieceColor.BLACK);
        board[0][7] = new Rook(PieceColor.BLACK);
        board[7][0] = new Rook(PieceColor.WHITE);
        board[7][7] = new Rook(PieceColor.WHITE);
        
        board[0][1] = new Knight(PieceColor.BLACK);
        board[0][6] = new Knight(PieceColor.BLACK);
        board[7][1] = new Knight(PieceColor.WHITE);
        board[7][6] = new Knight(PieceColor.WHITE);
        
        board[0][2] = new Bishop(PieceColor.BLACK);
        board[0][5] = new Bishop(PieceColor.BLACK);
        board[7][2] = new Bishop(PieceColor.WHITE);
        board[7][5] = new Bishop(PieceColor.WHITE);
        
        board[0][3] = new Queen(PieceColor.BLACK);
        board[7][3] = new Queen(PieceColor.WHITE);
        
        board[0][4] = new King(PieceColor.BLACK);
        board[7][4] = new King(PieceColor.WHITE);
    }
    
    public boolean move(String move) {
        // Implement move logic here
        return true;
    }
    
    public void printBoard() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if(piece == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(piece.toString() + " ");
                }
            }
            System.out.println(8 - i);
        }
        System.out.println("a b c d e f g h");
    }
}

enum PieceColor {
    WHITE, BLACK;
}

abstract class Piece {
    protected PieceColor color;
    
    public Piece(PieceColor color) {
        this.color = color;
    }
    
    public abstract boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board);
    
    public String toString() {
        String colorString = this.color == PieceColor.WHITE
