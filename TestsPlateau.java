package plateau;

import jeu.Gomoku;
import mode.CommandeGTP;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestsPlateau {
    @Test
    public void testPlateauCreation (){
        IJeuDePlateau jeu = new Gomoku();
        Plateau plateau = new Plateau(jeu, 9);
        plateau.boardSize(9);
        assertEquals(plateau.getTaillePlateau(),9);
    }

    @Test
    public void TestPutEtGetPiece() {
        IJeuDePlateau jeu = new Gomoku();
        Plateau plateau = new Plateau(jeu, 9);
        plateau.boardSize(9);
        plateau.put("X", 4, 5);
        assertEquals("X", plateau.getPiece(4, 5));
    }

    @Test
    public void TestClearBoard() {
        IJeuDePlateau jeu = new Gomoku();
        Plateau plateau = new Plateau(jeu, 9);
        plateau.boardSize(9);
        plateau.put("O", 3, 4);
        plateau.clearBoard();
        assertNull(plateau.getPiece(3, 4));
    }
    @Test
    public void testBoardSizeCommand() {
        Plateau plateau = new Plateau(new Gomoku(), 9);

        CommandeGTP.boardsize(plateau, new String[]{"boardsize", "9"});
        assertEquals(9, plateau.getTaillePlateau());

        CommandeGTP.boardsize(plateau, new String[]{"boardsize", "27"});
        assertEquals(9, plateau.getTaillePlateau());

        CommandeGTP.boardsize(plateau, new String[]{"boardsize", "0"});
        assertEquals(9, plateau.getTaillePlateau());

        CommandeGTP.boardsize(plateau, new String[]{"boardsize"});

        CommandeGTP.boardsize(plateau, new String[]{"boardsize", "abc"});
    }


}




