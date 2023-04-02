package com.c306.soduku;

/**
 * Interface de résolveur de Grille
 *
 * @author Sébastien Choplin <sebastien.choplin@u-picardie.fr>
 */
public interface Solveur {
    /**
     * Résoud une Grille
     *
     * @param grille Grille à résoudre
     * @return true si la grille a été résolue
     */
    boolean solve(Grille grille);
}
