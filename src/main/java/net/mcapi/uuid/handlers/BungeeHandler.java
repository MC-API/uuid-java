package net.mcapi.uuid.handlers;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import net.mcapi.uuid.UUIDHandler;
import net.mcapi.uuid.queries.NameQuery;
import net.mcapi.uuid.queries.UUIDQuery;
import net.mcapi.uuid.utils.ExpireHashMap;
import net.mcapi.uuid.utils.UUIDUtils;
import net.md_5.bungee.api.ProxyServer;

public class BungeeHandler implements UUIDHandler {

    private static ExpireHashMap<String, UUID> uuids = new ExpireHashMap<String, UUID>();
    private static ExpireHashMap<UUID, String> names = new ExpireHashMap<UUID, String>();

    @Override
    public UUID getUUID(String username) {
        if(ProxyServer.getInstance().getPlayer(username) != null) {
            return ProxyServer.getInstance().getPlayer(username).getUniqueId();
        }

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

    @Override
    public String getUUIDString(String username) {
        UUID UUID = getUUID(username);
        String uuid = UUID == null ? null : UUID.toString().replace("-", "");

        return uuid;
    }

    @Override
    public String getUsername(UUID uuid) {
        if(ProxyServer.getInstance().getPlayer(uuid) != null) {
            return ProxyServer.getInstance().getPlayer(uuid).getName();
        }

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

    @Override
    public String getUsername(String uuid) {
        return getUsername(UUID.fromString(UUIDUtils.convertUUIDToJava(uuid)));
    }

}
