package com.c306.soduku;

public class ElementDeGrilleImplAsChar implements ElementDeGrille {
    /**
     * @param representation.
     */
    private char representation;

    /**
     * @param caractere le caractère représentant l'élément de grille
     */
    public ElementDeGrilleImplAsChar(final char caractere) {
        this.representation = caractere;
    }

    /**
     * @return la représentation de l'élément de grille
     */
    @Override
    public char getRepresentation() {
        return representation;
    }
}
