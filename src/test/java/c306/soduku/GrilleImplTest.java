package c306.soduku;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GrilleImplTest {
    private final int dimension = 16;
    private Grille grille;
    private Set<ElementDeGrille> elements;

    @BeforeEach
    public void setUp() throws IOException, ElementInterditException, ValeurInitialeModifExcept, HorsBornesException,
            ValeurImpossibleException {

        InputStream in = new FileInputStream("C:\\Users\\utilisateur\\Desktop\\Solveur-de-sudoku\\src\\main\\java\\c306\\soduku\\grilles\\sudoku16-moyen-complete.txt");

        grille = GrilleParser.parse(in);
        elements = grille.getElements();
    }

    @Test
    public void testGetElements() {
        assertFalse(elements.isEmpty());
    }

    @Test
    public void testGetDimension() {
        assertEquals(dimension, grille.getDimension());
    }

    @Test
    public void testSetValue() {
        ElementDeGrille value = new ElementDeGrilleImplAsChar('f');

        // ValeurImpossibleException

        // ElementInterditException
        assertThrows(ElementInterditException.class, () -> grille.setValue(1, 0, value));

        // HorsBornesException
        assertThrows(HorsBornesException.class, () -> grille.setValue(-1, 0, value));

        // ValeurInitialeModifExcept
    }

    @Test
    public void testGetValue() throws HorsBornesException {
        ElementDeGrille value = grille.getValue(1, 1);
        assertNotNull(value);
    }

    @Test
    public void testIsComplete() {
        boolean complete = grille.isComplete();
        assertTrue(complete);
    }

    @Test
    public void testIsValeurInitiale() {
        boolean valeurInitiale = grille.isValeurInitiale(14, 0);
        assertTrue(valeurInitiale);
    }

}