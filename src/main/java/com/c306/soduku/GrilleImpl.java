package com.c306.soduku;

import java.util.HashSet;
import java.util.Set;

public class GrilleImpl implements Grille {
    /**
     * La dimension de la grille de Sudoku.
     */
    private int dimension;
    /**
     * La grille de Sudoku représentée par un tableau à deux dimensions.
     */
    private ElementDeGrille[][] grille;
    /**
     * L'ensemble des éléments autorisés dans la grille de Sudoku.
     */
    private Set<ElementDeGrille> elements;
    /**
     * La constante représentant le nombre 4, dimension d'un carre.
     */
    private static final int QUATRE = 4;

    /**
     * Constructeur GrilleImpl.
     *
     * @param elementDeGrilles tableau d'éléments de grille
     */
    public GrilleImpl(final ElementDeGrille[] elementDeGrilles) {
        this.dimension = elementDeGrilles.length;
        grille = new ElementDeGrille[dimension][dimension];
        elements = new HashSet<ElementDeGrille>();
        for (ElementDeGrille e : elementDeGrilles) {
            elements.add(e);
        }
    }

    /**
     * @return elements possibles a mettre dans une grille.
     */
    @Override
    public Set<ElementDeGrille> getElements() {
        return new HashSet<>(elements);
    }

    /**
     * @return largeur/hauteur de la grille
     */
    @Override
    public int getDimension() {
        return dimension;
    }

    /**
     * Affecte une valeur dans une case de la grille.
     *
     * @param x     position x dans la grille
     * @param y     position y dans la grille
     * @param value élément de grille à mettre dans la case, null
     *              pour vider la case
     * @throws ValeurImpossibleException si l'élément de grille n'est pas
     *                                   autorisé à cette position dans la
     *                                   grille
     *                                   aux vues des autres valeurs de la
     *                                   grille
     * @throws ElementInterditException  si l'élément de grille n'est pas
     *                                   autorisé dans cette grille
     *                                   pouvant être mis dans la grille
     * @throws HorsBornesException       si x ou y sont en dehors de la
     *                                   grille
     * @throws ValeurInitialeModifExcept si une valeur initiale de la
     *                                   grille est en position x,y
     */
    @Override
    public void setValue(final int x, final int y, final ElementDeGrille value)
            throws HorsBornesException, ValeurImpossibleException,
            ElementInterditException, ValeurInitialeModifExcept {

        if (x < 0 || y < 0 || x > dimension || y > dimension) {
            throw new HorsBornesException("HorsBornesException");
        }

        if (!isPossible(x, y, value)) {
            throw new ValeurImpossibleException("ValeurImpossibleException");
        }

        if (!elements.contains(value) && value != null) {
            throw new ElementInterditException("ElementInterditException");
        }

        if (isValeurInitiale(x, y)) {
            throw new ValeurInitialeModifExcept("ValeurInitialeModifExcept");
        }

        grille[x][y] = value;
    }

    /**
     * Renvoie une valeur de la grille.
     *
     * @param x position x dans la grille
     * @param y position y dans la grille
     * @return élément de la grille de la case x,y, null
     *         s'il n'y a pas d'élément à cette position
     * @throws HorsBornesException si x ou y sont en dehors de la grille
     */
    @Override
    public ElementDeGrille getValue(final int x, final int y)
            throws HorsBornesException {
        return grille[x][y];
    }

    /**
     * Teste si une grille est remplie.
     *
     * @return true si la grille est complete
     */
    @Override
    public boolean isComplete() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (grille[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Teste si une valeur peut être placée dans la grille.
     *
     * @param x     position x dans la grille
     * @param y     position y dans la grille
     * @param value valeur a mettre dans la case
     * @return true si value peut être placé dans la grille en
     *         position x,y en respectant les règles du sudoku
     *         et sans modifier une valeur initiale.
     * @throws HorsBornesException      si x ou y sont hors bornes
     * @throws ElementInterditException si value n'est pas un caractere
     *                                  pouvant être mis dans la grille
     */
    @Override
    public boolean isPossible(final int x,
            final int y,
            final ElementDeGrille value)
            throws HorsBornesException, ElementInterditException {

        int xDebut = (x / QUATRE) * QUATRE;
        int yDebut = (y / QUATRE) * QUATRE;

        if (x < 0 || y < 0 || x > dimension || y > dimension) {
            throw new HorsBornesException("HorsBornesException");
        }

        if (!elements.contains(value) && value != null) {
            throw new ElementInterditException("ElementInterditException");
        }

        for (int i = 0; i < dimension; i++) {
            if (grille[x][i] == value || grille[i][y] == value) {
                return false;
            }
        }

        for (int i = xDebut; i < xDebut + QUATRE; i++) {
            for (int j = yDebut; j < yDebut + QUATRE; j++) {
                if (grille[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * @param x position x dans la grille
     * @param y position y dans la grille
     * @return true si la case x,y contient une valeur
     *         initiale de la grille.
     */
    @Override
    public boolean isValeurInitiale(final int x, final int y) {
        if (grille[x][y] == null) {
            return false;
        }
        return true;
    }
}
