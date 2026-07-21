package com.talalanguage.api.application.search;

public class SearchSourceUnavailableException extends RuntimeException {
    private final String source;

    public SearchSourceUnavailableException(String source, Throwable cause) {
        super("Search source is temporarily unavailable: " + source, cause);
        this.source = source;
    }

    public String source() {
        return source;
    }
}
