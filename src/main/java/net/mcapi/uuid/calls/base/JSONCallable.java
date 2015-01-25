package net.mcapi.uuid.calls.base;

import net.mcapi.uuid.utils.UUIDUtils;

import org.json.simple.JSONObject;

import com.mashape.unirest.http.*;

public abstract class JSONCallable extends Callable<JSONObject> {

    private final String url;

    public JSONCallable(String url) {
        this.url = url;
    }

    @Override
    public JSONObject call() throws Exception {
        HttpResponse<JsonNode> response = Unirest.get(url).asJson();
        if(response == null || response.getBody() == null || response.getBody().getObject() == null) {
            return null;
        }

        return (JSONObject)UUIDUtils.PARSER.parse(response.getBody().getObject().toString());
    }

}
