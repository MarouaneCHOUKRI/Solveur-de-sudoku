package com.c306.soduku;

public class SolveurImpl implements Solveur {

    /**
     * @param dimension
     */
    private static final int DIM = 16;

    /**
     * Résoud une Grille.
     * @param grille Grille à résoudre
     * @return true si la grille a été résolue
     */
    @Override
    public boolean solve(final Grille grille) {
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                try {
                    if (grille.getValue(i, j) == null) {
                        for (ElementDeGrille element : grille.getElements()) {
                            if (grille.isPossible(i, j, element)) {
                                grille.setValue(i, j, element);
                                if (solve(grille)) {
                                    return true;
                                }
                            }
                        }
                    }
                } catch (HorsBornesException | ElementInterditException
                        | ValeurImpossibleException
                        | ValeurInitialeModifExcept e) {
                    System.out.println(e.getMessage() + " i : " + i
                            + " j : " + j);
                }
            }
        }
        return true;
    }
}
