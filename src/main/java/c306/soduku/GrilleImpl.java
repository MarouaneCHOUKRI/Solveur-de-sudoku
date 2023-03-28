package c306.soduku;

import java.util.HashSet;
import java.util.Set;

public class GrilleImpl implements Grille {

    private ElementDeGrille[][] grille;
    private Set<ElementDeGrille> elements;
    private int dimension;

    public GrilleImpl(ElementDeGrille[] elementDeGrilles) {
        this.dimension = elementDeGrilles.length;
        grille = new ElementDeGrille[dimension][dimension];
        elements = new HashSet<>();
        for (ElementDeGrille e : elementDeGrilles) {
            elements.add(e);
        }
    }

    @Override
    public Set<ElementDeGrille> getElements() {
        return elements;
    }

    @Override
    public int getDimension() {
        return dimension;
    }

    @Override
    public void setValue(int x, int y, ElementDeGrille value)
            throws HorsBornesException, ValeurImpossibleException, ElementInterditException, ValeurInitialeModifExcept {
        // HorsBornesException
        if (x < 0 || y < 0 || x > dimension || y > dimension) {
            throw new HorsBornesException("HorsBornesException");
        }

        // ValeurImpossibleException
        if (value != null && !isPossible(x, y, value)) {
            throw new ValeurImpossibleException("ValeurImpossibleException");
        }

        // ElementInterditException
        if (!elements.contains(value)) {
            throw new ElementInterditException("ElementInterditException");
        }

        // ValeurInitialeModifExcept
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

        if (x < 0 || y < 0 || x > dimension || y > dimension) {
            throw new HorsBornesException("HorsBornesException");
        }

        if (!elements.contains(value)) {
            throw new ElementInterditException("ElementInterditException");
        }

        for (int i = 0; i < dimension; i++) {
            if (x < 0 || y < 0 || grille[x][i] == value || grille[i][y] == value) {
                return false;
            }
        }

        /*
         * int xStart = (x / 3) * 3;
         * int yStart = (y / 3) * 3;
         * for (int i = xStart; i < xStart + 3; i++) {
         * for (int j = yStart; j < yStart + 3; j++) {
         * if (grille[i][j] == value) {
         * return false;
         * }
         * }
         * }
         */

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