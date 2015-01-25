package net.mcapi.uuid.handlers;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import net.mcapi.uuid.UUIDAPI;
import net.mcapi.uuid.UUIDHandler;
import net.mcapi.uuid.queries.APIQuery;
import net.mcapi.uuid.utils.ExpireHashMap;
import net.mcapi.uuid.utils.UUIDUtils;

public class JavaHandler implements UUIDHandler {

    private ExpireHashMap<String, UUID> uuid_cache = new ExpireHashMap<String, UUID>();
    private ExpireHashMap<UUID, String> name_cache = new ExpireHashMap<UUID, String>();

    public ExpireHashMap<String, UUID> getUUIDCache() {
        return uuid_cache;
    }

    public ExpireHashMap<UUID, String> getNameCache() {
        return name_cache;
    }

    @Override
    public UUID getUUID(String username) {
        username = username.toLowerCase();
        if(uuid_cache.containsKey(username)) {
            return uuid_cache.get(username);
        }

        APIQuery query = new APIQuery(username, "full_uuid", "uuid");

        try {
            UUID uuid = UUID.fromString(query.request());
            uuid_cache.put(username, uuid, 1, TimeUnit.HOURS);
            return uuid;
        } catch (Exception ex) {
            System.err.println("[MC-API] Could not lookup '" + username + "', returning null..");
            System.err.println("[MC-API] Server: " + UUIDAPI.getRegion().toString() + " (" + UUIDAPI.getRegion().buildURL() + ")");
            return null;
        }
    }

    @Override
    public String getUUIDString(String username) {
        UUID UUID = getUUID(username);
        String uuid = UUID == null ? null : UUID.toString().replace("-", "");

        return uuid;
    }

    @Override
    public String getUsername(UUID uuid) {
        if(name_cache.containsKey(uuid)) {
            return name_cache.get(uuid);
        }

        APIQuery query = new APIQuery(uuid.toString().replace("-", ""), "name");
        try {
            String username = query.request();
            name_cache.put(uuid, username, 1, TimeUnit.HOURS);
            return username;
        } catch (Exception ex) {
            System.err.println("[MC-API] Could not lookup '" + uuid.toString() + "', returning null..");
            System.err.println("[MC-API] Server: " + UUIDAPI.getRegion().toString() + " (" + UUIDAPI.getRegion().buildURL() + ")");
            return null;
        }
    }

    @Override
    public String getUsername(String uuid) {
        return getUsername(UUID.fromString(UUIDUtils.convertUUIDToJava(uuid)));
    }

}
