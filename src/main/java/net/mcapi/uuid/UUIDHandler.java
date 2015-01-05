package net.mcapi.uuid;

import java.util.UUID;

import net.mcapi.uuid.utils.ExpireHashMap;

/**
 * An interface to represent a handler for UUID conversions
 *
 * @author njb_said
 */
public abstract interface UUIDHandler {

    public UUID getUUID(String username);
    public String getUUIDString(String username);
    public String getUsername(UUID uuid);
    public String getUsername(String uuid);

    public ExpireHashMap<String, UUID> getUUIDCache();
    public ExpireHashMap<UUID, String> getNameCache();

}
