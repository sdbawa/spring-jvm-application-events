package com.sdbawa.movies.domain.statuslookup.exception;

/**
 * @author simar bawa
 */
public class StatusLookUpException extends Exception {

    public StatusLookUpException(String errorMessage, StatusLookUpException.Type type) {
        super(errorMessage);
        this.type = type;
    }


    public enum Type {
        ID_NOT_ASSIGNED_TO_TYPE,
        ID_NOT_EXIST_FOR_TYPE_NAME,
        LOOKUPS_EMPTY,
        LOOKUP_TYPE_NOT_FOUND
    }

    private final StatusLookUpException.Type type;

    public StatusLookUpException.Type getType() {
        return type;
    }
}
