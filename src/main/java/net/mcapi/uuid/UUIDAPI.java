package net.mcapi.uuid;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.mcapi.uuid.handlers.JavaHandler;
import net.mcapi.uuid.utils.Username;

import com.google.common.base.Preconditions;
import com.mashape.unirest.http.Unirest;

/**
 * A UUID lookup API for Java and
 * Minecraft servers.
 *
 * <p>Useful to convert a username to
 * {@link UUID} and vice-versa.
 *
 * <br>By default the {@link JavaHandler}
 * is used, however other handlers can be
 * used via {@link #setHandler(UUIDHandler)}
 * <p>
 * By default the {@link ServerRegion US server region} is used for queries, you change this via {@link #setRegion(ServerRegion)}
 *
 * @author njb_said
 */
public class UUIDAPI {

    private static ExecutorService service;
    private static UUIDHandler handler;
    private static ServerRegion region;

    /**
     * Get the current {@link ServerRegion} being used
     *
     * @return the server region we are using for queries
     */
    public static ServerRegion getRegion() {
        if(region == null) {
            region = ServerRegion.US;
        }
        return region;
    }

    /**
     * Change the {@link ServerRegion} we will use
     *
     * @param region new region to use for queries
     */
    public static void setRegion(ServerRegion region) {
        UUIDAPI.region = region;
    }

    public static void setHandler(UUIDHandler newHandler) {
        Preconditions.checkNotNull(newHandler, "Handler cannot be null!");
        handler = newHandler;
    }

    /**
     * Get the current {@link UUIDHandler} being used
     * @return the {@link UUIDHandler handler} we are using to process requests
     */
    public static UUIDHandler getHandler() {
        Preconditions.checkArgument(handler != null, "Handler cannot be null!");
        return handler;
    }

    static {
        handler = new JavaHandler();
        region = ServerRegion.US;
        Unirest.setTimeouts(3500, 15000);
        service = Executors.newFixedThreadPool(5);

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                if(!service.isShutdown()) {
                    service.shutdown();
                }
                try {
                    Unirest.shutdown();
                } catch (IOException ex) {
                    System.err.println("Error while shutting down MC-API unirest library: " + ex.getLocalizedMessage());
                }
                System.out.println("[MC-API] Shutdown");
            }
        }));
    }

    /**
     * Get a list of past usernames for a uuid
     * <p>
     * Can be empty if user has not changed name
     * or uuid not found
     *
     * @return a list of {@link Username username objects}
     */
    public static List<Username> getHistory(UUID uuid) {
        return handler.getHistory(uuid);
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
     * Reverse lookup a UUID string to a
     * username string, if the string is not
     * a valid UUID it will be converted to one.
     * <p>
     * Result is cached for one hour.
     *
     * @param uuid uuid (string) to lookup
     * @return username or <code>NULL</code> if not found
     */
    public static String getUsername(String uuid) {
        return handler.getUsername(uuid);
    }

    public static ExecutorService getExecutor() {
        return service;
    }

}
