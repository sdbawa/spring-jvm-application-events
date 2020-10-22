package com.sdbawa.mmts.domain.endorsement.controller.exception;

/**
 * @author simar bawa
 */
public class EndorsementException extends Exception {
    public EndorsementException(String errorMessage, EndorsementException.Type type) {
        super(errorMessage);
        this.type = type;
    }

    public enum Type {
        REQUEST_VALIDATION,
        INTERNAL_ERROR
    }

    private final EndorsementException.Type type;

    public EndorsementException.Type getType() {
        return type;
    }
}
