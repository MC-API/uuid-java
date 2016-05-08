package net.mcapi.uuid;

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
    EU("eu.mc-api.net", false),

    /**
     * American servers
     */
    US("us.mc-api.net", false),

    /**
     * Automatic server determined by our proxy
     */
    AUTO("mc-api.net", false);

    private String hostname;
    private boolean https = true;

    private ServerRegion(String hostname) {
        this.hostname = hostname;
    }

    private ServerRegion(String hostname, boolean https) {
        this.hostname = hostname;
        this.https = https;
    }

    public String getHostname() {
        return hostname;
    }

    public boolean allowsHTTPS() {
        return https;
    }

    public String buildURL() {
        return (allowsHTTPS() ? "https://" : "http://") + getHostname();
    }

}
