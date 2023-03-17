package c306.soduku;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

public class GrilleImplTest {

    private Grille grille;

    @Before
    public void setUp() {
        grille = new GrilleImpl(9);
    }

    @Test
    public void testIsPossible()
            throws HorsBornesException, ValeurImpossibleException, ElementInterditException, ValeurInitialeModifExcept {

        Map<Character, ElementDeGrille> elementDeGrilleMap = new HashMap<>();
        char v = '*';
        ElementDeGrille value = new ElementDeGrilleImplAsChar(v);
        elementDeGrilleMap.put(v, value);

        assertFalse(grille.isValeurInitiale(0, 2));
        assertTrue(grille.isPossible(0, 2, value));

    }
}
