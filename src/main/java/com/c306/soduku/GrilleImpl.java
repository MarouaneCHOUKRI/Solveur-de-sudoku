package com.c306.soduku;

import java.util.HashSet;
import java.util.Set;

public class GrilleImpl implements Grille {

    private int dimension;
    private ElementDeGrille[][] grille;
    private Set<ElementDeGrille> elements;

    public GrilleImpl(ElementDeGrille[] elementDeGrilles) {
        this.dimension = elementDeGrilles.length;
        grille = new ElementDeGrille[dimension][dimension];
        elements = new HashSet<ElementDeGrille>();
        for (ElementDeGrille e : elementDeGrilles) {
            elements.add(e);
        }
    }

    @Override
    public Set<ElementDeGrille> getElements() {
        return new HashSet<>(elements);
    }

    @Override
    public int getDimension() {
        return dimension;
    }

    @Override
    public void setValue(int x, int y, ElementDeGrille value)
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

    @Override
    public ElementDeGrille getValue(int x, int y) throws HorsBornesException {
        return grille[x][y];
    }

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

    @Override
    public boolean isPossible(int x, int y, ElementDeGrille value)
            throws HorsBornesException, ElementInterditException {

        int xDebut = (x / 4) * 4;
        int yDebut = (y / 4) * 4;

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

        for (int i = xDebut; i < xDebut + 4; i++) {
            for (int j = yDebut; j < yDebut + 4; j++) {
                if (grille[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public boolean isValeurInitiale(int x, int y) {
        if (grille[x][y] == null) {
            return false;
        }
        return true;
    }
}
