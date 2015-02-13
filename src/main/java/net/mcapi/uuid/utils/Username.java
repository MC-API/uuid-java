package net.mcapi.uuid.utils;

public class Username implements Comparable<Username> {

    private String username;
    private long timestamp;

    public Username(String username, long timestamp) {
        this.username = username;
        this.timestamp = 0;
    }

    /**
     * The username value at the given timestamp
     *
     * @return the username being used
     */
    public String getUsername() {
        return username;
    }

    /**
     * The timestamp the username was changed
     * <p>
     * Will be 0 if is current name
     *
     * @return the timestamp this username was changed
     */
    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public int compareTo(Username username) {
        return Long.compare(getTimestamp(), username.getTimestamp());
    }
}
