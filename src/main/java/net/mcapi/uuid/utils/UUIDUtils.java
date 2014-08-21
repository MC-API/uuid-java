package net.mcapi.uuid.utils;

import lombok.Getter;
import net.mcapi.uuid.UUIDAPI;

import org.json.simple.parser.JSONParser;

/**
 * Utility methods for UUIDs
 * 
 * @author MC-API
 */
public class UUIDUtils {

	@Getter private static JSONParser parser = new JSONParser();

	/**
	 * Convert a string into a string that {@link UUID}.fromString will accept
	 * 
	 * @param uuid uuid string (without dashes)
	 * @return a uuid compatible string 
	 */
    public static String convertUUIDToJava(String uuid) {
    	if(uuid.contains("-")) {
    		return uuid;
    	}

    	if(uuid.length() < 32) {
    		uuid = UUIDAPI.getUUIDString(uuid);
    	}

        uuid = insertChar(uuid, '-', 8);
        uuid = insertChar(uuid, '-', 13);
        uuid = insertChar(uuid, '-', 18);
        uuid = insertChar(uuid, '-', 23);

        return uuid;
    }

    private static String insertChar(String str, char ch, int index) {
        return str.substring(0, index) + ch + str.substring(index, str.length());
    }

}
