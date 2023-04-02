package com.c306.soduku;

public class SolveurImpl implements Solveur {

    private static int dimension = 16;

    public boolean solve(Grille grille) {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
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
                } catch (HorsBornesException | ElementInterditException | ValeurImpossibleException
                        | ValeurInitialeModifExcept e) {
                    System.out.println(e.getMessage() + " i : " + i + " j : " + j);
                }
            }
        }
        return true;
    }
}
