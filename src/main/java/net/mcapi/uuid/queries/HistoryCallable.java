package net.mcapi.uuid.queries;

import java.util.LinkedList;
import java.util.List;

import net.mcapi.uuid.UUIDAPI;
import net.mcapi.uuid.calls.base.JSONCallable;
import net.mcapi.uuid.utils.Username;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class HistoryCallable extends JSONCallable {

    public HistoryCallable(String query) {
        super(UUIDAPI.getRegion().buildURL() + "/v3/history/" + query);
    }

    public List<Username> request() throws Exception {
        JSONObject jsonReturn = call();

        if(jsonReturn.containsKey("history")) {
            List<Username> list = new LinkedList<Username>();

            for(Object o : (JSONArray)jsonReturn.get("history")) {
                if(o instanceof JSONObject) {
                    JSONObject obj = (JSONObject)o;
                    long time = 0;
                    if (obj.containsKey("changedToAt")){
                        time = Long.parseLong(obj.get("changedToAt").toString());
                    }
                    list.add(new Username(obj.get("name").toString(), time));
                }
            }

            return list;
        }

        return null;
    }

}
