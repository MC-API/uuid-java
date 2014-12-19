package net.mcapi.uuid;

import java.util.*;

import lombok.Getter;
import lombok.Setter;
import net.mcapi.uuid.handlers.*;

/**
 * A UUID lookup API for Java.
 * <p>
 * Useful to convert a username to {@link UUID} and vice-versa.
 * <br>
 * By default the {@link JavaHandler} is used, however other handlers can be used via {@link #setHandler(UUIDHandler)}
 *
 * @author njb_said
 */
public class UUIDAPI {

    @Getter @Setter private static UUIDHandler handler;
    @Getter private static Set<UUIDHandler> handlers = new HashSet<UUIDHandler>();

    static {
        handlers.add(new JavaHandler());
        handlers.add(new BukkitHandler());
        handlers.add(new BungeeHandler());
        handler = new JavaHandler();
    }

    /**
     * Find the UUID that matches a username.
     * <p>
     * Result is cached for one hour.
     *
     * @param username username to lookup
     * @return the {@link UUID}
     */
    public static UUID getUUID(String username) {
        return handler.getUUID(username);
    }

    /**
     * Find the UUID that matches a username.
     * <p>
     * Result is cached for one hour.
     *
     * @param username username to lookup
     * @return a string without dashes
     */
    public static String getUUIDString(String username) {
        return handler.getUUIDString(username);
    }

    /**
     * Reverse lookup a {@link UUID} to a username string.
     * <p>
     * Result is cached for one hour.
     *
     * @param uuid UUID to lookup
     * @return username or <code>NULL</code> if not found
     */
    public static String getUsername(UUID uuid) {
        return handler.getUsername(uuid);
    }

    /**
     * Reverse lookup a UUID string to a username string, if the string is not a
     * valid UUID it will be converted to one.
     * <p>
     * Result is cached for one hour.
     *
     * @param uuid uuid (string) to lookup
     * @return username or <code>NULL</code> if not found
     */
    public static String getUsername(String uuid) {
        return handler.getUsername(uuid);
    }

}
