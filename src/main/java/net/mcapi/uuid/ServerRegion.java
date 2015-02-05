package net.mcapi.uuid;

import java.util.*;

/**
 * Represents a region we have
 * request taking servers in
 *
 * @author njb_said
 */
public enum ServerRegion {

    /**
     * European servers
     */
    EU("eu.mc-api.net", false, "Delta", "Zeta"),

    /**
     * American servers
     */
    US("us.mc-api.net", false, "Iota"),

    /**
     * Australian servers
     */
    AU("au.mc-api.net", false, "Zeus"),

    /**
     * Automatic server determined by our proxy
     */
    AUTO("mc-api.net", false, "All");

    private String hostname;
    private boolean https = true;
    private String[] servers;

    private ServerRegion(String hostname, String... serverNames) {
        this.hostname = hostname;
        this.servers = serverNames;
    }

    private ServerRegion(String hostname, boolean https, String... serverNames) {
        this.hostname = hostname;
        this.https = https;
        this.servers = serverNames;
    }

    public String getHostname() {
        return hostname;
    }

    public boolean allowsHTTPS() {
        return https;
    }

    public List<String> getServers() {
        return new ArrayList<String>(Arrays.asList(servers));
    }

    public String buildURL() {
        return (allowsHTTPS() ? "https://" : "http://") + getHostname();
    }

}
