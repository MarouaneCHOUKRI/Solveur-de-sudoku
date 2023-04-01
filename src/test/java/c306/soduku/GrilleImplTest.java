package c306.soduku;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        InputStream in = getClass().getResourceAsStream("/sudoku16-moyen.txt");

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
    public void testSetValue()
            throws HorsBornesException, ValeurImpossibleException, ElementInterditException, ValeurInitialeModifExcept {

        grille.setValue(0, 0, grille.getValue(4, 14));

        assertThrows(HorsBornesException.class,
                () -> grille.setValue(-1, 0, grille.getValue(0, 0)));

        assertThrows(ValeurImpossibleException.class,
                () -> grille.setValue(0, 0, grille.getValue(0, 8)));

        assertThrows(ElementInterditException.class,
                () -> grille.setValue(1, 0, new ElementDeGrilleImplAsChar('?')));

        assertThrows(ValeurInitialeModifExcept.class,
                () -> grille.setValue(0, 1, grille.getValue(9, 0)));
    }

    @Test
    public void testGetValue() throws HorsBornesException {
        assertNotNull(grille.getValue(1, 0));
    }

    @Test
    public void testIsComplete() {
        assertFalse(grille.isComplete());
    }

    @Test
    public void testIsPossible() throws HorsBornesException,
            ElementInterditException {

        assertTrue(grille.isPossible(0, 0, grille.getValue(4, 14)));

        assertThrows(HorsBornesException.class,
                () -> grille.setValue(-1, 0, grille.getValue(0, 0)));

        assertThrows(ElementInterditException.class,
                () -> grille.setValue(1, 0, new ElementDeGrilleImplAsChar('0')));
    }

    @Test
    public void testIsValeurInitiale() {
        assertTrue(grille.isValeurInitiale(14, 0));
    }
}