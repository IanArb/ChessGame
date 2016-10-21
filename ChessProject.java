import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
    This class can be used as a starting point for creating your Chess game project. The only piece that
	has been coded is a white pawn...a lot done, more to do!
*/

public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
    private JLayeredPane layeredPane;
    private JPanel chessBoard;
    private JLabel chessPiece;
    private int xAdjustment;
    private int yAdjustment;
    private int startX;
    private int startY;
    private int initialX;
    private int initialY;
    private JPanel panels;
    private JLabel pieces;
    private int squareSize = 75;
    private int counter = 0;


    public ChessProject() {
        Dimension boardSize = new Dimension(600, 600);

        //  Use a Layered Pane for this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane 
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel(new BorderLayout());
            chessBoard.add(square);

            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground(i % 2 == 0 ? Color.white : Color.gray);
            else
                square.setBackground(i % 2 == 0 ? Color.gray : Color.white);
        }

        // Setting up the Initial Chess board.
        for (int i = 8; i < 16; i++) {
            pieces = new JLabel(new ImageIcon("WhitePawn.png"));
            panels = (JPanel) chessBoard.getComponent(i);
            panels.add(pieces);
        }
        pieces = new JLabel(new ImageIcon("WhiteRook.png"));
        panels = (JPanel) chessBoard.getComponent(0);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteKnight.png"));
        panels = (JPanel) chessBoard.getComponent(1);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteKnight.png"));
        panels = (JPanel) chessBoard.getComponent(6);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteBishop.png"));
        panels = (JPanel) chessBoard.getComponent(2);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteBishop.png"));
        panels = (JPanel) chessBoard.getComponent(5);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteKing.png"));
        panels = (JPanel) chessBoard.getComponent(3);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
        panels = (JPanel) chessBoard.getComponent(4);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteRook.png"));
        panels = (JPanel) chessBoard.getComponent(7);
        panels.add(pieces);
        for (int i = 48; i < 56; i++) {
            pieces = new JLabel(new ImageIcon("BlackPawn.png"));
            panels = (JPanel) chessBoard.getComponent(i);
            panels.add(pieces);
        }
        pieces = new JLabel(new ImageIcon("BlackRook.png"));
        panels = (JPanel) chessBoard.getComponent(56);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackKnight.png"));
        panels = (JPanel) chessBoard.getComponent(57);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackKnight.png"));
        panels = (JPanel) chessBoard.getComponent(62);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackBishop.png"));
        panels = (JPanel) chessBoard.getComponent(58);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackBishop.png"));
        panels = (JPanel) chessBoard.getComponent(61);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackKing.png"));
        panels = (JPanel) chessBoard.getComponent(59);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackQueen.png"));
        panels = (JPanel) chessBoard.getComponent(60);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackRook.png"));
        panels = (JPanel) chessBoard.getComponent(63);
        panels.add(pieces);
    }

    /*
        This method checks if there is a piece present on a particular square.
    */
    private Boolean piecePresent(int x, int y) {
        Component c = chessBoard.findComponentAt(x, y);
        if (c instanceof JPanel) {
            return false;
        } else {
            return true;
        }
    }

    /*
        This is a method to check if a piece is a Black piece.
    */
    private Boolean checkWhiteOponent(int newX, int newY) {
        Boolean opponent;
        Component c1 = chessBoard.findComponentAt(newX, newY);
        JLabel awaitingPiece = (JLabel) c1;
        String tmp1 = awaitingPiece.getIcon().toString();
        if (((tmp1.contains("Black")))) {
            opponent = true;
        } else {
            opponent = false;
        }
        return opponent;
    }

    /*
    This is a method to check if a piece is a White piece.
    */
    private Boolean checkBlackOpponent(int newX, int newY) {
        Boolean opponent;
        System.out.print(newX + " " + newY);
        Component component = chessBoard.findComponentAt(newX, newY);
        JLabel awaitingPiece = (JLabel) component;
        String tmp1 = awaitingPiece.getIcon().toString();
        if ((tmp1.contains("White"))) {
            opponent = true;
        } else {
            opponent = false;

        }
        return opponent;
    }

    private String playersTurn() {
        if((counter % 2) == 0) {
            return "White";
        } else {
            return "Black";
        }
    }

    /*
        This method is called when we press the Mouse. So we need to find out what piece we have
        selected. We may also not have selected a piece!
    */
    public void mousePressed(MouseEvent e) {
        chessPiece = null;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel)
            return;

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel) c;
        initialX = e.getX();
        initialY = e.getY();
        startX = (e.getX() / 75);
        startY = (e.getY() / 75);
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
        chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    }

    /*
       This method is used when the Mouse is released...we need to make sure the move was valid before
       putting the piece back on the board.
   */
    public void mouseReleased(MouseEvent e) {
        if (chessPiece == null) return;

        chessPiece.setVisible(false);

        Boolean success = false;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
        String tmp = chessPiece.getIcon().toString();
        String pieceName = tmp.substring(0, (tmp.length() - 4));
        Boolean validMove = false;
        Boolean progression = false;
        String myTurn = playersTurn();

        int landingX = (e.getX() / squareSize);
        int landingY = (e.getY() / squareSize);
        int xMovement = Math.abs((e.getX() / squareSize) - startX);
        int yMovement = Math.abs((e.getY() / squareSize) - startY);

        printChessMovements(pieceName, landingX, landingY, xMovement, yMovement, myTurn);

        Pawn pawn = new Pawn(e, success, pieceName, validMove, progression, landingX, landingY, myTurn, yMovement).invoke();
        validMove = pawn.getValidMove();
        progression = pawn.getProgression();
        success = pawn.getSuccess();
        myTurn = pawn.getMyTurn();

        validMove = isKnight(e, pieceName, validMove, landingX, landingY, myTurn);

        validMove = isBishop(e, pieceName, validMove, landingX, landingY, myTurn);

        validMove = isRook(e, pieceName, validMove, landingX, landingY, myTurn);

        validMove = isBishop(e, pieceName, validMove, landingX, landingY, myTurn);

        validMove = isQueen(e, pieceName, validMove, landingX, landingY, myTurn);

        validMove = isKing(e, pieceName, validMove, myTurn, xMovement, yMovement);

        takeQueen(e, success, c, pieceName, validMove, progression);
    }

    private Boolean isKing(MouseEvent e, String pieceName, Boolean validMove, String myTurn, int xMovement, int yMovement) {
        if (pieceName.contains(myTurn + "King")) {
            //Form the square movement for the King
            boolean isYMovement = xMovement == 0 && yMovement == 1;
            boolean isXMovement = xMovement == 1 && yMovement == 0;
            boolean isXYMovement = xMovement == 1 && yMovement == 1;
            if (isYMovement || isXMovement || isXYMovement) {

                // Check if there is no King present
                if(!isKingPresent(e.getX(), e.getY())) {
                    validMove = isLanding(e.getX(), e.getY(), pieceName);
                } else {
                    validMove = false;
                }

            } else {
                validMove = false;
            }

        }
        return validMove;
    }

    private Boolean isLanding(int newX, int newY, String pieceName) {
        Boolean validMove;
        if(pieceName.contains("King") && isKingPresent(newX, newY)) {
            validMove = false;
        } else {
            if(piecePresent(newX, newY)) {
                if (pieceName.contains("White")) {
                    if (checkWhiteOponent(newX, newY)) {
                        validMove = true;
                    } else {
                        validMove = false;
                    }
                } else {
                    if (checkBlackOpponent(newX, newY)) {
                        validMove = true;
                    } else {
                        validMove = false;
                    }
                }
            } else {
                validMove = true;
            }

        }

        return validMove;
    }

    private Boolean isKingPresent(int newX, int newY) {
        Boolean isKingPresent = false;
        int[][] location = new int[][]{
                {newX, newY+75},
                {newX, newY-75},
                {newX+75, newY},
                {newX-75, newY},
                {newX-75, newY-75},
                {newX-75, newY+75},
                {newX+75, newY+75},
                {newX+75, newY-75},

        };

        for(int[] loc : location) {
            if(!isEdgeOfBoard(loc[0], loc[1])) {
                if(piecePresent(loc[0], loc[1])) {
                    Component component = chessBoard.findComponentAt(loc[0], loc[1]);
                    JLabel awaitingPiece = (JLabel) component;
                    String tmp1 = awaitingPiece.getIcon().toString();
                    if ((tmp1.contains("King"))) {
                        isKingPresent = true;
                    }
                }
            }
        }

        return isKingPresent;
    }

    private Boolean isEdgeOfBoard(int newX, int newY) {
        return (newX <= 0 || newX >= 600 || newY <= 0 ||  newY >= 600);
    }

    private Boolean isKingCaptured(String winner, Component captured) {
        Boolean isGameOver = false;
        JLabel awaitingPiece = (JLabel) captured;
        String pieceName = awaitingPiece.getIcon().toString();
        if(pieceName.contains("King")) {
            winner = (winner.contains("Black") ? "Black" : "White");
            isGameOver = true;
            int input = JOptionPane.showOptionDialog(null, winner + " wins!", "Game Over", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            if(input == JOptionPane.OK_OPTION) {
                this.dispose();
                System.exit(0);
            }
        }
        return isGameOver;
    }


    private Boolean isQueen(MouseEvent e, String pieceName, Boolean validMove, int landingX, int landingY, String myTurn) {
        if (pieceName.contains(myTurn + "Queen")) {
            Boolean isInTheWay = false;
            int distance = Math.abs(startX - landingX);

            // Do bishop movement
            if (Math.abs(startX - landingX) == Math.abs(startY - landingY)) {
                //Determine if there is no piece blocking your way
                isInTheWay = isPieceDiagonal(landingX, landingY, isInTheWay, distance);
                //Determine if the bishop move is valid
                validMove = isValidBishopMove(e, pieceName, isInTheWay);
                // Do rook movement
            } else if ((isValidRookMove(landingX, landingY))) {
                validMove = false;
            } else {
                //Determine if the rook move is valid
                validMove = isVerticalHorizontal(e, pieceName, landingX, landingY, isInTheWay);
            }
        }
        return validMove;
    }

    private Boolean isValidBishopMove(MouseEvent e, String pieceName, Boolean isInTheWay) {
        Boolean validMove;
        //Check if there is a piece blocking your way
        if (isInTheWay) {
            validMove = false;
        } else {
            validMove = isOpponent(e, pieceName);
        }
        return validMove;
    }

    private void takeQueen(MouseEvent e, Boolean success, Component c, String pieceName, Boolean validMove, Boolean progression) {
        if (!validMove) {
            int location;
            if (startY == 0) {
                location = startX;
            } else {
                location = (startY * 8) + startX;
            }
            String pieceLocation = pieceName + ".png";
            pieces = new JLabel(new ImageIcon(pieceLocation));
            panels = (JPanel) chessBoard.getComponent(location);
            panels.add(pieces);
        } else {
            if (progression) {
                int location;
                location = (e.getX() / squareSize);
                if (c instanceof JLabel) {
                    Container parent = c.getParent();
                    parent.remove(0);
                    pieces = new JLabel(new ImageIcon("BlackQueen.png"));
                    parent = (JPanel) chessBoard.getComponent(location);
                    parent.add(pieces);
                    isKingCaptured(pieceName, c);
                }
            } else if (success) {
                counter++;
                int location = 56 + (e.getX() / squareSize);
                if (c instanceof JLabel) {
                    Container parent = c.getParent();
                    parent.remove(0);
                    pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
                    parent = (JPanel) chessBoard.getComponent(location);
                    parent.add(pieces);
                    isKingCaptured(pieceName, c);
                }
            } else {
                counter++;
                if (c instanceof JLabel) {
                    Container parent = c.getParent();
                    parent.remove(0);
                    parent.add(chessPiece);
                    isKingCaptured(pieceName, c);
                } else {
                    Container parent = (Container) c;
                    parent.add(chessPiece);
                }
                chessPiece.setVisible(true);
            }
        }
    }

    private Boolean isRook(MouseEvent e, String pieceName, Boolean validMove, int landingX, int landingY, String myTurn) {
        if (pieceName.contains(myTurn + "Rook")) {
            boolean isInTheWay = false;
            if ((isValidRookMove(landingX, landingY))) {
                validMove = false;
            } else {
                validMove = isVerticalHorizontal(e, pieceName, landingX, landingY, isInTheWay);
            }
        }
        return validMove;
    }

    private Boolean isVerticalHorizontal(MouseEvent e, String pieceName, int landingX, int landingY, boolean isInTheWay) {
        Boolean validMove;
        if (((Math.abs(startX - landingX) != 0)
                && (Math.abs(startY - landingY) == 0))
                || ((Math.abs(startX - landingX) == 0)
                && (Math.abs(landingY - startY) != 0))) {
            validMove = isVerticalHorizontalMoves(e, pieceName, landingX, landingY, isInTheWay);
        } else {
            validMove = false;
        }
        return validMove;
    }

    private Boolean isVerticalHorizontalMoves(MouseEvent e, String pieceName, int landingX, int landingY, boolean isInTheWay) {
        Boolean validMove;
        if (Math.abs(startX - landingX) != 0) {
            int xMove = Math.abs(startX - landingX);
            if (startX - landingX > 0) {
                for (int i = 0; i < xMove; i++) {
                    if (piecePresent(initialX - (i * squareSize), e.getY())) {
                        isInTheWay = true;
                        break;
                    } else {
                        isInTheWay = false;
                    }
                }
            } else {
                for (int i = 0; i < xMove; i++) {
                    if (piecePresent(initialX + (i * squareSize), e.getY())) {
                        isInTheWay = true;
                        break;
                    } else {
                        isInTheWay = false;
                    }
                }
            }
        } else {
            int yMove = Math.abs(startY - landingY);
            if (startY - landingY > 0) {
                for (int i = 0; i < yMove; i++) {
                    if (piecePresent(e.getX(), initialY - (i * squareSize))) {
                        isInTheWay = true;
                        break;
                    } else {
                        isInTheWay = false;
                    }
                }
            } else {
                for (int i = 0; i < yMove; i++) {
                    if (piecePresent(e.getX(), initialY + (i * squareSize))) {
                        isInTheWay = true;
                        break;
                    } else {
                        isInTheWay = false;
                    }
                }
            }
        }

        if (isInTheWay) {
            validMove = false;
        } else {
            if (piecePresent(e.getX(), e.getY())) {
                if (pieceName.contains("White")) {
                    validMove = isWhiteOpponent(e);
                } else {
                    validMove = isBlackOpponent(e);
                }
            } else {
                validMove = true;
            }
        }
        return validMove;
    }

    private Boolean isBishop(MouseEvent e, String pieceName, Boolean validMove, int landingX, int landingY, String myTurn) {
        if (pieceName.contains(myTurn + "Bishop")) {
            // Check if piece is in the way
            boolean isInTheWay = false;
            // Calculate the distance between the starting X position and possible landing X position
            int distance = Math.abs(startX - landingX);

            validMove = isDiagonal(e, pieceName, landingX, landingY, isInTheWay, distance);
        }
        return validMove;
    }

    private Boolean isDiagonal(MouseEvent e, String pieceName, int landingX, int landingY, boolean isInTheWay, int distance) {
        Boolean validMove;
        if (isValidRookMove(landingX, landingY)) {
            validMove = false;
        } else {
            // Check if starting position on the X axis is equal to starting position on the Y axis
            if (Math.abs(startX - landingX) == Math.abs(startY - landingY)) {
                isInTheWay = isPieceDiagonal(landingX, landingY, isInTheWay, distance);
                //Check if there is a piece blocking your way
                validMove = isValidBishopMove(e, pieceName, isInTheWay);
            } else {
                validMove = false;
            }
        }
        return validMove;
    }

    private Boolean isPieceDiagonal(int landingX, int landingY, boolean isInTheWay, int distance) {
        //Conditions to check if piece is moving along a diagonal
        if ((startX - landingX < 0) && (startY - landingY < 0)) {
            int i;
            for (i = 0; i < distance; i++) {
                if (piecePresent((initialX + (i * squareSize)), (initialY + (i * squareSize)))) {
                    isInTheWay = true;
                }
            }
        } else if ((startX - landingX < 0) && (startY - landingY > 0)) {
            int i;
            for (i = 0; i < distance; i++) {
                if (piecePresent((initialX + (i * squareSize)), (initialY - (i * squareSize)))) {
                    isInTheWay = true;
                }
            }
        } else if ((startX - landingX > 0) && (startY - landingY > 0)) {
            int i;
            for (i = 0; i < distance; i++) {
                if (piecePresent((initialX - (i * squareSize)), (initialY - (i * squareSize)))) {
                    isInTheWay = true;
                }
            }
        } else if ((startX - landingX > 0) && (startY - landingY < 0)) {
            int i;
            for (i = 0; i < distance; i++) {
                if (piecePresent((initialX - (i * squareSize)), (initialY + (i * squareSize)))) {
                    isInTheWay = true;
                }
            }
        }
        return isInTheWay;
    }

    private boolean isValidRookMove(int landingX, int landingY) {
        return ((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7));
    }

    private Boolean isKnight(MouseEvent e, String pieceName, Boolean validMove, int landingX, int landingY, String myTurn) {
        if (pieceName.contains(myTurn + "Knight")) {
            if (((landingX < 0) || (landingY > 7)) || ((landingY < 0) || (landingX > 7))) {
                validMove = false;
            } else {
                // Check all possible valid Knight moves that follows the L shape
                if (isValidKnightMove(landingX, landingY)) {
                    // Check if you can take opponent's piece
                    validMove = isOpponent(e, pieceName);
                } else {
                    validMove = false;
                }
            }
        }
        return validMove;
    }

    private Boolean isValidKnightMove(int landingX, int landingY) {
        boolean isOneSquareX = landingX == startX + 1;
        boolean isTwoSquareY = landingY == startY + 2;
        boolean isOneSquareBackX = landingX == startX - 1;
        boolean isTwoSquareX = landingX == startX + 2;
        boolean isOneSquareY = landingY == startY + 1;
        boolean isTwoSquareBackY = landingY == startY - 2;
        boolean isOneSquareBackY = landingY == startY - 1;
        boolean isTwoSquareBackX = landingX == startX - 2;
        return (isOneSquareX && isTwoSquareY)
                || (isOneSquareBackX && isTwoSquareY)
                || (isTwoSquareX && isOneSquareY)
                || (isTwoSquareBackX && isOneSquareY)
                || (isOneSquareX && isTwoSquareBackY)
                || (isOneSquareBackX && isTwoSquareBackY)
                || (isTwoSquareX && isOneSquareBackY)
                || (isTwoSquareBackX && isOneSquareBackY);
    }

    private Boolean isBlackOpponent(MouseEvent e) {
        Boolean validMove;
        if (checkBlackOpponent(e.getX(), e.getY())) {
            validMove = true;
        } else {
            validMove = false;
        }
        return validMove;
    }

    private Boolean isWhiteOpponent(MouseEvent e) {
        Boolean validMove;
        if (checkWhiteOponent(e.getX(), e.getY())) {
            validMove = true;
        } else {
            validMove = false;
        }
        return validMove;
    }

    private Boolean isOpponent(MouseEvent e, String pieceName) {
        Boolean validMove;
        if (piecePresent(e.getX(), e.getY())) {
            if (pieceName.contains("White")) {
                validMove = isWhiteOpponent(e);
            } else {
                validMove = isBlackOpponent(e);
            }
        } else validMove = true;
        return validMove;
    }

    private boolean isMovedTwoSquares(MouseEvent e) {
        // 75 is the width and height of the square
        return ((e.getY() / squareSize) - startY) == 2;
    }

    private boolean isMovedOneSquare(MouseEvent e) {
        return ((e.getY() / squareSize) - startY) == 1;
    }

    private void printChessMovements(String pieceName, int landingX, int landingY, int xMovement, int yMovement, String myTurn) {
        System.out.println("----------------------------------------------");
        System.out.println("The piece that is being moved is : " + pieceName);
        System.out.println("The starting coordinates are : " + "( " + startX + "," + startY + ")");
        System.out.println("The xMovement is : " + xMovement);
        System.out.println("The yMovement is : " + yMovement);
        System.out.println("The landing coordinates are : " + "( " + landingX + "," + landingY + ")");
        System.out.println("Players turn is : " + myTurn);
        System.out.println("----------------------------------------------");
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    /*
        Main method that gets the ball moving.
    */
    public static void main(String[] args) {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Pawn Object
    private class Pawn {
        private MouseEvent e;
        private Boolean success;
        private String pieceName;
        private Boolean validMove;
        private Boolean progression;
        private int landingX;
        private int landingY;
        private int yMovement;
        private String myTurn;

        public Pawn(MouseEvent e, Boolean success, String pieceName, Boolean validMove, Boolean progression, int landingX, int landingY, String myTurn, int yMovement) {
            this.e = e;
            this.success = success;
            this.pieceName = pieceName;
            this.validMove = validMove;
            this.progression = progression;
            this.landingX = landingX;
            this.landingY = landingY;
            this.myTurn = myTurn;
            this.yMovement = yMovement;
        }

        public Boolean getSuccess() {
            return success;
        }

        public Boolean getValidMove() {
            return validMove;
        }

        public Boolean getProgression() {
            return progression;
        }

        public String getMyTurn() {
            return myTurn;
        }

        public Pawn invoke() {
            whitePawn();

            blackPawn();

            return this;
        }

        private void blackPawn() {
            if (pieceName.equals("BlackPawn") && myTurn.equals("Black")) {
                // The pawn can either move two or one squares
                if (isTwoOrOneSquare(landingX, landingY)) {
                    // if there is a piece in the way, invalid move
                    if (isNoPiecePresent(e)) {
                        validMove = true;
                    } else {
                        validMove = false;
                    }
                } else if ((Math.abs(startX - landingX) == 1) && ((startY - landingY) == 1)) {
                    if (piecePresent(e.getX(), e.getY())) {
                        // Checks the black opponent's white queen and turn pawn into black queen if landingY == 0
                        if (checkBlackOpponent(e.getX(), e.getY())) {
                            validMove = true;
                            if (landingY == 0) {
                                progression = true;
                            }
                        } else {
                            validMove = false;
                        }
                    } else {
                        validMove = false;
                    }
                } else if ((startY != 6) && ((startX == landingX) && ((startY - landingY) == 1))) {
                    // isNoPiecePresent(e) method - Checks if there is a piece in the way, if there is, invalid move
                    if (isNoPiecePresent(e)) {
                        validMove = true;
                        if (landingY == 0) {
                            progression = true;
                        }
                    } else {
                        validMove = false;
                    }
                } else {
                    validMove = false;
                }
            }
        }

        private void whitePawn() {
            if (pieceName.equals("WhitePawn") && myTurn.equals("White")) {
                // Starting position of the board
                if (startY == 1) {
                    if (isValidMove(e)) {
                        if (isMovedTwoSquares(e)) {
                            if ((!piecePresent(e.getX(), (e.getY())))) {
                                validMove = true;
                            } else {
                                validMove = false;
                            }
                        } else {
                            if ((!piecePresent(e.getX(), (e.getY())))) {
                                validMove = true;
                            } else {
                                validMove = false;
                            }
                        }
                    } else if (piecePresent(e.getX(), e.getY())) {
                        checkWhiteOpponent();
                    }
                    // Anywhere else on the board
                } else {
                    if (yMovement == 1) {
                        if ((piecePresent(e.getX(), (e.getY()))) && ((((landingX == (startX + 1) && (startX + 1 <= 7))) || ((landingX == (startX - 1)) && (startX - 1 >= 0)))) && landingY == (startY + 1)) {
                            checkWhiteOpponent();
                        } else {
                            // Check if the piece is not present and if not.. invalid move
                            // 75 is the width of the square
                            if (isNoPiecePresent(e)) {
                                if ((startX == (e.getX() / squareSize)) && ((e.getY() / squareSize) - startY) == 1) {
                                    if (startY == 6) {
                                        success = true;
                                    }
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            } else {
                                validMove = false;
                            }
                        }
                    } else {
                        validMove = false;
                    }
                }
            }
        }

        private void checkWhiteOpponent() {
            if (checkWhiteOponent(e.getX(), e.getY())) {
                validMove = true;
                if (startY == 6) {
                    success = true;
                }
            } else {
                validMove = false;
            }
        }

        private boolean isValidMove(MouseEvent e) {
            return (startX == (e.getX() / squareSize)) && (isMovedOneSquare(e) || isMovedTwoSquares(e));
        }

        private boolean isNoPiecePresent(MouseEvent e) {
            // returns the x and y coordinates of mouse event
            return !piecePresent(e.getX(), e.getY());
        }

        private boolean isTwoOrOneSquare(int landingX, int landingY) {
            // start Y == 6 - This determines the position for the black pawns in the 2nd row on the y coordinates
            // startX == landing X - Checks if x direction and landing x are equal
            // startY - landingY == 1 - Checks if pawn is being moved once in the Y direction
            // startY - landingY == 2 - Checks if the pawn is being moved twice in the Y direction
            return (startY == 6) && (startX == landingX) && (((startY - landingY) == 1) || (startY - landingY) == 2);
        }
    }
}


