package c306.soduku;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class GrilleImplTest {

    private Grille grille;

    @Before
    public void setUp() {
        grille = new GrilleImpl(9);
    }

    @Test
    public void testIsComplete()
            throws HorsBornesException, ValeurImpossibleException, ValeurInitialeModifExcept {
        ElementDeGrilleImplAsChar value = new ElementDeGrilleImplAsChar('0');
        for (int i = 0; i < grille.getDimension(); i++) {
            for (int j = 0; j < grille.getDimension(); j++) {
                grille.setValue(i, j, value);
            }
        }
        assertTrue(grille.isComplete());
    }

    @Test
    public void testIsPossible()
            throws HorsBornesException, ValeurImpossibleException, ElementInterditException, ValeurInitialeModifExcept {
        ElementDeGrilleImplAsChar value1 = new ElementDeGrilleImplAsChar('0');
        ElementDeGrilleImplAsChar value2 = new ElementDeGrilleImplAsChar('9');
        grille.setValue(0, 0, value1);
        grille.setValue(0, 1, value2);

        assertTrue(grille.isPossible(0, 2, value1));
        assertFalse(grille.isPossible(0, 2, value2));
    }

}
