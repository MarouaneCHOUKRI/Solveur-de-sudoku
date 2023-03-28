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
    private Grille grille;

    @BeforeEach
    public void setUp() throws IOException, ElementInterditException, ValeurInitialeModifExcept, HorsBornesException,
            ValeurImpossibleException {

        InputStream in = new FileInputStream("C:\\Users\\chouk\\OneDrive\\Bureau\\Solveur-de-sudoku\\"
                + "src\\main\\java\\c306\\soduku\\grilles\\sudoku16-moyen-complete.txt");

        grille = GrilleParser.parse(in);
    }

    @Test
    public void testGetElements() {
        Set<ElementDeGrille> elements = grille.getElements();
        assertFalse(elements.isEmpty());
    }

    @Test
    public void testGetDimension() {
        int dimension = grille.getDimension();
        assertEquals(16, dimension);
    }

    @Test
    public void testSetValue() {
        Set<ElementDeGrille> elements = grille.getElements();
        ElementDeGrille value = new ElementDeGrilleImplAsChar('0');

        // // HorsBornesException
        // assertThrows(HorsBornesException.class, () -> grille.setValue(-1, 0, value));

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