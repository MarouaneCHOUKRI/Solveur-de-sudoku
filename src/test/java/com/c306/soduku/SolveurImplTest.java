package com.c306.soduku;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SolveurImplTest {
    /**
     * @param grille
     */
    private Grille grille;
    /**
     * @param dimension
     */
    private final int dimension = 16;

    /**
     * Configure la grille de test avant le test.
     * @throws IOException
     * @throws ElementInterditException
     * @throws ValeurInitialeModifExcept
     * @throws HorsBornesException
     * @throws ValeurImpossibleException
     */
    @BeforeEach
    public void setUp() throws IOException, ElementInterditException,
            ValeurInitialeModifExcept, HorsBornesException,
            ValeurImpossibleException {

        InputStream in = getClass().getResourceAsStream("/sudoku16-a.txt");

        grille = GrilleParser.parse(in);
    }

    /**
     * Teste la méthode solve() pour vérifier
     * si la grille a bien ete resolue.
     */
    @Test
    public void testSolve() throws HorsBornesException {
        SolveurImpl solveur = new SolveurImpl();

        assertTrue(solveur.solve(grille));
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (grille.getValue(i, j) == null) {
                    System.out.print("@");
                } else {
                    System.out.print(grille.getValue(i, j).getRepresentation());
                }
            }
            System.out.println();
        }
    }
}
