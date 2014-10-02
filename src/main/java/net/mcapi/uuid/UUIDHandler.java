package net.mcapi.uuid;

import java.util.UUID;

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

}
