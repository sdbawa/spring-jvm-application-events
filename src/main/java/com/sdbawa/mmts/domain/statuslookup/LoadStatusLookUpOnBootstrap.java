package com.sdbawa.mmts.domain.statuslookup;

import com.google.common.collect.ImmutableMap;
import com.sdbawa.mmts.domain.statuslookup.exception.StatusLookUpException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author simar bawa
 */
@Component
@Slf4j
public class LoadStatusLookUpOnBootstrap implements StatusLookupService {

    private final JdbcTemplate jdbcTemplate;

    public LoadStatusLookUpOnBootstrap(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private ImmutableMap<String, List<Map<String, Integer>>> statusLookUpMap;

    private static String dataLoadQuery = "select id, name, type from STATUS_LOOKUP";
    private static String nameColumnDefinition = "name";
    private static String typeColumnDefinition = "type";
    private static String statusIdColumnDefinition = "id";

    @Override
    public boolean checkIfStatusIdBelongsToType(Integer statusId, String type) throws StatusLookUpException {
        if (checkIfStatusLookUpIsNotNullAndTypeExist(type)) {
            List<Map<String, Integer>> typeEntries = statusLookUpMap.get(type);
            for (Map<String, Integer> entry :  typeEntries) {
                if (entry.containsValue(statusId)) {
                    return true;
                }
            }
        }
        throw new StatusLookUpException("StatusId " + statusId.intValue()
                        + " is NOT allowed for type " + type,
                        StatusLookUpException.Type.ID_NOT_ASSIGNED_TO_TYPE);
    }

    @Override
    public int getStatusIdByTypeAndName(String type, String name) throws StatusLookUpException {
        if (checkIfStatusLookUpIsNotNullAndTypeExist(type)) {
            List<Map<String, Integer>> typeEntries = statusLookUpMap.get(type);
            for (Map<String, Integer> entry :  typeEntries) {
                if (entry.get(name) != null ) {
                    return entry.get(name);
                }
            }
        }
        throw new StatusLookUpException("StatusId dos NOT exist for"
                        + " type = " + type  + " and name = " + name,
                        StatusLookUpException.Type.ID_NOT_EXIST_FOR_TYPE_NAME);
    }

    /**
     * This method is  responsible to load data from status_lookup table during application startup.
     * During the life cycle of application, this data will be served from in-memory.
     *
     */
    @PostConstruct
    void loadStatusMapFromDatabase() {
        log.info("Loading Status Map From Database during bootstrap");
        try {
            jdbcTemplate.query(dataLoadQuery, (ResultSet rs) -> {
                HashMap<String, List<Map<String, Integer>>> mapRet = new HashMap<String, List<Map<String, Integer>>>();
                do {
                    Map<String, Integer> nameIdMap = new HashMap<>();
                    nameIdMap.put(rs.getString(nameColumnDefinition), rs.getInt(statusIdColumnDefinition));
                    if (mapRet.get(rs.getString(typeColumnDefinition)) == null) {
                        List<Map<String, Integer>> list = new LinkedList<>();
                        list.add(nameIdMap);
                        mapRet.put(rs.getString(typeColumnDefinition), list);
                    } else {
                        List<Map<String, Integer>> list = mapRet.get(rs.getString(typeColumnDefinition));
                        list.add(nameIdMap);
                        mapRet.put(rs.getString(typeColumnDefinition), list);
                    }
                }
                while (rs.next()); /*checkstyle requires statement on new line*/
                if (CollectionUtils.isEmpty(mapRet)) {
                    log.error("Alert - status_lookup is empty");
                    return;
                }
                /*Do not return actual data, instead return copy. */
                this.statusLookUpMap = ImmutableMap.<String, List<Map<String, Integer>>>builder()
                                .putAll(mapRet)
                                .build();
            });
        } catch (Exception e) {
            log.error("Alert - Error while loading loading data from status_lookup table during application bootstrap {}", e.getMessage());
        }
        log.info("Done Loading Status Map From Database during bootstrap with size " + statusLookUpMap.size());
    }

    private boolean checkIfStatusLookUpIsNotNullAndTypeExist(String type) throws StatusLookUpException {
        if (CollectionUtils.isEmpty(statusLookUpMap)) {
            throw new StatusLookUpException("status_lookup returned empty records", StatusLookUpException.Type.LOOKUPS_EMPTY);
        }

        if (statusLookUpMap.get(type) == null) {
            throw new StatusLookUpException("no records found for type = " + type , StatusLookUpException.Type.LOOKUP_TYPE_NOT_FOUND);
        }
        return Boolean.TRUE;
    }

}
