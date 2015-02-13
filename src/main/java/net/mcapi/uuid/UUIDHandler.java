package net.mcapi.uuid;

import java.util.List;
import java.util.UUID;

import net.mcapi.uuid.utils.ExpireHashMap;
import net.mcapi.uuid.utils.Username;

/**
 * Represents a handler for interfacing
 * with the MC-API web apis
 *
 * @author njb_said
 */
public abstract class UUIDHandler {

    protected ExpireHashMap<String, UUID> uuid_cache = new ExpireHashMap<String, UUID>();
    protected ExpireHashMap<UUID, String> name_cache = new ExpireHashMap<UUID, String>();

    public ExpireHashMap<String, UUID> getUUIDCache() {
        return uuid_cache;
    }

    public ExpireHashMap<UUID, String> getNameCache() {
        return name_cache;
    }

    /**
     * Query for the UUID that corresponds to a username
     *
     * @param username username to query
     * @return the uuid or null if not found
     */
    public abstract UUID getUUID(String username);

    /**
     * Query for a string representation of the UUID
     * that corresponds to a username
     *
     * @see UUIDHandler#getUUID(String)
     * @param username username to query
     * @return the uuid or null if not found
     */
    public abstract String getUUIDString(String username);

    /**
     * Query for the username that corresponds to a UUID
     *
     * @param uuid uuid to query
     * @return the username or null
     */
    public abstract String getUsername(UUID uuid);

    /**
     * Query for a string representation of the
     * username that corresponds to a UUID
     *
     * @param uuid uuid to query
     * @return the username or null
     */
    public abstract String getUsername(String uuid);

    /**
     * Get the author of this handler
     *
     * @return author of this handler
     */
    public String getAuthor() {
        return "MC-API";
    }

    /**
     * Get a list of past usernames for a uuid
     * <p>
     * Can be empty if user has not changed name
     * or uuid not found
     *
     * @return a list of {@link Username username objects}
     */
    public List<Username> getHistory(UUID uuid) {
        throw new UnsupportedOperationException("The handler you are accessing does not support this feature yet!");
    }

}
