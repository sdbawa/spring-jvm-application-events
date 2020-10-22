package com.sdbawa.mmts.domain.statuslookup;

import com.sdbawa.mmts.domain.statuslookup.exception.StatusLookUpException;

/**
 * @author simar bawa
 */
public interface StatusLookupService {

    boolean checkIfStatusIdBelongsToType(Integer statusId, String type) throws StatusLookUpException;

    int getStatusIdByTypeAndName(String type, String name) throws StatusLookUpException;
}
