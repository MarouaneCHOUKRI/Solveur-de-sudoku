package com.c306.soduku;

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
    /**
     * @param dimension
     */
    private final int dimension = 16;
    /**
     * @param grille
     */
    private Grille grille;
    /**
     * @param elements
     */
    private Set<ElementDeGrille> elements;
    /**
     * @param v4
     */
    private final int v4 = 4;
    /**
     * @param v14
     */
    private final int v14 = 14;
    /**
     * @param v8
     */
    private final int v8 = 8;
    /**
     * @param v9
     */
    private final int v9 = 9;

    /**
     * Configure la grille de test avant chaque test.
     * @throws IOException
     * @throws ElementInterditException
     * @throws ValeurInitialeModifExcept
     * @throws HorsBornesException
     * @throws ValeurImpossibleException
     */
    @BeforeEach
    public void setUp() throws IOException, ElementInterditException,
            ValeurInitialeModifExcept,
            HorsBornesException, ValeurImpossibleException {

        InputStream in = getClass()
                .getResourceAsStream("/sudoku16-moyen.txt");

        grille = GrilleParser.parse(in);
        elements = grille.getElements();
    }

    /**
     * Teste la méthode getElements() pour vérifier
     * si la liste d'éléments de la grille n'est pas vide.
     */
    @Test
    public void testGetElements() {
        assertFalse(elements.isEmpty());
    }

    /**
     * Teste la méthode getDimension() pour vérifier si la
     * dimension de la grille correspond à la dimension définie.
     */
    @Test
    public void testGetDimension() {
        assertEquals(dimension, grille.getDimension());
    }

    /**
     * Teste la méthode setValue() pour vérifier si les exceptions appropriées
     * sont levées lorsqu'une tentative est faite pour modifier
     * une valeur de la grille.
     * @throws HorsBornesException
     * @throws ValeurImpossibleException
     * @throws ElementInterditException
     * @throws ValeurInitialeModifExcept
     */
    @Test
    public void testSetValue()
            throws HorsBornesException, ValeurImpossibleException,
            ElementInterditException, ValeurInitialeModifExcept {

        grille.setValue(0, 0, grille.getValue(v4, v14));

        assertThrows(HorsBornesException.class,
                () -> grille.setValue(-1, 0, grille.getValue(0, 0)));

        assertThrows(ValeurImpossibleException.class,
                () -> grille.setValue(0, 0, grille.getValue(0, v8)));

        assertThrows(ElementInterditException.class,
                () -> grille.setValue(1, 0,
                        new ElementDeGrilleImplAsChar('?')));

        assertThrows(ValeurInitialeModifExcept.class,
                () -> grille.setValue(0, 1, grille.getValue(v9, 0)));
    }

    /**
     * Teste la méthode getValue() pour vérifier si la méthode
     * renvoie une valeur non nulle pour des coordonnées
     * valides de la grille.
     * @throws HorsBornesException si les coordonnées de
     *                             la grille sont en dehors
     *                             des bornes valides
     */
    @Test
    public void testGetValue() throws HorsBornesException {
        assertNotNull(grille.getValue(1, 0));
    }

    /**
     * Teste la méthode isComplete() pour vérifier si
     * la méthode renvoie false pour une grille incomplète.
     */
    @Test
    public void testIsComplete() {
        assertFalse(grille.isComplete());
    }

    /**
     * Teste la méthode isPossible() pour vérifier si les exceptions appropriées
     * sont levées.
     * @throws HorsBornesException
     * @throws ElementInterditException
     */
    @Test
    public void testIsPossible() throws HorsBornesException,
            ElementInterditException {

        assertTrue(grille.isPossible(0, 0, grille.getValue(v4, v14)));

        assertThrows(HorsBornesException.class,
                () -> grille.isPossible(-1, 0, grille.getValue(0, 0)));

        assertThrows(ElementInterditException.class,
                () -> grille.isPossible(1, 0,
                        new ElementDeGrilleImplAsChar('0')));
    }

    /**
     * Teste la méthode isValeurInitiale() pour vérifier si elle renvoie
     * correctement true pour une valeur initiale de la grille.
     */
    @Test
    public void testIsValeurInitiale() {
        assertTrue(grille.isValeurInitiale(v14, 0));
    }
}
