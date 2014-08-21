package net.mcapi.uuid;

import java.util.UUID;

/**
 * A UUID lookup API for Java
 * <p>
 * Useful to convert a username to {@link UUID} and vice-versa
 * 
 * @author MC-API
 */
public class UUIDAPI {

	/**
	 * Find the UUID that matches a username
	 * 
	 * @param username username to lookup
	 * @return the {@link UUID}
	 */
	public static UUID getUUID(String username) {
		return null;
	}

	/**
	 * Find the UUID that matches a username
	 * 
	 * @param username username to lookup
	 * @return a string without dashes
	 */
	public static String getUUIDString(String username) {
		UUID UUID = getUUID(username);
		String uuid = UUID == null ? null : UUID.toString().replace("-", "");

		return uuid;
	}

	/**
	 * Reverse lookup a {@link UUID} to a username string
	 * 
	 * @param uuid UUID to lookup
	 * @return username or <code>NULL</code> if not found
	 */
	public static String getUsername(UUID uuid) {
		return null;
	}

	/**
	 * Reverse lookup a UUID string to a username string
	 * <p>
	 * If the string is not a valid UUID it will be converted to one
	 * 
	 * @param uuid uuid (string) to lookup
	 * @return username or <code>NULL</code> if not found
	 */
	public static String getUsername(String uuid) {
		return getUsername(UUID.fromString(UUIDUtils.convertUUIDToJava(uuid)));
	}

}
