package net.mcapi.uuid;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import net.mcapi.uuid.queries.NameQuery;
import net.mcapi.uuid.queries.UUIDQuery;
import net.mcapi.uuid.utils.ExpireHashMap;
import net.mcapi.uuid.utils.UUIDUtils;

/**
 * A UUID lookup API for Java
 * <p>
 * Useful to convert a username to {@link UUID} and vice-versa
 * 
 * @author MC-API
 */
public class UUIDAPI {

    private static ExpireHashMap<String, UUID> uuids = new ExpireHashMap<String, UUID>();
    private static ExpireHashMap<UUID, String> names = new ExpireHashMap<UUID, String>();

    /**
     * Find the UUID that matches a username
     * <p>
     * Result is cached for one hour
     * 
     * @param username username to lookup
     * @return the {@link UUID}
     */
    public static UUID getUUID(String username) {
        if(uuids.containsKey(username)) {
            return uuids.get(username);
        }

        UUIDQuery query = new UUIDQuery(username);

        try {
            UUID uuid = uuids.put(username, query.call(), 1, TimeUnit.HOURS);
            return uuid;
        } catch (Exception ex) {
            System.err.println("[MC-API] Could not lookup '" + username + "', returning null..");
            return null;
        }
    }

    /**
     * Find the UUID that matches a username
     * <p>
     * Result is cached for one hour
     * 
     * @param username username to lookup
     * @return a string without dashes
     */
    public static String getUUIDString(String username) {
        UUID UUID = getUUID(username);
        String uuid = UUID == null ? null : UUID.toString().replace("-", "");

        return uuid;
    }

    /**
     * Reverse lookup a {@link UUID} to a username string
     * <p>
     * Result is cached for one hour
     * 
     * @param uuid UUID to lookup
     * @return username or <code>NULL</code> if not found
     */
    public static String getUsername(UUID uuid) {
        if(names.containsKey(uuid)) {
            return names.get(uuid);
        }

        NameQuery query = new NameQuery(uuid.toString().replace("-", ""));

        try {
            String username = names.put(uuid, query.call(), 1, TimeUnit.HOURS);
            return username;
        } catch (Exception ex) {
            System.err.println("[MC-API] Could not lookup '" + uuid.toString() + "', returning null..");
            return null;
        }
    }

    /**
     * Reverse lookup a UUID string to a username string, if the string is not a valid UUID it will be converted to one
     * <p>
     * Result is cached for one hour
     * 
     * @param uuid uuid (string) to lookup
     * @return username or <code>NULL</code> if not found
     */
    public static String getUsername(String uuid) {
        return getUsername(UUID.fromString(UUIDUtils.convertUUIDToJava(uuid)));
    }

}
