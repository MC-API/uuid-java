package net.mcapi.uuid.queries;

import net.mcapi.uuid.UUIDAPI;
import net.mcapi.uuid.calls.base.JSONCallable;

import org.json.simple.JSONObject;

public class APIQuery extends JSONCallable {

    private final String key;

    /**
     * Create a query
     * <p>
     * Will use key as path
     *
     * @param query parameter for the query
     * @param key what we want to obtain
     * @see APIQuery#APIQuery(String, String, String)
     */
    public APIQuery(String query, String key) {
        this(query, key, key);
    }

    /**
     * Create a query for a specific path
     *
     * @param query parameter for the query
     * @param key what we want to obtain
     * @param path path within the api
     */
    public APIQuery(String query, String key, String path) {
        super(UUIDAPI.getRegion().buildURL() + "/v3/" + path + "/" + query);
        this.key = key;
    }

    public String request() throws Exception {
        JSONObject jsonReturn = call();

        if(jsonReturn.containsKey(key)) {
            return jsonReturn.get(key).toString();
        }
        return null;
    }

}
