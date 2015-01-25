package net.mcapi.uuid.queries;

import net.mcapi.uuid.UUIDAPI;
import net.mcapi.uuid.calls.base.JSONCallable;

import org.json.simple.JSONObject;

public class APIQuery extends JSONCallable {

    private final String key;

    public APIQuery(String query, String key) {
        super(UUIDAPI.getRegion().buildURL() + "/v3/" + key + "/" + query);
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
