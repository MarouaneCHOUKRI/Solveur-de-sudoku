package com.c306.soduku;

public class ElementDeGrilleImplAsChar implements ElementDeGrille {
    private final char caractere;

    public ElementDeGrilleImplAsChar(char caractere) {
        this.caractere = caractere;
    }

    @Override
    public char getRepresentation() {
        return caractere;
    }
}
