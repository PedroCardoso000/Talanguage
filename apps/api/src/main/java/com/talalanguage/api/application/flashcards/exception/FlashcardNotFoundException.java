package com.talalanguage.api.application.flashcards.exception;

public class FlashcardNotFoundException extends RuntimeException {

    public FlashcardNotFoundException() {
        super("Flashcard nao encontrado.");
    }
}
