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

    // Test GetElements
    @Test
    public void testGetElements() {
        Set<ElementDeGrille> elements = grille.getElements();
        assertNotNull(elements);
        assertFalse(elements.isEmpty());
    }

    // Test GetDimension
    @Test
    public void testGetDimension() {
        int dimension = grille.getDimension();
        assertEquals(16, dimension);
    }

    // Test SetValue
    @Test
    public void testSetValue() {

        // Test HorsBornesException
        ElementDeGrille value = new ElementDeGrilleImplAsChar('f');
        assertThrows(HorsBornesException.class, () -> grille.setValue(-1, 0, value));

        // Test ValeurImpossibleException
        ElementDeGrille impossibleValue = new ElementDeGrilleImplAsChar('a');
        assertThrows(ValeurImpossibleException.class, () -> grille.setValue(0, 0, impossibleValue));

        // Test ElementInterditException
        ElementDeGrille interditValue = new ElementDeGrilleImplAsChar('*');
        assertThrows(ElementInterditException.class, () -> grille.setValue(0, 0, interditValue));

        // Test ValeurInitialeModifExcept
        assertThrows(ValeurInitialeModifExcept.class, () -> grille.setValue(0, 1, value));
    }

    // Test GetValue
    @Test
    public void testGetValue() throws HorsBornesException {
        ElementDeGrille value = grille.getValue(0, 0);
        assertNotNull(value);
    }

    // Test IsComplete
    @Test
    public void testIsComplete() {
        boolean complete = grille.isComplete();
        assertTrue(complete);
    }

    // Test IsPossible
    @Test
    public void testIsPossible() throws HorsBornesException, ElementInterditException {

        // Test HorsBornesException
        ElementDeGrille value = new ElementDeGrilleImplAsChar('f');
        assertThrows(HorsBornesException.class, () -> grille.isPossible(-1, 0, value));

        // Test ElementInterditException
        ElementDeGrille interditValue = new ElementDeGrilleImplAsChar('*');
        assertThrows(ElementInterditException.class, () -> grille.isPossible(0, 0, interditValue));

        // Test non possible
        ElementDeGrille nonPossibleValue = new ElementDeGrilleImplAsChar('2');
        assertFalse(grille.isPossible(0, 0, nonPossibleValue));
    }

    // Test IsValeurInitiale
    @Test
    public void testIsValeurInitiale() {
        boolean valeurInitiale = grille.isValeurInitiale(0, 0);
        assertTrue(valeurInitiale);
    }
}