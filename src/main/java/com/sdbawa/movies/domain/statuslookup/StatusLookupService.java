package com.sdbawa.movies.domain.statuslookup;

import com.sdbawa.movies.domain.statuslookup.exception.StatusLookUpException;

/**
 * @author simar bawa
 */
public interface StatusLookupService {

    boolean checkIfStatusIdBelongsToType(Integer statusId, String type) throws StatusLookUpException;

    int getStatusIdByTypeAndName(String type, String name) throws StatusLookUpException;
}
