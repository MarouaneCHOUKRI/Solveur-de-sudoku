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
    public void testIsPossible()
            throws HorsBornesException, ValeurImpossibleException, ElementInterditException, ValeurInitialeModifExcept {

        ElementDeGrille value1 = new ElementDeGrilleImplAsChar('0');
        ElementDeGrille value2 = new ElementDeGrilleImplAsChar('2');


        assertTrue(grille.isPossible(0, 2, value1));
        grille.setValue(0, 2, value1);
        assertFalse(grille.isPossible(0, 2, value2));
    }
}
