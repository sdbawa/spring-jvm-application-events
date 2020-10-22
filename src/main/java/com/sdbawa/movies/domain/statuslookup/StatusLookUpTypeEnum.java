package com.sdbawa.movies.domain.statuslookup;

/**
 * @author simar bawa
 */
public enum StatusLookUpTypeEnum {
    ACTOR("ACTOR"),
    MOVIE("MOVIE"),
    DIRECTOR("DIRECTOR");

    private String value;

    StatusLookUpTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
