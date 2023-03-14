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
    public void testIsComplete() {
        /*
         * ElementDeGrille value = new ElementDeGrille();
         * for (int i = 0; i < grille.getDimension(); i++) {
         * for (int j = 0; j < grille.getDimension(); j++) {
         * grille.setValue(i, j, value);
         * }
         * }
         * assertTrue(grille.isComplete());
         */
    }

    @Test
    public void testIsPossible() {
        /*
         * ElementDeGrille value1 = new ElementDeGrille();
         * ElementDeGrille value2 = new ElementDeGrille();
         * grille.setValue(0, 0, value1);
         * grille.setValue(0, 1, value2);
         * 
         * assertTrue(grille.isPossible(0, 2, value1));
         * assertFalse(grille.isPossible(0, 2, value2));
         */
    }

}
