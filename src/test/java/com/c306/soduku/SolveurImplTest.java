package com.c306.soduku;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SolveurImplTest {
    private Grille grille;

    @BeforeEach
    public void setUp() throws IOException, ElementInterditException, ValeurInitialeModifExcept, HorsBornesException,
            ValeurImpossibleException {

        InputStream in = getClass().getResourceAsStream("/sudoku16-a.txt");

        grille = GrilleParser.parse(in);
    }

    @Test
    public void testSolve() throws HorsBornesException {
        SolveurImpl solveur = new SolveurImpl();

        assertTrue(solveur.solve(grille));
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
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
